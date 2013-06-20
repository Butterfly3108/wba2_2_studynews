package xmpp;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.Subscription;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

public class ConnectionHandler {

    private XMPPConnection xmpp_conn;
    private AccountManager ac;
    private PubSubManager pubsub_man;
    private String username;
    private String hostname;
    private ItemEventListener<Item> listener;

    public ConnectionHandler() {
    }

    /**
	* Baut eine Verbindung zum XMPP Server auf
	*
	* @param hostname Server Addresse
	* @param port Server Port
	* @return erfolgreich oder fehlgeschlagen
	*/
    public boolean connect(String hostname, int port) {

        if (xmpp_conn != null && xmpp_conn.isConnected()) {
            return true;
        }

        ConnectionConfiguration config = new ConnectionConfiguration(hostname, port);
        xmpp_conn = new XMPPConnection(config);
        ac = new AccountManager(xmpp_conn);

        try {
            xmpp_conn.connect();
            pubsub_man = new PubSubManager(xmpp_conn, "pubsub."+ xmpp_conn.getHost());
        } catch (XMPPException e) {
            return false;
        }
        
        this.hostname = hostname;
        
        return true;
    }

    /**
	* Login beim XMPP Server
	*
	* @param username Benutzdername vom Benutzer
	* @param password Passwort vom Benutzer
	* @return erfolgreich oder fehlgeschlagen
	*/
    public boolean login(String username, String password) {

        try {
            xmpp_conn.login(username, password);
        } catch (XMPPException e) {
            return false;
        }
        
        this.username = username;
        
        return true;
    }

    /**
	* Registriert einen Benutzer
	*
	* @param username Benutzdername vom Benutzer
	* @param password Passwort vom Benutzer
	* @return erfolgreich oder fehlgeschlagen
	*/
    public boolean register(String username, String password) {

        try {
            ac.createAccount(username, password);
        } catch (XMPPException e) {
            return false;
        }

        return true;
    }

    /**
     * Erstellt einen neuen Node
     * @param nodeName Name des zu erstellenden Nodes
     * @return erfolgreich oder fehlgeschlagen
     */
    public boolean createNewNode(String nodeName) {

    	try {
    		LeafNode node;
        	ConfigureForm form = new ConfigureForm(FormType.submit);
        	form.setAccessModel(AccessModel.open);
        	form.setDeliverPayloads(true);
        	form.setNotifyRetract(true);
        	form.setPersistentItems(false);
        	form.setPublishModel(PublishModel.open);
        	node = pubsub_man.createNode(nodeName);
        	node.sendConfigurationForm(form);

        } catch (XMPPException e1) {
            System.err.println("Node konnte nicht erstellt werden!");
            return false;
        }
    	
    	System.out.println("Node nodeName erstellt");
    	return true;

    }
    
    /**
	* Liste aller Nodes 
	*
	* @return Liste aller Nodes
	*/
    public List<String> getAllNodes() {

        List<String> entries = new ArrayList<String>();

        try {
            DiscoverItems itms = pubsub_man.discoverNodes(null);

            Iterator<DiscoverItems.Item> it = itms.getItems();

            for (; it.hasNext();) {
                entries.add(it.next().getNode());
            }

        } catch (XMPPException e) {
            e.printStackTrace();
        }

        return entries;
    }

    /**
	* Durch einen Node veröffentlichen
	*
	* @param node_id Node-id
	* @param payload_data Nutzdaten welche veröffentlicht werden sollen
	* @return erfolgreich oder fehlgeschlagen
    * @throws XMPPException 
	*/
    @SuppressWarnings("unchecked")
	public boolean publishWithPayload(String nodeName, String news) {

        LeafNode node = null;
        
        if(news.length() == 0) {
            System.err.println("Keine Daten eingegeben!");
            return false;
        }
        
        try {
            node = pubsub_man.getNode(nodeName);
        } catch (XMPPException e) {

            System.err.println("Node nicht gefunden!");

            if (e.getXMPPError().getCode() == 404) {

                try {
                    node = pubsub_man.createNode(nodeName);
                    node.sendConfigurationForm(createForm(FormType.submit, false, true, PublishModel.open, AccessModel.open));

                } catch (XMPPException e1) {
                    System.err.println("Node konnte nicht erstellt werden!");
                    return false;
                }
            }
        }

        if (node != null) {

        	// Datum und Uhrzeit
            GregorianCalendar gCalendar = new GregorianCalendar();
            Date currentDate = new Date();
            gCalendar.setTime(currentDate);
            XMLGregorianCalendar xmlCalendar = null;
            try {
            	xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
            } catch (DatatypeConfigurationException ex) {
            	
            }
            
		node.publish(new PayloadItem(nodeName + System.currentTimeMillis(), 
					          new SimplePayload("notification", "",
					            	    "<notification>" +
					                    "<datum>"+xmlCalendar+"</datum>"+
					                    "<verfasser>"+getUsername()+"</verfasser>" +
					                    "<topic>"+nodeName+"</topic>" +
					                    "<nachricht>"+news+"</nachricht>"+
					                    "</notification>")));
        }

        return true;
    }

    /**
	* Topic abonnieren
	*
	* @param nodeName Name der Node
	* @return erfolgreich oder fehlgeschlagen
	*/
    public boolean subscribeToNode(String nodeName) {
        LeafNode node = null;

        try {
            node = pubsub_man.getNode(nodeName);
            node.subscribe(this.username + "@" + this.hostname);
            node.addItemEventListener(listener);
        } catch (XMPPException e) {

            System.err.println("Node nicht gefunden! Neuer Node wird angelegt.");

            if (e.getXMPPError().getCode() == 404) {

                try {
                    node = pubsub_man.createNode(nodeName);
                    node.sendConfigurationForm(createForm(FormType.submit, false, true, PublishModel.open, AccessModel.open));
                    node.addItemEventListener(listener);
                    return true;
                } catch (XMPPException e1) {
                    System.err.println("Node konnte nicht erstellt werden!");
                    return false;
                }
            }
            else {
                System.err.println("Unbekannter Errorcode: " + e.getXMPPError().getCode());
                return false;
            }
        }
        
        return true;
    }

    /**
	* Topic abbestellen
	*
	* @param nodeName Name der Node
	* @return erfolgreich oder fehlgeschlagen
	*/
    public boolean unsubscribeToNode(String nodeName) {
        LeafNode node = null;

        try {
            node = pubsub_man.getNode(nodeName);
            node.unsubscribe(this.username + "@" + this.hostname);
            node.removeItemEventListener(listener);

            System.out.println("Abo abbestellen erfolgreich!");

        } catch (XMPPException e) {
            System.err.println("Abo abbestellen fehlgeschlagen!");
            return false;
        }
        
        return true;
    }

    /**
	* Node löschen
	*
	* @param node_id Node-id
	* @return erfolgreich oder fehlgeschlagen
	*/
    public boolean deleteNode(String nodeName) {
        
        try {
            pubsub_man.deleteNode(nodeName);
        } catch (XMPPException e) {
            System.err.println("Node mit dem Namen \""+ nodeName + "\" konnte nicht gelöscht werden!");
            return false;
        }

        return true;
    }

    /**
	* Liste mit IDs aller Nodes die ein Benutzer abonniert hat
	*
	* @return Node-Liste
	*/
    public List<String> getSubscribedNodes() {

        List<String> entries = new ArrayList<String>();

        try {
            List<Subscription> subs = pubsub_man.getSubscriptions();

            for (Subscription curr : subs) {
                entries.add(curr.getNode());
            }

        } catch (XMPPException e) {
            e.printStackTrace();
        }

        return entries;

    }

    /**
	*
	* @param nodeName Name der Node
	* @return Node Information als String
	*/
    public String getNodeInformation(String nodeName) {

        String info = "";

        ServiceDiscoveryManager discoManager = ServiceDiscoveryManager.getInstanceFor(xmpp_conn);

        DiscoverInfo discoInfo;
        try {
            discoInfo = discoManager.discoverInfo("pubsub." + xmpp_conn.getHost(), nodeName);

            Iterator<Identity> it = discoInfo.getIdentities();

            while (it.hasNext()) {
                DiscoverInfo.Identity identity = (DiscoverInfo.Identity) it.next();
                info += "Name:\t" + identity.getName() + "\n" + "Type:\t"+ identity.getType() + "\n" + "Kategorie:\t"+ identity.getCategory() + "\n";

                LeafNode node = pubsub_man.getNode(nodeName);

                List<Subscription> subs = node.getSubscriptions();

                if (subs.size() > 0) {
                    info += "Beschreibung:\n";

                    for (Subscription curr : subs) {
                        info += " " + curr.toXML() + "\n";
                    }

                    info += "\n";
                }
            }

        } catch (XMPPException e) {
            e.printStackTrace();
        }

        return info;

    }

    /**
	* Listener aller Nodes die ein Benutzer abonniert hat
	*
	*/
    private void attachListenerToSubscribedNodes() {

        List<Subscription> subs;
        try {
            subs = pubsub_man.getSubscriptions();
        } catch (XMPPException e1) {
            System.err.println("Abonnements konnte nicht bezogen werden!");
            e1.printStackTrace();
            return;
        }
        
        for (Subscription curr : subs) {
            try {
                pubsub_man.getNode(curr.getNode()).addItemEventListener(listener);
            } catch (XMPPException e) {
                System.err.println("Node nicht gefunden, um Listener anzufügen!");
            }
        }
    }

    /**
	* Fügt einen Listener hinzu der alle eingehenden Nachrichten ausgibt
	*
	* @param listener 
	*/
    public void addItemListener(ItemEventListener<Item> listener) {
        this.listener = listener;
        attachListenerToSubscribedNodes();
    }

    /**
	* Gibt den Usernamen, des aktuell angemeldeten Benutzers zurück
	*
	* @return username
	*/
    public String getUsername() {
        return xmpp_conn.getUser();
    }
    
    /**
	* Gibt den Hostnamen zurück
	*
	* @return hostname
	*/
    public String getHost() {
        return xmpp_conn.getHost();
    }
    
    /**
	* Trennt die Verbindung zum XMPP Server
	*
	*/
    public void disconnect() {
        Presence offline = new Presence(Presence.Type.unavailable, "", 1, Presence.Mode.away);
        xmpp_conn.sendPacket(offline);
        xmpp_conn.disconnect();
    }

    /**
	* Erstellt ConfigureForm
	*
	* @return configureform
	*/
    private ConfigureForm createForm(FormType type, boolean pers, boolean payload, PublishModel pm, AccessModel am) {
        ConfigureForm form = new ConfigureForm(type);
        form.setPersistentItems(pers);
        form.setDeliverPayloads(payload);
        form.setPublishModel(pm);
        form.setAccessModel(am);

        return form;
    }

    public void finalize() {
        disconnect();
    }
}
