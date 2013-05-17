package restfulWebservice;

import java.io.File;
import java.io.FileNotFoundException;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.research.ws.wadl.Response;

	import dozenten.Dozenten;
	import dozenten.Dozenten.Dozent;
	import dozentenliste.Dozentenliste;
	import dozenten.ObjectFactory;

	@Path ("/dozenten")
	public class DozentService {

	@GET
	@Produces("application/xml")
	public Dozentenliste getAll() throws JAXBException, FileNotFoundException
	{
		Dozentenliste dozentliste = new Dozentenliste();
		JAXBContext context = JAXBContext.newInstance(Dozentenliste.class);
		Unmarshaller um = context.createUnmarshaller();
		dozentliste = (Dozentenliste) um.unmarshal(new File("/Users/Butterfly/git/wba2_phase2/wba2-2/src/dozentenliste.xml"));
	
		return dozentliste;
	}

	@GET
	@Path("/dozent/{id}")
	@Produces("application/xml")
	public Dozenten getOne(@PathParam("id") int id) throws JAXBException, FileNotFoundException
	{
		ObjectFactory of = new ObjectFactory();
		Dozenten dozent = of.createDozenten();
		
		JAXBContext context = JAXBContext.newInstance(Dozenten.class);
		Unmarshaller um = context.createUnmarshaller();
		dozent = (Dozenten) um.unmarshal(new File("/Users/Butterfly/git/wba2_phase2/wba2-2/src/dozent1.xml"));
		Dozenten doz = of.createDozenten();
		doz.getDozent().add(dozent.getDozent().get(id-1));
	
		return doz;
	
	  }
	
	
	@POST
	@Path("/dozent/add")
	@Consumes("application/xml")
	public Response addDozent(Dozenten newDozent) throws JAXBException, FileNotFoundException
	{
		Dozentenliste dozenten = getAll();
		JAXBContext context = JAXBContext.newInstance(Dozentenliste.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		m.setProperty("jaxb.schemaLocation", "http://www.example.org/dozentliste dozentenliste.xsd");
		m.marshal(dozenten, new File("/src/dozentenliste.xml"));
		return null;
	}
	
	
}
