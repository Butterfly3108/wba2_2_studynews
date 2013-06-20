package client;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.Affiliation;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;

import xmpp.ConnectionHandler;


public class NodeClient {

	/**
	* @param args
	* @throws XMPPException
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws XMPPException {
		
		Scanner in = new Scanner(System.in);
		ConnectionHandler ch = new ConnectionHandler();
		
		System.out.println("Benutzername: ");
		String username = in.nextLine();
		System.out.println("Passwort: ");
		String password = in.nextLine();
		
		if(username != null || password != null) {
			ch.connect("localhost", 5222);
			ch.login(username, password);
		} else
			System.out.println("Fehler!");
		
		String jid = username+"@localhost";

		LeafNode leaf;
		List<Affiliation> affiliations;
		int auswahl = 0;
		
		System.out.println("1: Node erstellen:");
		System.out.println("2: Nachrichten empfangen");
		System.out.println("3: Abonnierte Leafs anzeigen");
		System.out.println("4: unsubscribe Leafs");
		System.out.println("5: subscribe Leaf");
		System.out.println("6: nachricht senden");
		System.out.println("7: Items entfernen");
		
		auswahl = in.nextInt();
		in.nextLine();
		
		String nodeName;
		
		switch(auswahl) {
			case 1:
			System.out.println("name: ");
			nodeName = in.nextLine();
			
			ch.createNewNode(nodeName);
			
			break;
//			case 2:
//				affiliations = mgr.getAffiliations();
//				for(int i = 0; i < affiliations.size(); i++) {
//					System.out.println(affiliations.get(i).getNodeId());
//				}
//				System.out.println("wählen Sie aus:");
//				nodeName = in.nextLine();
//				leaf = mgr.getNode(nodeName);
//				
//				leaf.addItemEventListener(new ItemEventCoordinator());
//				leaf.subscribe(jid);
//				
//				try {
//					Thread.sleep( 1000 * 300);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
//			case 3:
//				affiliations = mgr.getAffiliations();
//				for(int i = 0; i < affiliations.size(); i++) {
//					System.out.println(affiliations.get(i).getNodeId());
//					leaf = mgr.getNode(affiliations.get(i).getNodeId());
//					System.out.println(leaf.getSubscriptions().get(0).getJid());
//				}
//				break;
//			case 4:
//				affiliations = mgr.getAffiliations();
//				for(int i = 0; i < affiliations.size(); i++) {
//					System.out.println(affiliations.get(i).getNodeId());
//				}
//				System.out.println("wählen Sie aus:");
//				nodeName = in.nextLine();
//				leaf = mgr.getNode(nodeName);
//				leaf.unsubscribe(jid);
//				break;
//			case 5:
//				System.out.println("Node wählen:");
//				nodeName = in.nextLine();
//				leaf = mgr.getNode(nodeName);
//				System.out.println(leaf.subscribe(jid).getId());
//				break;
//			case 6:
//				affiliations = mgr.getAffiliations();
//				for(int i = 0; i < affiliations.size(); i++) {
//					System.out.println(affiliations.get(i).getNodeId());
//				}
//				System.out.println("wählen Sie aus:");
//				nodeName = in.nextLine();
//				leaf = mgr.getNode(nodeName);
//				System.out.println("itemname:");
//				String itemName = in.nextLine();
//				// Datum und Uhrzeit
//				GregorianCalendar gCalendar = new GregorianCalendar();
//				Date currentDate = new Date();
//				gCalendar.setTime(currentDate);
//				XMLGregorianCalendar xmlCalendar = null;
//				try {
//					xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
//					} catch (DatatypeConfigurationException ex) {
//				}
//				leaf.publish(new PayloadItem(itemName + System.currentTimeMillis(), 
//				          new SimplePayload("notification", "", 
//				        		  "<notification><datum>"+xmlCalendar+"</datum><verfasser>Christian Noss</verfasser><topic>"+leaf.getId()+"</topic><nachricht>test</nachricht></notifications>")));
//				break;
//			case 7:
//				affiliations = mgr.getAffiliations();
//				for(int i = 0; i < affiliations.size(); i++) {
//					System.out.println(affiliations.get(i).getNodeId());
//					mgr.deleteNode(affiliations.get(i).getNodeId());
//				}
//			
		}
	}
}
