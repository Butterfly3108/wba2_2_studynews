package restfulWebservice;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import jaxb.Ressource;
import jaxb.dozenten.Dozent;
import jaxb.userDatabase.Eintrag;
import jaxb.userDatabase.UserDatabase;

@Path ("/user")
public class UserService extends Ressource {
	
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
	public Eintrag getOne(@PathParam("id") BigInteger id) throws JAXBException, IOException
	{
		UserDatabase liste = getAll();
		Eintrag user= new Eintrag();
		
		for(int i=0; i<liste.getEintrag().size(); i++){
			if(liste.getEintrag().get(i).getId().equals(i)) {
				user.setId(BigInteger.valueOf(i));
				user.setNachname(liste.getEintrag().get(i).getNachname());
				user.setNachname(liste.getEintrag().get(i).getNachname());
				user.setStatus(liste.getEintrag().get(i).getStatus());
			}
		}
		
		return user;		
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
		
		marshal(UserDatabase.class, liste, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/userDatabase.xml", "");
		
		File file = new File("/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/user/"+id+".xml");

		file.delete();
		result2 = id+".xml entfernt";
		
		return Response.noContent().entity(result).entity(result2).build();
	}

}
