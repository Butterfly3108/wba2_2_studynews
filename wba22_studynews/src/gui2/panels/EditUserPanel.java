package gui2.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.StringReader;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.ScrollPaneConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import gui.Application;
import gui2.ClientMain;
import jaxb.userDatabase.UserDatabase.Eintrag;
import jaxb.userDatabase.UserDatabase;
import xmpp.ConnectionHandler;
import xmpp2.PubsubClient;

public class EditUserPanel extends JPanel {
	
	private ClientMain app;
	private ConnectionHandler ch;
	private Eintrag user = new Eintrag();
	
	private JTextField vornameField;
	private JTextField nachnameField;
	private JTextField statusField;
	private DefaultListModel userListData;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 489852620191460633L;

	public EditUserPanel(ClientMain app, ConnectionHandler ch, Eintrag user) {
		this.app = app;
		this.ch = ch;
		this.user = user;
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton changeBtn = new JButton("Aktualisieren");
		JButton btnMainMenu = new JButton("Main Menu");
		JLabel vornameLabel = new JLabel("Vorname");
		JLabel nachnameLabel = new JLabel("Nachname");
		JLabel statusLabel = new JLabel("Status");
		
		vornameField = new JTextField();
		nachnameField = new JTextField();
		statusField = new JTextField();				

		try {
			loadProfile();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.anchor= GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1; gbc.weighty = 1;
		gbc.gridx = 0; gbc.gridy = 0;
		
		panel.add(vornameLabel, gbc);
		gbc.gridx++;
		panel.add(vornameField, gbc);
		gbc.gridx--;
		gbc.gridy++;
		panel.add(nachnameLabel, gbc);
		gbc.gridx++;
		panel.add(nachnameField, gbc);
		gbc.gridx--;
		gbc.gridy++;
		panel.add(statusLabel, gbc);
		gbc.gridx++;
		panel.add(statusField, gbc);
		
		gbc.gridy++;
		gbc.gridx--;
		
		panel.add(changeBtn, gbc);
		gbc.gridx++;
		panel.add(btnMainMenu, gbc);
		
		changeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				safeProfile();
			}
		});
		
		btnMainMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				app.changePanel(1, 0);
			}
		});
		
	}

	protected void safeProfile() {
		user.setVorname(vornameField.getText());
		user.setNachname(nachnameField.getText());
		user.setStatus(statusField.getText());
		
		Client client = Client.create();
		
		try { 
			WebResource webResource = client
			   .resource("http://localhost:4434/user/"+user.getId().toString()+"/edit/");
			
			ClientResponse response = webResource.accept("application/xml")
					.put(ClientResponse.class, user);
	 
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
			showPopup(response.getEntity(String.class));
		} catch (Exception e) {
				 
			e.printStackTrace();
			 
		} finally {
			
		}
		
		
	}

	private void loadProfile() throws JAXBException {

		vornameField.setText(user.getVorname());
		nachnameField.setText(user.getNachname());
		statusField.setText(user.getStatus());
		
	}
	
	private void showPopup(String status) {
		int x = getSize().width / 2;
	    int y = getSize().height / 2;
		JPanel popupPanel = new JPanel(new BorderLayout());
		JLabel statusLabel = new JLabel(status);
		JButton popupButton = new JButton("Schliessen");
		popupPanel.add(statusLabel, BorderLayout.CENTER);
		popupPanel.add(popupButton, BorderLayout.SOUTH);
		PopupFactory factory = PopupFactory.getSharedInstance();
		final Popup popup = factory.getPopup(new JFrame(), popupPanel, x, y);
		popup.show();
		
		popupButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				popup.hide();
				app.changePanel(1, 0);
			}
		});
	}
	
	public void refresh() {
		try {
			loadProfile();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
