package client;


import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import javax.xml.bind.Unmarshaller;

import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import jaxb.Ressource;
import jaxb.payload.Notification;

@SuppressWarnings("rawtypes")
public class ItemEventCoordinator extends Ressource implements ItemEventListener {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void handlePublishedItems(ItemPublishEvent items) {
		System.out.println("Item count: " + items.getItems().size());
		Collection<? extends Item> itemss = items.getItems();
        for (Item item : itemss) {
                  PayloadItem pi = (PayloadItem) item;
                  
                  JAXBContext jc;
				try {
					jc = JAXBContext.newInstance(Notification.class);
					Unmarshaller unmarshaller = jc.createUnmarshaller();

					String payloadXml = pi.getPayload().toXML();
					StringReader reader = new StringReader(payloadXml);
					Notification notify = (Notification) unmarshaller.unmarshal(reader);
					System.out.println("Neue Benachrichtigung:");
					System.out.println("Datum: "+ notify.getDatum());
					System.out.println("Verfasser: "+notify.getVerfasser());
					System.out.println("Topic" + notify.getTopic());
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
