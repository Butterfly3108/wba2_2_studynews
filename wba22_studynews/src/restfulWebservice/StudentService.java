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
import jaxb.studenten.Student;
import jaxb.userDatabase.UserDatabase;
import jaxb.userDatabase.UserDatabase.Eintrag;

	@Path ("/student")
	public class StudentService extends Ressource {

		
		@GET
		@Produces(MediaType.APPLICATION_XML)
		public UserDatabase getAll() throws JAXBException, IOException
		{
			UserDatabase liste = new UserDatabase();
			liste = (UserDatabase) unmarshal(UserDatabase.class, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/userDatabase.xml");
				
				return liste;
		}
	
		
		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_XML)
		public Student getOne(@PathParam("id") BigInteger id) throws JAXBException, IOException
		{
			Student student = new Student();
			student = (Student) unmarshal(Student.class, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/student/"+id+".xml");
			
			return student;		
		}
		

		
		@POST
		@Consumes(MediaType.APPLICATION_XML)
		@Path("/add")
		public Response post(Student student) throws JAXBException, IOException {
			UserDatabase liste = getAll();
			int studentId = liste.getEintrag().get(liste.getEintrag().size()-1).getId().intValue()+1;
			
			student.setId(BigInteger.valueOf(studentId));	
			
			marshal(Student.class, student, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/student/"+studentId+".xml", "http://example.org/student student.xsd ");
			
			Eintrag dEintrag = new Eintrag();
			dEintrag.setId(BigInteger.valueOf(studentId));
			dEintrag.setNachname(student.getNachname());
			dEintrag.setVorname(student.getVorname());
			
			liste.getEintrag().add(dEintrag);
			
			marshal(UserDatabase.class, liste, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/userDatabase.xml", "");
			
			String result = "Student mit der id: "+student.getId()+" hinzugefügt";

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
			
			marshal(UserDatabase.class, liste, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/userDatabase.xml", "");
			
			File file = new File("/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/user/"+id+".xml");

			file.delete();
			result2 = id+".xml entfernt";
			
			return Response.noContent().entity(result).entity(result2).build();
		}
		
		
//		@PUT
//		@Consumes(MediaType.APPLICATION_XML)
//		@Path("{id}/abo")
//		public Response editStudent(@PathParam("id") BigInteger id, Student student) {
//			
//			
//			
//			return Response.status(201).build();
//		}
		
		
}
