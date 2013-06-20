package gui.panels;

import gui.Application;

import javax.swing.JPanel;

import jaxb.userDatabase.UserDatabase;
import xmpp2.PubsubClient;

public class UserAbosPanel extends JPanel {
	
	private Application app;
	private PubsubClient pubsub;
	private UserDatabase user;

	public UserAbosPanel(Application app, PubsubClient pubsub, UserDatabase user) {
		this.app = app;
		this.pubsub = pubsub;
		this.user = user;
		initUI();
	}
	
	private void initUI() {
		
	}

}
