package restfulWebservice;

import java.io.IOException;
import java.math.BigInteger;
import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
//import javax.ws.rs.core.MediaType;

import jaxb.Ressource;
import studenten.Student;
import studentenliste.Studentenliste;
import studentenliste.Studentenliste.DEintrag;

	@Path ("/student")
	public class StudentService extends Ressource {

		
		@GET
		@Produces(MediaType.APPLICATION_XML)
		public Studentenliste getAll() throws JAXBException, IOException
		{
			Studentenliste studentenliste = new Studentenliste();
			studentenliste = (Studentenliste) unmarshal(Studentenliste.class, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/studentenliste.xml");
				
				return studentenliste;
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
			Studentenliste studentliste = getAll();
			int studentId = studentliste.getDEintrag().get(studentliste.getDEintrag().size()-1).getStudentId().intValue()+1;
			
			student.setId(BigInteger.valueOf(studentId));	
			
			marshal(Student.class, student, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/student/"+studentId+".xml", "http://example.org/student student.xsd ");
			
			DEintrag dEintrag = new DEintrag();
			dEintrag.setStudentId(BigInteger.valueOf(studentId));
			dEintrag.setName(student.getName());
			
			studentliste.getDEintrag().add(dEintrag);
			
			marshal(Studentenliste.class, studentliste, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/studentliste.xml", "http://example.org/student ../xmlUxsd/studentliste.xsd");
			
			String result = "Student mit der id: "+student.getId()+" hinzugefügt";

			return Response.status(201).entity(result).build();
		}
		
		
		@DELETE
		@Consumes(MediaType.APPLICATION_XML)
		@Path("/{id}/delete")
		public Response delete(@PathParam("id") BigInteger id) throws JAXBException, IOException {
			
			Studentenliste studentliste = getAll();
			
			String result = null;
			String result2 = null;
			
			for(int i = 0; i < studentliste.getDEintrag().size(); i++) {
				if(studentliste.getDEintrag().get(i).getStudentId().equals(id) ) {
					studentliste.getDEintrag().remove(i);
					result = "Eintrag in Liste entfernt";
				} else {
					result = "Eintrag in Liste nicht gefunden";
				}
			}
			
			marshal(Studentenliste.class, studentliste, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/studentliste.xml", "http://example.org/student ../xmlUxsd/studentliste.xsd");
			
			File file = new File("/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/student/"+id+".xml");

			file.delete();
			result2 = id+".xml entfernt";
			
			return Response.noContent().entity(result).entity(result2).build();
		}
		
		
//		@PUT
//		@Consumes(MediaType.APPLICATION_XML)
//		@Path("{id}/edit")
//		public Response editStudent(@PathParam("id") BigInteger id, Student student) {
//			return Response.status(201).build();
//		}
		
		
}
