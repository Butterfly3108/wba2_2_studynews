package gui2;

import gui2.panels.MainMenuPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBException;

import xmpp.ClientFrame;
import xmpp.ConnectionHandler;
import xmpp.XMPPConfig;
import jaxb.userDatabase.UserDatabase;

public class ConnectionFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private MainMenuPanel mmp;
    private UserDatabase.Eintrag user;
    private ClientMain cm;

    private JPanel contentPane;
    private JTextField textHostname;
    private JTextField textPort;
    private JTextField textUsername;
    private JPasswordField passwordPassword;
    private JLabel lblStatus;

    public ConnectionFrame() {
//      setTitle("Anmelden");
//      setResizable(false);
//      setAlwaysOnTop(true);
//      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      setBounds(100, 100, 400, 260);
//      setLocationRelativeTo(null);
  	initUI();
  }
  	
  	private final void initUI() {
                
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblHostname = new JLabel("Hostname");
        lblHostname.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHostname.setBounds(80, 39, 82, 15);
        contentPane.add(lblHostname);

        JLabel lblPort = new JLabel("Port");
        lblPort.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPort.setBounds(80, 67, 82, 15);
        contentPane.add(lblPort);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUsername.setBounds(80, 95, 82, 15);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setBounds(80, 121, 82, 15);
        contentPane.add(lblPassword);

        textHostname = new JTextField();
        textHostname.setText(XMPPConfig.hostname);
        textHostname.setBounds(180, 34, 144, 25);
        contentPane.add(textHostname);
        textHostname.setColumns(10);

        textPort = new JTextField();
        textPort.setText(String.valueOf(XMPPConfig.port));
        textPort.setBounds(180, 61, 144, 25);
        contentPane.add(textPort);
        textPort.setColumns(10);

        textUsername = new JTextField();
        textUsername.setBounds(180, 88, 144, 25);
        contentPane.add(textUsername);
        textUsername.setColumns(10);

        passwordPassword = new JPasswordField();
        passwordPassword.setBounds(180, 115, 144, 25);
        contentPane.add(passwordPassword);

        JButton btnRegistrieren = new JButton("Registrieren");
        btnRegistrieren.addActionListener(new ActionListener() {

            @SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent e) {
                String hostname = textHostname.getText();
                int port = -1;
                String username = textUsername.getText();
                String password = passwordPassword.getText();

                try {
                    port = Integer.parseInt(textPort.getText());
                } catch (NumberFormatException e_num_form) {
                }

                if (hostname.length() > 3 && port > 0 && port <= 65536
                        && username.length() > 0 && password.length() > 0) {

                    ConnectionHandler ch = new ConnectionHandler();

                    if (ch.connect(hostname, port)) {

                        if (ch.register(username, password)) {
                            // success
                            System.out
                                    .println("Connection and Registration Success!");
                            lblStatus.setText("Erfolgreich registriert!");
                        } else {
                            lblStatus.setText("Registrierung fehlgeschlagen!");
                        }
                    } else {
                        // failed
                        lblStatus
                                .setText("Verbindungsherstellung fehlgeschlagen!");
                    }
                }

            }
        });

        btnRegistrieren.setBounds(60, 152, 104, 25);
        contentPane.add(btnRegistrieren);

        JButton btnVerbinden = new JButton("Verbinden");
        btnVerbinden.addActionListener(new ActionListener() {

            @SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent e) {
                String hostname = textHostname.getText();
                int port = -1;
                String username = textUsername.getText();
                String password = passwordPassword.getText();

                try {
                    port = Integer.parseInt(textPort.getText());
                } catch (NumberFormatException e_num_form) {
                }

                if (hostname.length() > 3 && port > 0 && port <= 65536
                        && username.length() > 0 && password.length() > 0) {

                    ConnectionHandler ch = new ConnectionHandler();

                    if (ch.connect(hostname, port)) {

                        if (ch.login(username, password)) {

                        	lblStatus.setForeground(Color.GREEN);
                        	lblStatus.setText("erfolgreich");
                        	dispose();
                        	ClientMain app = new ClientMain(ch);
                        	app.setVisible(true);
                        	
	                    } else {
	                    	lblStatus.setForeground(Color.RED);
	                    	lblStatus.setText("Anmeldung fehlgeschlagen");
	                    } 
                    }else {
                        // failed
                        lblStatus.setText("Verbindungsherstellung fehlgeschlagen!");
                    }
                }

            }
                    
        });

        btnVerbinden.setBounds(180, 152, 144, 25);
        contentPane.add(btnVerbinden);

        lblStatus = new JLabel("");
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setBounds(90, 185, 245, 25);
        contentPane.add(lblStatus);

        textPort.requestFocusInWindow();
        textPort.requestFocus();

        setVisible(true);
    }

}