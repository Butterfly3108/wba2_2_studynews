package gui2.panels;

import gui.Application;
import gui2.ClientMain;
import gui2.ConnectionHandler;

import javax.swing.JPanel;

import jaxb.userDatabase.Eintrag;
import jaxb.userDatabase.UserDatabase;
import xmpp2.PubsubClient;

public class AllTopicsPanel extends JPanel {
	
	private ClientMain app;
	private ConnectionHandler ch;
	private Eintrag user;

	public AllTopicsPanel(ClientMain app, ConnectionHandler ch, Eintrag user) {
		this.app = app;
		this.ch = ch;
		this.user = user;
		initUI();
	}
	
	private void initUI() {
		
	}

}
