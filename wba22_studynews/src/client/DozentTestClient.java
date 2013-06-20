package client;

import java.math.BigInteger;
import java.util.Scanner;

import jaxb.dozenten.CtAdresse;
import jaxb.dozenten.CtLehre;
import jaxb.dozenten.Dozent;
import jaxb.dozenten.ObjectFactory;
import jaxb.dozenten.CtLehre.Veranstaltungen;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
				
				System.out.println("Geben Sie Ihren Vornamen ein:");
				String vorname = in.nextLine();
				System.out.println("Geben Sie Ihren Nachnamen ein:");
				String nachname = in.nextLine();
				dozent.setNachname(nachname);
				dozent.setVorname(vorname);
				dozent.setAdresse(new CtAdresse());
				dozent.getAdresse().setAnschrift("Steinmüllerallee 1, 51643 Gummersbach");
				System.out.println("Geben Sie Ihre Raumnummer ein:");
				int raumnr = in.nextInt();
				dozent.getAdresse().setRaum(BigInteger.valueOf(raumnr));
				System.out.println("Geben Sie Ihre Telefonnummer ein:");
				int telnr = in.nextInt();
				dozent.getAdresse().setTel(BigInteger.valueOf(telnr));
				dozent.getAdresse().setEmail(nachname+" at gm.fh-koeln.de");
				dozent.setLehre(new CtLehre());
				System.out.println("Geben Sie Ihr Lehrgebiet ein:");
				String lehrgebiet = in.nextLine();
				dozent.getLehre().setLehrgebiet(lehrgebiet);
				dozent.getLehre().setUrl("http://www.gm.fh-koeln.de/~"+nachname);
				dozent.getLehre().setVeranstaltungen(new Veranstaltungen());
				
				
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
				   throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
				}
		 
				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
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
				   throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
				}
		 
				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				System.out.println(output);
		 
			  } catch (Exception e) {
		 
				e.printStackTrace();
		 
			  }
		} 


		
	}

}
