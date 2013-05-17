package restfulWebservice;


import java.io.File;
import java.io.FileNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import dozenten.Dozenten;
import dozenten.Dozenten.Dozent;
import dozentenliste.Dozentenliste;
import dozenten.ObjectFactory;


@Path ("/dozenten")
public class DozentService {
	
	/*
	 * Alle Dozenten laden
	 */
	@GET
	@Path("/dozent")
	@Produces("application/xml")
	public Dozentenliste getAll() throws JAXBException, FileNotFoundException
	{
		JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller um = context.createUnmarshaller();
		Dozentenliste dozentenliste = (Dozentenliste) um.unmarshal(new File("/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/dozentenliste/dozentenliste.xml"));
		
		return dozentenliste;
	}
	
	/**Einen Dozent laden
	 * 
	 * @param id Eine ID des Dozenten der zurückgegeben werden soll
	 * @return Ein Dozent
	 */
	@GET
	@Path("/dozent/{id}")
	@Produces("application/xml")
	public Dozenten getOne(@PathParam("id") int id) throws JAXBException, FileNotFoundException
	{
		JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller um = context.createUnmarshaller();
		Dozenten dozenten = (Dozenten) um.unmarshal(new File("/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/dozenten/dozenten.xml"));
		
		Dozenten d = new Dozenten();
		d.getDozent().add(dozenten.getDozent().get(id-1));
		
		return d;
	}
}