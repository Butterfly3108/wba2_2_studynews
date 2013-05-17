package restfulWebservice;

import java.io.IOException;
import java.math.BigInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBException;
import javax.ws.rs.core.MediaType;

import jaxb.Ressource;
import dozenten.Dozent;
import dozentenliste.Dozentenliste;

	@Path ("/dozenten")
	public class DozentService extends Ressource {

		/*
		 * Alle Dozenten laden
		 * @see jaxb.Ressource#getAll()
		 */
		@GET
		@Produces(MediaType.APPLICATION_XML)
		public Dozentenliste getAll() throws JAXBException, IOException
		{
				Dozentenliste dozentenliste = new Dozentenliste();
				dozentenliste = (Dozentenliste) unmarshal(Dozentenliste.class, "dozentenliste.xml");
				return dozentenliste;
		}
	
		/*
		 * Einen Dozenten laden
		 */
		/*
		@GET
		@Path("{id}")
		@Produces(MediaType.APPLICATION_XML)
		public Dozent getOne(@PathParam("id") BigInteger id) throws JAXBException, IOException
		{
			Dozent dozent = new Dozent();
			dozent = (Dozent) unmarshal(Dozent.class, "dozent/"+id+".xml");
	
			return dozent;
		
		}
		*/	
}
