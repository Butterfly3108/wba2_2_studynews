package xmpp;

import java.io.IOException;
import java.io.StringReader;

//import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import jaxb.Ressource;
import jaxb.payload.Notification;


public class ItemEventCoordinator extends Ressource implements ItemEventListener<Item> {

//	@SuppressWarnings("unused")
//	private JTextField nodeName;
//
//    public ItemEventCoordinator(JTextField nodeName) {
//        this.nodeName = nodeName;
//    }
	
  public ItemEventCoordinator() {
  
}
	
	@SuppressWarnings("unchecked")
	@Override
	public void handlePublishedItems(ItemPublishEvent<Item> items) {
		System.out.println("Item count: " + items.getItems().size());
		for (Item item : items.getItems()) {
				PayloadItem<SimplePayload> pi = (PayloadItem<SimplePayload>) item;		                  
				
				try {
					JAXBContext jc = JAXBContext.newInstance(Notification.class);
					Unmarshaller unmarshaller = jc.createUnmarshaller();
					
					String payloadXml = pi.getPayload().toXML();
					StringReader reader = new StringReader(payloadXml);
					Notification notify = (Notification) unmarshaller.unmarshal(reader);
					System.out.println("Neue Nachricht!");
					System.out.println("Erstellungsdatum: "+ notify.getDatum());
					System.out.println("Verfasser: "+notify.getVerfasser());
					System.out.println("Topic:" + notify.getTopic());
					System.out.println("Nachricht: "+notify.getNachricht());
				
				
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          
			}
	}
	
	
	@Override
	public Object getAll() throws JAXBException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
