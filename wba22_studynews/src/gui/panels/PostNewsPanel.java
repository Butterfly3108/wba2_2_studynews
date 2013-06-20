package gui.panels;

import javax.swing.JPanel;

import gui.Application;
import jaxb.userDatabase.UserDatabase;
import xmpp2.PubsubClient;

public class PostNewsPanel extends JPanel {
	
	private Application app;
	private PubsubClient pubsub;
	private UserDatabase user;

	public PostNewsPanel(Application app, PubsubClient pubsub, UserDatabase user) {
		this.app = app;
		this.pubsub = pubsub;
		this.user = user;
		initUI();
	}
	
	private void initUI() {
		
	}

}
