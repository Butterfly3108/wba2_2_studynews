package client;

import java.math.BigInteger;
import java.util.Scanner;

import junit.framework.Assert;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import javax.ws.rs.core.MediaType;

import dozenten.Dozent;
import dozenten.CtAdresse;
import dozenten.ObjectFactory;
import dozenten.CtLehre;
import dozenten.CtLehre.Veranstaltungen;
import dozenten.CtNewsticker;
import dozenten.CtAbonnenten;

public class DozentTestClient {
	
	public static void main(String[] args) {
		int auswahl = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("1: Dozent erstellen");
		System.out.println("2: Dozent löschen");
		System.out.println("3: Alle Dozenten anzeigen");
		System.out.println("4: Einen Dozenten anzeigen");
		auswahl = in.nextInt();
		in.nextLine();
		if(auswahl == 1) {
			
			try {

				Client client = Client.create();

				WebResource webResource = client.resource("http://localhost:4434/dozent/add/");

				Dozent dozent = new ObjectFactory().createDozent();
				
				dozent.setTitel("Max Mustermann");
				dozent.setAdresse(new CtAdresse());
				dozent.getAdresse().setAnschrift("Musterstraße 1, 11111 Musterhausen");
				dozent.getAdresse().setRaum(BigInteger.valueOf(1111));
				dozent.getAdresse().setTel(BigInteger.valueOf(02261111111));
				dozent.getAdresse().setEmail("mustermann at gm.fh-koeln.de");
				dozent.setLehre(new CtLehre());
				dozent.getLehre().setLehrgebiet("Musterlehre");
				dozent.getLehre().setUrl("http://www.gm.fh-koeln.de/~mustermann");
				dozent.getLehre().setVeranstaltungen(new Veranstaltungen());
				dozent.setNewsticker(new CtNewsticker());
				dozent.setAbonnenten(new CtAbonnenten());
				
				
				ClientResponse response = webResource.accept("application/xml").post(ClientResponse.class, dozent);
				
				if (response.getStatus() != 201) {
					throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
				}
				
				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				System.out.println(output);

				
			} catch (Exception e) {

				e.printStackTrace();
			
			}
		} else if (auswahl == 2) {
			System.out.println("id eingeben:");
			String id = in.nextLine();
			try {
				Client client = Client.create();
				WebResource webResource = client.resource("http://localhost:4434/dozent/"+id+"/delete");
				ClientResponse response = webResource.accept("application/xml").delete(ClientResponse.class);
				
				if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
				}
	
			
				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				System.out.println(output);

			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
		} else if(auswahl == 3) {
			
			try {
				 
				Client client = Client.create();
		 
				WebResource webResource = client.resource("http://localhost:4434/dozent/");
		 
				ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
		 
				if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
				}
		 
				String output = response.getEntity(String.class);
		 
				System.out.println("Output from Server .... \n");
				System.out.println(output);
		 
			  } catch (Exception e) {
		 
				e.printStackTrace();
		 
			  }
		} else if(auswahl == 4) {
			
			try {
				System.out.println("id eingeben:");
				String id = in.nextLine();
				
				Client client = Client.create();
		 
				WebResource webResource = client.resource("http://localhost:4434/dozent/"+id);
		 
				ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
		 
				if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
				}
		 
				String output = response.getEntity(String.class);
		 
				System.out.println("Output from Server .... \n");
				System.out.println(output);
		 
			  } catch (Exception e) {
		 
				e.printStackTrace();
		 
			  }
		}
		
	}

}
