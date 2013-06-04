package xmpp_client;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
 
public class TestClient {
	// Port und Adresse des Servers
	private String clientHost = "http://localhost";
	private int clientPort = 5222;
	// Name und Passwort fuer Authentifizierung
	private String username = "username";
	private String password = "password";
	// Adresse des Empfängers
	private String userTo = "username to” + ”@" + clientHost;
	// Nachrichten Text
	private String message = "Nachrichten Text";
 
	public void sendMessage() {
		try {
			ConnectionConfiguration config = new ConnectionConfiguration(clientHost, clientPort);
			config.setCompressionEnabled(true); 
			config.setSASLAuthenticationEnabled(true); 
            // Verbindunsaufbau zu dem Server
			Connection connection = new XMPPConnection(config);
			connection.connect();
                        
			connection.login(username, password);
			// Chat Erstellung
			Chat chat = connection.getChatManager().createChat(userTo, null);
			
			chat.sendMessage(message);
	
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	public static void main(String[] args) {
		TestClient client = new TestClient();
		client.sendMessage();
	}
}