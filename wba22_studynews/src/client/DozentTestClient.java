package client;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import dozenten.Dozent;
import dozenten.ObjectFactory;

public class DozentTestClient {
	
	public static void main(String[] args) {
		int auswahl = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("1:Ticket erstellen");
		System.out.println("2:Ticket löschen");
		auswahl = in.nextInt();
		in.nextLine();
		if(auswahl == 1) {
			/*
			try {

				Client client = Client.create();

				WebResource webResource = client
				.resource("http://localhost:4434/ticket/add/");

				Dozent ticket = new ObjectFactory().createDozent();
			}
*/
		}
	}

}
