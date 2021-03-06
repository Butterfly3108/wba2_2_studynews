package gui2;

import gui2.panels.AllTopicsPanel;
import gui2.panels.EditUserPanel;
import gui2.panels.MainMenuPanel;
import gui2.panels.NewModulPanel;
import gui2.panels.NewUserPanel;
import gui2.panels.PostNewsPanel;
import gui2.panels.UserAbosPanel;
import gui2.panels.ViewUsersPanel;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import xmpp.ConnectionHandler;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import jaxb.userDatabase.UserDatabase;
import jaxb.userDatabase.UserDatabase.Eintrag;
import gui2.ConnectionFrame;


public class ClientMain extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private ConnectionHandler ch;
	private Eintrag e= new Eintrag();
	
	
	private MainMenuPanel mmPanel;
	private UserAbosPanel uaPanel;
	private EditUserPanel euPanel;
	private NewModulPanel nmPanel;
	private PostNewsPanel pnPanel;
	private AllTopicsPanel atPanel;
	private NewUserPanel nuPanel;
	private ViewUsersPanel vuPanel;
	
	private List<JPanel> panelList;
	
	public ClientMain(ConnectionHandler ch) {
		
		
		this.ch = ch;
		
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
		
		
		mmPanel = new MainMenuPanel(this, ch, e);
		uaPanel = new UserAbosPanel(this, ch, e);
		euPanel = new EditUserPanel(this, ch, e);
		nmPanel = new NewModulPanel(this, ch, e);
		pnPanel = new PostNewsPanel(this, ch, e);
		atPanel = new AllTopicsPanel(this, ch, e);
		nuPanel = new NewUserPanel(this, ch, e);
		vuPanel = new ViewUsersPanel(this, ch, e);
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
			} 
		}
		
		public Eintrag loadProfile() throws JAXBException, IOException {
			
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:4434/user/");
			ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
			String output = response.getEntity(String.class);
			System.out.println(output);
			JAXBContext jc = JAXBContext.newInstance(UserDatabase.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			StringReader reader = new StringReader(output);
			UserDatabase userdb = (UserDatabase) unmarshaller.unmarshal(reader);
			System.out.println(userdb.getEintrag().size());
//			Client client = Client.create();
//			WebResource webResource = client.resource("http://localhost:4434/user/");
//			lets get the XML as a String
//			String text = webResource.accept("application/xml").get(String.class);
//			JAXBContext jc = JAXBContext.newInstance(UserDatabase.class);
//			Unmarshaller unmarshaller = jc.createUnmarshaller();
//			StringReader reader = new StringReader(text);
//			UserDatabase userdb = (UserDatabase) unmarshaller.unmarshal(reader);
			System.out.println(ch.getUsername());
			System.out.println(userdb.getEintrag().get(6).getNachname());
			System.out.println(userdb.getEintrag().get(6).getStatus());
			
			
			
			for(int i = 0; i < userdb.getEintrag().size(); i++) {
				if(userdb.getEintrag().get(i).getNachname().equalsIgnoreCase(ch.getUsername())) {
					System.out.println("status "+userdb.getEintrag().get(i).getStatus());
					e.setStatus(userdb.getEintrag().get(i).getStatus());
					e.setNachname(ch.getUsername());
					e.setVorname(userdb.getEintrag().get(i).getVorname());
					e.setId(BigInteger.valueOf(i));
					System.out.println(e.getStatus());
					System.out.println(e.getNachname());
					System.out.println(e.getVorname());
					System.out.println(e.getId());
				}
			}
		
			return e;
		}


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                try {
                    // set system L&F
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                } catch (UnsupportedLookAndFeelException e) {
                } catch (ClassNotFoundException e) {
                } catch (InstantiationException e) {
                } catch (IllegalAccessException e) {
                }

                new ConnectionFrame();
            }
        });

    }

}