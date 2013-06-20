package gui2.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import gui.Application;
import gui2.ClientMain;
import jaxb.userDatabase.UserDatabase.Eintrag;
import jaxb.userDatabase.UserDatabase;
import xmpp.ConnectionFrame;
import xmpp.ConnectionHandler;
import xmpp.ItemEventCoordinator;
import xmpp2.PubsubClient;

public class MainMenuPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ConnectionFrame parentFrame;
	
	private JLabel idField;
	private JLabel nameField;
	private JLabel userlevelField;
	
	private Eintrag user = new Eintrag();
	private ConnectionHandler ch;
	private ClientMain app;
	private JButton btnLogout;

	/**
	 * 
	 */
	

	public MainMenuPanel(ClientMain app, ConnectionHandler ch, Eintrag user) {
		this.user = user;
		this.ch = ch;
		initUI();
	}

	private void initUI() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel leftNaviPanel = createLeftNavi();
		
		JPanel profilePanel = loadProfilePanel();
		profilePanel.setBorder(BorderFactory.createTitledBorder("Profil"));
		profilePanel.setPreferredSize(new Dimension(400, 450));
		
		
		
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridheight = 6;
		add(leftNaviPanel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		//gbc.gridheight = 2;
		gbc.weightx = 1;
		//gbc.anchor = GridBagConstraints.NORTH;
		add(profilePanel, gbc);
		
	}
	
	private JPanel createLeftNavi() {
		JPanel panel = new JPanel();
		Dimension buttonDim = new Dimension(150,25);
		
		
		JButton btnUserAbos = new JButton("Abonnements");
		JButton btnEditUser = new JButton("Benutzer ändern");
		JButton btnAllTopics = new JButton("Topicliste");
		JButton btnEditProfile = new JButton("Profil ändern");
		JButton btnNewModul = new JButton("Neues Modul");
		JButton btnPostNews = new JButton("Neuigkeit");
		JButton btnTopics = new JButton("Erstellte Topics");
		JButton btnNewUser = new JButton("Neuer Benutzer");
		JButton btnViewUsers = new JButton("Benutzerliste");
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(8,16,8,16);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		
		switch(UserLevel.valueOf(user.getStatus().toUpperCase())) {
		case STUDENT:
			add(btnUserAbos,gbc);
			gbc.gridy++;
			add(btnEditProfile,gbc);
		case DOZENT:
			add(btnEditProfile,gbc);
			gbc.gridy++;
			add(btnTopics,gbc);
			gbc.gridy++;
			add(btnNewModul,gbc);
			gbc.gridy++;
			add(btnPostNews,gbc);
			break;
		case ADMIN:
			add(btnEditProfile,gbc);
			gbc.gridy++;
			add(btnNewModul,gbc);
			gbc.gridy++;
			add(btnViewUsers,gbc);
			gbc.gridy++;
			add(btnUserAbos,gbc);
			gbc.gridy++;
			add(btnNewUser,gbc);
			gbc.gridy++;
			add(btnTopics, gbc);
			gbc.gridy++;
			add(btnPostNews,gbc);
		}
		
		btnUserAbos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				app.changePanel(0, 1);
			}
		});
		
		btnNewModul.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				app.changePanel(0, 3);
			}
		});
		
		btnEditUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				app.changePanel(0, 2);
			}
		});
		
		btnAllTopics.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				app.changePanel(0, 5);
			}
		});
		
		btnPostNews.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				app.changePanel(0, 4);
			}
		});
		
		btnViewUsers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				app.changePanel(0, 7);
			}
		});
		
		btnNewUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				app.changePanel(0, 6);
			}
		});
		
		return panel;
	}

	private JPanel loadProfilePanel() {
		JPanel panel = new JPanel();
		
		JLabel idLabel = new JLabel("ID");
		JLabel nameLabel = new JLabel("Name:");
		JLabel userlevelLabel = new JLabel("Level:");
		
		idField = new JLabel("loading");
		nameField = new JLabel("loading");
		userlevelField = new JLabel("loading");
		
		refreshProfile();
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(2,8,2,8);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.2;
		gbc.weighty = 0.2;
		
		panel.add(idLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		panel.add(idField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(nameLabel, gbc);
		
		gbc.gridx = 1;
		panel.add(nameField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(userlevelLabel, gbc);
		
		gbc.gridx = 1;
		panel.add(userlevelField, gbc);

		return panel;
	}

//	btnLogout = new JButton("Abmelden");
//    btnLogout.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent ae) {
//            ch.disconnect();
//            setEnabled(false);
//            parentFrame.setVisible(true);
////            labelUsername.setText("");
////            listmodel.clear();
////            listmodelsubs.clear();
////            textMessages.setText("");
////            textNodeInformation.setText("");
////            textNodeName.setText("");
////            textPayload.setText("");
//        }
//    });
//    btnLogout.setBounds(623, 53, 117, 25);
//    contentPane.add(btnLogout);
//}
	
	public void refreshProfile() {
		idField.setText(String.valueOf(user.getId()));
		nameField.setText(user.getNachname());
		userlevelField.setText(user.getStatus().toString());
	}
	
	public enum UserLevel {
		STUDENT, DOZENT, ADMIN
	}
	


}
