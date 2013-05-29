package restfulWebservice;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import javax.ws.rs.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import module.Modul;
import modulliste.Modulliste;
import modulliste.StZustand;
import modulliste.Modulliste.MEintrag;
import jaxb.Ressource;


@Path ("/modul")
public class ModulService extends Ressource {

	private static String schemaLoc;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Modulliste getAll() throws JAXBException, IOException
	{
			Modulliste modulliste = new Modulliste();
			modulliste = (Modulliste) unmarshal(Modulliste.class, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/modulliste.xml");
			
			return modulliste;
	}

	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Modul getOne(@PathParam("id") BigInteger id) throws JAXBException, IOException
	{
		Modul modul = new Modul();
		modul = (Modul) unmarshal(Modul.class, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/modul/"+id+".xml");
		
		return modul;		
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/add")
	public Response post(Modul modul) throws JAXBException, IOException {
		Modulliste modulliste = getAll();
		int modulId = modulliste.getMEintrag().get(modulliste.getMEintrag().size()-1).getModulId().intValue()+1;
		
		modul.setId(BigInteger.valueOf(modulId));	
		
		schemaLoc = "http://example.org/modul ../xmlUxsd/module/modul.xsd ";
		marshal(Modul.class, modul, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/modul/"+modulId+".xml", schemaLoc);
		
		MEintrag mEintrag = new MEintrag();
		mEintrag.setModulId(BigInteger.valueOf(modulId));
		mEintrag.setZustand(StZustand.valueOf(modul.getZustand().toString()));
		mEintrag.setKuerzel(modul.getBezeichnung().getKuerzel());
		mEintrag.setBezeichnung(modul.getBezeichnung().getValue());
		
		modulliste.getMEintrag().add(mEintrag);
		
		schemaLoc = "http://example.org/modul ../xmlUxsd/modulliste.xsd";
		marshal(Modulliste.class, modulliste, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/modulliste.xml", schemaLoc);
		
		String result = "Dozent mit der id: "+modul.getId()+" hinzugef�gt";

		return Response.status(201).entity(result).build();
	}
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/{id}/delete")
	public Response delete(@PathParam("id") BigInteger id) throws JAXBException, IOException {
		
		Modulliste modulliste = getAll();
		
		String result = null;
		String result2 = null;
		
		for(int i = 0; i < modulliste.getMEintrag().size(); i++) {
			if(modulliste.getMEintrag().get(i).getModulId().equals(id) ) {
				modulliste.getMEintrag().remove(i);
				result = "Eintrag in Liste entfernt";
			} else {
				result = "Eintrag in Liste nicht gefunden";
			}
		}
		
		schemaLoc = "http://example.org/modul ../xmlUxsd/modulliste.xsd";
		marshal(Modulliste.class, modulliste, "/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/modulliste.xml", schemaLoc);
		
		File file = new File("/Users/Butterfly/git/wba22_studynews/wba22_studynews/src/xmlUxsd/modul/"+id+".xml");

		file.delete();
		result2 = id+".xml entfernt";
		
		return Response.noContent().entity(result).entity(result2).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}/set")
	public Response setStatus(@PathParam("id") BigInteger id, @QueryParam("status")String zustand) throws JAXBException, IOException {
		Modul modul = getOne(id);
		
		modul.setZustand(module.StZustand.fromValue(zustand));
		
		schemaLoc = "http://example.org/modul ../../schema/modul.xsd";
		marshal(Modul.class, modul, "modul/"+id+".xml", schemaLoc);
		
		Modulliste mList = getAll();
		
		System.out.println(modul.getZustand());
		
		for(int i = 0; i < mList.getMEintrag().size(); i++) {
			if(mList.getMEintrag().get(i).getModulId().equals(id))
				mList.getMEintrag().get(i).setZustand(StZustand.fromValue(zustand));
			}
		
		schemaLoc = "http://example.org/modul ../schema/modulliste.xsd";
		marshal(Modulliste.class, mList, "modulliste.xml", schemaLoc);
		
		return Response.status(201).build();
	}


}