package gui2.panels;

import javax.swing.JPanel;

import gui.Application;
import gui2.ClientMain;
import jaxb.userDatabase.UserDatabase.Eintrag;
import jaxb.userDatabase.UserDatabase;
import xmpp.ConnectionHandler;
import xmpp2.PubsubClient;

public class PostNewsPanel extends JPanel {
	
	private ClientMain app;
	private ConnectionHandler ch;
	private Eintrag user = new Eintrag();

	public PostNewsPanel(ClientMain app, ConnectionHandler ch, Eintrag user) {
		this.app = app;
		this.ch = ch;
		this.user = user;

		initUI();
	}
	
	private void initUI() {
		
	}

}
