package client;

import java.math.BigInteger;
import java.util.Scanner;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import dozenten.Dozent;
import dozenten.CtAdresse;
import dozenten.ObjectFactory;
import dozenten.CtLehre;
import dozenten.CtLehre.Veranstaltungen;

public class DozentTestClient {
	
	public static void main(String[] args) {
		int auswahl = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("1:Dozent erstellen");
		System.out.println("2:Dozent löschen");
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
				dozent.getAdresse().getRaumnr().setNummer(BigInteger.valueOf(1111));
				dozent.getAdresse().getEmail().setAdresse("mustermann at max.de");
				dozent.setLehre(new CtLehre());
				dozent.getLehre().setLehrgebiet("Muster");
				dozent.getLehre().setUrl("http://www.gm.fh-koeln.de/~mustermann");
				dozent.getLehre().setVeranstaltungen(new Veranstaltungen());
				dozent.getLehre().getVeranstaltungen().getBezeichnung().add("Musterveranstaltung");
				
				ClientResponse response = webResource.accept("MediaType.APPLICATION_XML").post(ClientResponse.class, dozent);
				
				if (response.getStatus() != 201) {
					throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
				}
				
				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				System.out.println(output);

				
			} catch (Exception e) {

				e.printStackTrace();
			
			}
		}else if (auswahl == 2) {
			System.out.println("id eingeben:");
			String id = in.nextLine();
			try {
				Client client = Client.create();
				WebResource webResource = client.resource("http://localhost:4434/dozent/"+id+"/delete");
	
				ClientResponse response = webResource.accept("MediaType.APPLICATION_XML").delete(ClientResponse.class);
	
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
