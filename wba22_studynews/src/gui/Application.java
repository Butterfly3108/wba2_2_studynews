package gui;


import gui.panels.AllTopicsPanel;
import gui.panels.EditUserPanel;
import gui.panels.MainMenuPanel;
import gui.panels.NewModulPanel;
import gui.panels.NewUserPanel;
import gui.panels.PostNewsPanel;
import gui.panels.UserAbosPanel;
import gui.panels.ViewUsersPanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import jaxb.userDatabase.*;
import xmpp2.PubsubClient;

public class Application extends JFrame {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	
	private PubsubClient pubsub;
	private UserDatabase user;
	
	private MainMenuPanel mmPanel;
	private UserAbosPanel uaPanel;
	private EditUserPanel euPanel;
	private NewModulPanel nmPanel;
	private PostNewsPanel pnPanel;
	private AllTopicsPanel atPanel;
	private NewUserPanel nuPanel;
	private ViewUsersPanel vuPanel;
	
	
	private List<JPanel> panelList;
	
	public Application(PubsubClient pubsub) {
		this.pubsub = pubsub;
		user = new UserDatabase();
		try {
			loadProfile();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initUI();
	}
	
	private void initUI() {
	panelList = new ArrayList<JPanel>();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(800, 600);
	setTitle("Studynews System");
	
	mmPanel = new MainMenuPanel(this, pubsub, user);
	uaPanel = new UserAbosPanel(this, pubsub, user);
	euPanel = new EditUserPanel(this, pubsub, user);
	nmPanel = new NewModulPanel(this, pubsub, user);
	pnPanel = new PostNewsPanel(this, pubsub, user);
	atPanel = new AllTopicsPanel(this, pubsub, user);
	nuPanel = new NewUserPanel(this, pubsub, user);
	vuPanel = new ViewUsersPanel(this, pubsub, user);
	panelList.add(0, mmPanel); panelList.add(1, uaPanel);
	panelList.add(2, euPanel); panelList.add(3, nmPanel);
	panelList.add(4, pnPanel); panelList.add(5, atPanel);
	panelList.add(6, nuPanel); panelList.add(7, vuPanel);	
	getContentPane().add(panelList.get(0), BorderLayout.CENTER);
	
	}
	
	public void changePanel(int oldPanel, int newPanel) {
		panelList.get(oldPanel).setVisible(false);
		getContentPane().add(panelList.get(newPanel), BorderLayout.CENTER);
		panelList.get(newPanel).setVisible(true);
		
		if(newPanel == 0) {
			try {
				loadProfile();
				mmPanel.refreshProfile();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} //else
		
//		if(newPanel ==1) {
//		euPanel.refresh();
//		} else
//		
//		if(newPanel == 5) {
//		ntPanel.refreshItFields();
//		}
//		
//		if (newPanel == 7) {
//		try {
//		vtPanel.loadTicket(vtlPanel.getTicketId());
//		} catch (JAXBException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		}
//		}
	
	
	}
	
	public void loadProfile() throws JAXBException, IOException {
	
		if(user.getEintrag().size() != 0) {
			user.getEintrag().remove(0);
		}
		
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:4434/user/");
		// lets get the XML as a String
		ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
		String text = webResource.accept("application/xml").get(String.class);
		JAXBContext jc = JAXBContext.newInstance(UserDatabase.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		StringReader reader = new StringReader(text);
		UserDatabase userdb = (UserDatabase) unmarshaller.unmarshal(reader);
		
		for(int i = 0; i < userdb.getEintrag().size(); i++) {
			if(userdb.getEintrag().get(i).getNachname().toUpperCase().equalsIgnoreCase(pubsub.getUsername())) {
				user.getEintrag().add(userdb.getEintrag().get(i));
			}
		}
	
	}
	
	/**
	* @param args
	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				try {
					LoginWindow login = new LoginWindow();
					login.show();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}