package client;

import java.math.BigInteger;
import java.util.Scanner;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


import studenten.Student;
import studenten.CtAbos;
import studenten.CtDozent;
import studenten.CtModul;
import studenten.ObjectFactory;

public class StudentTestClient {
	
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
				WebResource webResource = client.resource("http://localhost:4434/student/add/");
				Student student = new ObjectFactory().createStudent();
				
				student.setName("Max Mustermann");
				student.setKennung("mi1212");
				student.setStudiengang("Medieninformatik");
				student.setSemester(BigInteger.valueOf(6));
				student.setEmail("max.mustermann at gmx.de");
				student.setAbos(new CtAbos());
				student.getAbos().setModule(new CtModul());
				student.getAbos().setDozent(new CtDozent());
				
				
				
				ClientResponse response = webResource.accept("application/xml").post(ClientResponse.class, student);
				
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
				WebResource webResource = client.resource("http://localhost:4434/student/"+id+"/delete");
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
				WebResource webResource = client.resource("http://localhost:4434/student/");
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
				WebResource webResource = client.resource("http://localhost:4434/student/"+id);		 
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
