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




import xmpp.ConnectionHandler;
import jaxb.Ressource;
import jaxb.dozenten.Dozent;
import jaxb.userDatabase.UserDatabase;
import jaxb.userDatabase.Eintrag;

	@Path ("/dozent")
	public class DozentService extends Ressource {

		private static String schemaLoc;
		
		@GET
		@Produces(MediaType.APPLICATION_XML)
		public UserDatabase getAll() throws JAXBException, IOException
		{
			UserDatabase userDatabase = new UserDatabase();
			userDatabase = (UserDatabase) unmarshal(UserDatabase.class, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/userDatabase.xml");
				
				return userDatabase;
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
			UserDatabase liste = getAll();
			int dozentId = liste.getEintrag().get(liste.getEintrag().size()-1).getId().intValue()+1;
			
			dozent.setId(BigInteger.valueOf(dozentId));	
			
			schemaLoc = "http://example.org/dozent ../xmlUxsd/dozent/dozent.xsd ";
			marshal(Dozent.class, dozent, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/dozent/"+dozentId+".xml", schemaLoc);
			
			ConnectionHandler ch = new ConnectionHandler();
			ch.createNewNode(dozent.getNachname());
			
			Eintrag dEintrag = new Eintrag();
			dEintrag.setId(BigInteger.valueOf(dozentId));
			dEintrag.setNachname(dozent.getNachname());
			dEintrag.setVorname(dozent.getVorname());
			
			liste.getEintrag().add(dEintrag);
			
			schemaLoc = "";
			marshal(UserDatabase.class, liste, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/userDatabase.xml", schemaLoc);
			
			String result = "Dozent mit der id: "+dozent.getId()+" hinzugefügt";

			return Response.status(201).entity(result).build();
		}
		
		
		@DELETE
		@Consumes(MediaType.APPLICATION_XML)
		@Path("/{id}/delete")
		public Response delete(@PathParam("id") BigInteger id) throws JAXBException, IOException {
			
			UserDatabase liste = getAll();
			
			String result = null;
			String result2 = null;
			
			for(int i = 0; i < liste.getEintrag().size(); i++) {
				if(liste.getEintrag().get(i).getId().equals(id) ) {
					liste.getEintrag().remove(i);
					result = "Eintrag in Liste entfernt";
				} else {
					result = "Eintrag in Liste nicht gefunden";
				}
			}
			
			schemaLoc = "";
			marshal(UserDatabase.class, liste, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/userDatabase.xml", schemaLoc);
			
			File file = new File("/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/user/"+id+".xml");

			file.delete();
			result2 = id+".xml entfernt";
			
			return Response.noContent().entity(result).entity(result2).build();
		}
		
//		@PUT
//		@Consumes(MediaType.APPLICATION_XML)
//		@Path("{id}/news")
//		public Response addNews(@PathParam("id") BigInteger id, Dozent newDozent) throws JAXBException, IOException {
//			Dozent dozent = getOne(id);
//			
//			newDozent.getNewsticker().getEintrag().get(0).setVerfasser(dozent.getTitel());
//			
//			dozent.getNewsticker().getEintrag().add(dozent.getNewsticker().getEintrag().size(), newDozent.getNewsticker().getEintrag().get(0));
//			
//			schemaLoc = "http://example.org/dozent ../xmlUxsd/dozent/dozent.xsd";
//			marshal(Dozent.class, dozent, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/dozent/"+id+".xml", schemaLoc);
//			
//			String result = "News gepostet!";
//			
//			return Response.status(201).entity(result).build();
//		}
		
		
//		@PUT
//		@Consumes(MediaType.APPLICATION_XML)
//		@Path("{id}/edit")
//		public Response editDozent(@PathParam("id") BigInteger id, Dozent newDozent) throws JAXBException, IOException {
//			
//			Dozent dozent = getOne(id);
//			
//			dozent.getLehre().getVeranstaltungen().getList().add(dozent.getLehre().getVeranstaltungen().getList().size(), newDozent.getLehre().getVeranstaltungen().getList().get(0));
//			
//			schemaLoc = "http://example.org/dozent ../xmlUxsd/dozent/dozent.xsd";
//			marshal(Dozent.class, dozent, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/dozent/"+id+".xml", schemaLoc);
//			
//			String result = "News gepostet!";
//			
//			return Response.status(201).entity(result).build();
//		}
		
		
}
