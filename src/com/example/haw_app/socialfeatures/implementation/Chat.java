package com.example.haw_app.socialfeatures.implementation;

//import java.util.ArrayList;
import java.util.Collection;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
//import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;

import com.example.haw_app.ChatActivity;
import com.example.haw_app.socialfeatures.interfaces.IChat;

import android.os.Handler;
import android.util.Log;

public class Chat implements IChat {
	//private Student si = null;
	private static Chat instance = null;
	public static final String HOST = "ec2-176-34-76-54.eu-west-1.compute.amazonaws.com";
	public static final int PORT = 5222;
	public static final String SERVICE = "ec2-176-34-76-54.eu-west-1.compute.amazonaws.com";
	ChatActivity chatActivity;
	private XMPPConnection connection;
	private Handler mHandler = new Handler();

	private Chat() {
		
	}
	
	public void setChatActivity(ChatActivity chatActivity){
		this.chatActivity = chatActivity;
	}

	public void sendMessage(String to, String text) {
		to = to + "@" + SERVICE;
		Message msg = new Message(to, Message.Type.chat);
		msg.setBody(text);
		if (connection != null) {
			//Log.i("HAW_App Chat ", "Text SEND ");
			connection.sendPacket(msg);
			chatActivity.setMessage(StringUtils.parseBareAddress(connection.getUser()) + ":");
			chatActivity.setMessage(text);
			chatActivity.setListAdapter();
		}
	}

	public void setConnection(XMPPConnection connection) {
		this.connection = connection;
		if (connection != null) {
			PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
			connection.addPacketListener(new PacketListener() {
				@Override
				public void processPacket(Packet packet) {
					Message message = (Message) packet;
					if (message.getBody() != null) {
						String fromName = StringUtils.parseBareAddress(message
								.getFrom());
						Log.i("HAW_App Chat ", "Text Recieved " + message.getBody()
								+ " from " + fromName );
						chatActivity.setMessage(fromName + ":");
						chatActivity.setMessage(message.getBody());
						// Add the incoming message to the list view
						mHandler.post(new Runnable() {
							public void run() {
								chatActivity.setListAdapter();
							}
						});
					}
				}
			}, filter);
		}
	}
	
	public void chatDisconnect() {
		try {
			if (connection != null)
				//Log.i("HAW_App Chat ", "DISCONNECT");
			connection.disconnect();
		} catch (Exception e) {

		}
	}

	public void prepareConnection(){
		ConnectionConfiguration connConfig = new ConnectionConfiguration(
				HOST, PORT, SERVICE);
		 this.connection = new XMPPConnection(connConfig);
		
	}
	
	public void connect(final String userName,final String password) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				prepareConnection();
				try {
					connection.connect();
					Log.i("HAW_App Chat ",
							"Connected to " + connection.getHost());
				} catch (XMPPException ex) {
					Log.e("HAW_App Chat ", "Failed to connect to "
							+ connection.getHost());
					Log.e("HAW_App Chat ", ex.toString());
				}
				try {
					// SASLAuthentication.supportSASLMechanism("PLAIN", 0);
					connection.login(userName, password);
					Log.i("HAW_App Chat ",
							"Logged in as " + connection.getUser());

					// Set the status to available
					Presence presence = new Presence(Presence.Type.available);
					connection.sendPacket(presence);
					setConnection(connection);

					Roster roster = connection.getRoster();
					Collection<RosterEntry> entries = roster.getEntries();
					for (RosterEntry entry : entries) {
						Log.d("HAW_App Chat ",
								"--------------------------------------");
						Log.d("HAW_App Chat ", "RosterEntry " + entry);
						Log.d("HAW_App Chat ",
								"User: " + entry.getUser());
						Log.d("HAW_App Chat ",
								"Name: " + entry.getName());
						Log.d("HAW_App Chat ",
								"Status: " + entry.getStatus());
						Log.d("HAW_App Chat ",
								"Type: " + entry.getType());
						Presence entryPresence = roster.getPresence(entry
								.getUser());

						Log.d("HAW_App Chat ", "Presence Status: "
								+ entryPresence.getStatus());
						Log.d("HAW_App Chat ", "Presence Type: "
								+ entryPresence.getType());
						Presence.Type type = entryPresence.getType();
						if (type == Presence.Type.available)
							Log.d("HAW_App Chat ", "Presence AVIALABLE");
						Log.d("HAW_App Chat ", "Presence : "
								+ entryPresence);

					}
				} catch (XMPPException ex) {
					Log.e("HAW_App Chat ", "Failed to log in as "
							+ userName);
					Log.e("HAW_App Chat ", ex.toString());
				}
			}
		});
		t.start();
	}
	
	public void registerUser(final String userName, final String password)
	{	 
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				prepareConnection();

				try {
					connection.connect();
					AccountManager manager = new AccountManager(connection);
					manager.createAccount(userName,password);
					connection.disconnect();
					Log.i("HAW App Chat",
							"Account " + userName + " registriert");
				} catch (XMPPException ex) {
					Log.e("HAW_App Chat ", "Failed to connect to "
							+ connection.getHost());
					Log.e("HAW_App Chat ", ex.toString());
				}
			}
		});
		t.start();
	}

	public static Chat getInstance() {
		if (instance == null) {
			instance = new Chat();
		}
		return instance;
	}	
}