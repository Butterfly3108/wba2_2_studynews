package restfulWebservice;

import java.io.IOException;
import java.math.BigInteger;
import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
//import javax.ws.rs.core.MediaType;

import jaxb.Ressource;
import dozenten.Dozent;
import dozentenliste.Dozentenliste;
import dozentenliste.Dozentenliste.DEintrag;

	@Path ("/dozent")
	public class DozentService extends Ressource {

		
		@GET
		@Produces(MediaType.APPLICATION_XML)
		public Dozentenliste getAll() throws JAXBException, IOException
		{
				Dozentenliste dozentenliste = new Dozentenliste();
				dozentenliste = (Dozentenliste) unmarshal(Dozentenliste.class, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/dozentenliste.xml");
				
				return dozentenliste;
		}
	
		
		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_XML)
		public Dozent getOne(@PathParam("id") BigInteger id) throws JAXBException, IOException
		{
			Dozent dozent = new Dozent();
			dozent = (Dozent) unmarshal(Dozent.class, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/dozent/"+id+".xml");
			
			return dozent;		
		}
		

		
		@POST
		@Consumes(MediaType.APPLICATION_XML)
		@Path("/add")
		public Response post(Dozent dozent) throws JAXBException, IOException {
			Dozentenliste dozentenliste = getAll();
			int dozentId = dozentenliste.getDEintrag().get(dozentenliste.getDEintrag().size()-1).getDozentId().intValue()+1;
			
			dozent.setId(BigInteger.valueOf(dozentId));	
			
			marshal(Dozent.class, dozent, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/dozent/"+dozentId+".xml", "http://example.org/dozent dozent.xsd ");
			
			DEintrag dEintrag = new DEintrag();
			dEintrag.setDozentId(BigInteger.valueOf(dozentId));
			dEintrag.setName(dozent.getTitel());
			dEintrag.setLehrgebiet(dozent.getLehre().getLehrgebiet());
			
			dozentenliste.getDEintrag().add(dEintrag);
			
			marshal(Dozentenliste.class, dozentenliste, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/dozentenliste.xml", "http://example.org/ticket ../xmlUxsd/dozentenliste.xsd");
			
			String result = "Dozent mit der id: "+dozent.getId()+" hinzugefügt";

			return Response.status(201).entity(result).build();
		}
		
		
		@DELETE
		@Consumes(MediaType.APPLICATION_XML)
		@Path("/{id}/delete")
		public Response delete(@PathParam("id") BigInteger id) throws JAXBException, IOException {
			
			Dozentenliste dozentenliste = getAll();
			
			String result = null;
			String result2 = null;
			
			for(int i = 0; i < dozentenliste.getDEintrag().size(); i++) {
				if(dozentenliste.getDEintrag().get(i).getDozentId().equals(id) ) {
					dozentenliste.getDEintrag().remove(i);
					result = "Eintrag in Liste entfernt";
				} else {
					result = "Eintrag in Liste nicht gefunden";
				}
			}
			
			marshal(Dozentenliste.class, dozentenliste, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/dozentenliste.xml", "http://example.org/ticket ../xmlUxsd/dozentenliste.xsd");
			
			File file = new File("/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/dozent/"+id+".xml");

			file.delete();
			result2 = id+".xml entfernt";
			
			return Response.noContent().entity(result).entity(result2).build();
		}
		
		
		@PUT
		@Consumes(MediaType.APPLICATION_XML)
		@Path("{id}/edit")
		public Response editDozent(@PathParam("id") BigInteger id, Dozent dozent) {
			return Response.status(201).build();
		}
		
		
}
