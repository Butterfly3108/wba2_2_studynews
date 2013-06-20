package xmpp;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class ClientFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private ConnectionFrame parentFrame;
    
    private JPanel contentPane;
    private ConnectionHandler ch;
    private JTextField textNodeName;
    private JRadioButton rdbtnPublish;
    private JRadioButton rdbtnSubscribe;
    private JRadioButton rdbtnUnsubscribe;
    private JRadioButton rdbtnDelete;
    private JTextArea textPayload;
    private JTextPane textMessages;
    private JTextArea textNodeInformation;
    private JList listSubs;
    private JList listNodes;
    private final ButtonGroup actionType = new ButtonGroup();

    private DefaultListModel listmodel;
    private DefaultListModel listmodelsubs;
    private JLabel lblMessages;
    private JButton btnSend;
    private JButton btnUpdate;
    private JLabel labelUsername;
    private JButton btnLogout;

    public ClientFrame(ConnectionFrame parent) {
        setResizable(false);
        setVisible(true);
        setEnabled(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);

        parentFrame = parent;
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        listmodel = new DefaultListModel();

        contentPane.setLayout(null);

        listNodes = new JList(listmodel);
        listNodes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                int index = list.getSelectedIndex();

                if (index >= 0) {
                    listSubs.clearSelection();
                    textNodeName.setText((String) listmodel.get(index));
                    textNodeInformation.setText(ch.getNodeInformation((String) listmodel.get(index)));
                }
            }
        });
        listNodes.setBorder(new LineBorder(new Color(0, 0, 0)));
        listNodes.setBounds(636, 152, 104, 219);
        contentPane.add(listNodes);

        textPayload = new JTextArea();
        textPayload.setLineWrap(true);
        textPayload.setBounds(57, 220, 427, 151);
        
        JScrollPane payloadScroll = new JScrollPane(textPayload);
        payloadScroll.setBounds(57, 220, 427, 151);
        contentPane.add(payloadScroll);

        textNodeName = new JTextField();
        textNodeName.setBounds(57, 115, 104, 25);
        contentPane.add(textNodeName);
        textNodeName.setColumns(10);

        rdbtnPublish = new JRadioButton("Publish");
        actionType.add(rdbtnPublish);
        rdbtnPublish.setBounds(295, 116, 77, 23);
        contentPane.add(rdbtnPublish);

        rdbtnSubscribe = new JRadioButton("Subscribe");
        rdbtnSubscribe.setSelected(true);
        actionType.add(rdbtnSubscribe);
        rdbtnSubscribe.setBounds(170, 116, 104, 23);
        contentPane.add(rdbtnSubscribe);

        rdbtnUnsubscribe = new JRadioButton("Unsubscribe");
        actionType.add(rdbtnUnsubscribe);
        rdbtnUnsubscribe.setBounds(170, 152, 120, 23);
        contentPane.add(rdbtnUnsubscribe);

        rdbtnDelete = new JRadioButton("Delete");
        actionType.add(rdbtnDelete);
        rdbtnDelete.setBounds(295, 150, 77, 23);
        contentPane.add(rdbtnDelete);

        btnSend = new JButton("Senden");
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {

                if (!(textNodeName.getText().length() > 0))
                    return;

                if (rdbtnPublish.isSelected()) {
                    if (ch.publishWithPayload(textNodeName.getText(),
                            textPayload.getText())) {
                        System.out.println("Publishing succeded!");
                        refreshLists();
                    } else {
                        System.err.println("Publishing failed!");
                    }
                } else if (rdbtnSubscribe.isSelected()) {
                    if (ch.subscribeToNode(textNodeName.getText())) {
                        System.out.println("Subscribing succeded!");
                        refreshLists();
                    } else {
                        System.err.println("Subscribing failed!");
                    }
                } else if (rdbtnUnsubscribe.isSelected()) {
                    if (ch.unsubscribeToNode(textNodeName.getText())) {
                        System.out.println("Unsubscribing succeded!");
                        refreshLists();
                    } else {
                        System.err.println("Unsubscribing failed!");
                    }
                } else if (rdbtnDelete.isSelected()) {
                    if (ch.deleteNode(textNodeName.getText())) {
                        System.out.println("Deleting succeded!");
                        refreshLists();
                    } else {
                        System.err.println("Deleting failed!");
                    }
                }

            }
        });
        btnSend.setBounds(380, 113, 104, 25);
        contentPane.add(btnSend);

        JLabel lblPayload = new JLabel("Payload");
        lblPayload.setBounds(56, 193, 70, 15);
        contentPane.add(lblPayload);

        lblMessages = new JLabel("Empfangene Nachrichten");
        lblMessages.setBounds(57, 400, 200, 15);
        contentPane.add(lblMessages);

        JLabel lblAlle = new JLabel("Alle");
        lblAlle.setBounds(636, 125, 91, 15);
        contentPane.add(lblAlle);

        textMessages = new JTextPane();
        textMessages.setEditable(false);
        textMessages.setBounds(57, 427, 427, 99);
        
        JScrollPane messagesScroll = new JScrollPane(textMessages);
        messagesScroll.setBounds(57, 427, 427, 99);
        contentPane.add(messagesScroll);

        listmodelsubs = new DefaultListModel();

        listSubs = new JList(listmodelsubs);
        listSubs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                int index = list.getSelectedIndex();

                if (index >= 0) {
                    listNodes.clearSelection();
                    textNodeName.setText((String) listmodelsubs.get(index));
                    textNodeInformation.setText(ch.getNodeInformation((String) listmodelsubs.get(index)));
                }
            }
        });
        listSubs.setBorder(new LineBorder(new Color(0, 0, 0)));
        listSubs.setBounds(520, 153, 104, 218);
        contentPane.add(listSubs);

        JLabel lblAbboniert = new JLabel("Abonniert");
        lblAbboniert.setBounds(523, 125, 91, 15);
        contentPane.add(lblAbboniert);

        JLabel lblNodeId = new JLabel("Node ID");
        lblNodeId.setBounds(57, 88, 70, 15);
        contentPane.add(lblNodeId);

        JLabel lblNodeInformationen = new JLabel("Node Informationen");
        lblNodeInformationen.setBounds(523, 400, 200, 15);
        contentPane.add(lblNodeInformationen);

        textNodeInformation = new JTextArea();
        textNodeInformation.setEditable(false);
        textNodeInformation.setBounds(520, 427, 220, 99);

        JScrollPane nodeInformationScroll = new JScrollPane(textNodeInformation);
        nodeInformationScroll.setBounds(520, 427, 220, 99);
        contentPane.add(nodeInformationScroll);

        btnUpdate = new JButton("Aktu.");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                refreshLists();
            }
        });
        btnUpdate.setBounds(380, 149, 104, 25);
        contentPane.add(btnUpdate);
        
        JLabel lblAngemeldetAls = new JLabel("Angemeldet als:");
        lblAngemeldetAls.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAngemeldetAls.setBounds(620, 12, 120, 15);
        contentPane.add(lblAngemeldetAls);
        
        labelUsername = new JLabel("");
        labelUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        labelUsername.setBounds(380, 33, 360, 15);
        contentPane.add(labelUsername);
        
        btnLogout = new JButton("Abmelden");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ch.disconnect();
                setEnabled(false);
                parentFrame.setVisible(true);
                labelUsername.setText("");
                listmodel.clear();
                listmodelsubs.clear();
                textMessages.setText("");
                textNodeInformation.setText("");
                textNodeName.setText("");
                textPayload.setText("");
            }
        });
        btnLogout.setBounds(623, 53, 117, 25);
        contentPane.add(btnLogout);
    }

    private void refreshLists() {
        /* Fill listmodel with all existing nodes */
        List<String> entries = ch.getAllNodes();
        listmodel.clear();

        for (String curr : entries) {
            listmodel.addElement(curr);
        }

        /* Fill listmodelsubs with all subscribed nodes */
        List<String> subs_entries = ch.getSubscribedNodes();
        listmodelsubs.clear();

        for (String curr : subs_entries) {
            listmodelsubs.addElement(curr);
        }
    }

    public void setConnectionHandler(ConnectionHandler ch) {
        this.ch = ch;
        
        labelUsername.setText(ch.getUsername() + "@" + ch.getHost());
        
        refreshLists();
    }
}
