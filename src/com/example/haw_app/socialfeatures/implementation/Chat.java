package com.example.haw_app.socialfeatures.implementation;

//import java.util.ArrayList;
import java.util.Collection;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterListener;
//import org.jivesoftware.smack.Roster;
//import org.jivesoftware.smack.RosterEntry;
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
import com.example.haw_app.LoginActivity;
//import com.example.haw_app.socialfeatures.interfaces.IChat;

import android.os.AsyncTask;
import android.os.Handler;

//import android.util.Log;

public class Chat {// implements IChat {
	private static Chat instance = null;
	public static final String HOST = "ec2-176-34-76-54.eu-west-1.compute.amazonaws.com";
	public static final int PORT = 5222;
	public static final String SERVICE = "ec2-176-34-76-54.eu-west-1.compute.amazonaws.com";
	ChatActivity chatActivity = null;
	LoginActivity loginActivity = null;
	private XMPPConnection connection;
	private Handler mHandler = new Handler();

	// private ArrayList<String> contactList = new ArrayList<String>();

	// private Handler eHandler = new Handler();

	private Chat() {

	}

	public void setChatActivity(ChatActivity chatActivity) {
		this.chatActivity = chatActivity;
	}

	public void setLoginActivity(LoginActivity loginActivity) {
		this.loginActivity = loginActivity;
	}

	public void sendMessage(String to, String text) {
		to = to + "@" + SERVICE;
		Message msg = new Message(to, Message.Type.chat);
		msg.setBody(text);
		if (connection != null) {
			// Log.i("HAW_App Chat ", "Text SEND ");
			connection.sendPacket(msg);
			chatActivity.setMessage(StringUtils.parseBareAddress(connection
					.getUser()) + ":");
			chatActivity.setMessage(text);
			chatActivity.setListAdapter();
		}
	}

	public void sendError(int errorNr) {
		switch (errorNr) {
		case 0:
			loginActivity.setError("Connect fehlgeschlagen");
			break;
		case 1:
			loginActivity.setError("Login fehlgeschlagen");
			break;
		case 2:
			loginActivity.setError("Register fehlgeschlagen");
			break;
		}

		mHandler.post(new Runnable() {
			public void run() {
				loginActivity.errorText();
			}
		});
	}

	public void setConnection(XMPPConnection connection) {
		this.connection = connection;
		if (connection != null) {
			PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
			connection.addPacketListener(new PacketListener() {
				@Override
				public void processPacket(Packet packet) {
					// if (chatActivity != null) {
					Message message = (Message) packet;
					if (message.getBody() != null) {
						String fromName = StringUtils.parseBareAddress(message
								.getFrom());
						// Log.i("HAW_App Chat ",
						// "Text Recieved " + message.getBody()
						// + " from " + fromName);
						chatActivity.setMessage(fromName + ":");
						chatActivity.setMessage(message.getBody());
						// Add the incoming message to the list view
						mHandler.post(new Runnable() {
							public void run() {
								chatActivity.setListAdapter();
							}
						});
					}
					// }
				}
			}, filter);
		}
	}

	public void chatDisconnect() {
		try {
			if (connection != null)
				// Log.i("HAW_App Chat ", "DISCONNECT");
				connection.disconnect();
		} catch (Exception e) {

		}
	}

	public void prepareConnection() {
		if (connection == null) {
			ConnectionConfiguration connConfig = new ConnectionConfiguration(
					HOST, PORT, SERVICE);
			this.connection = new XMPPConnection(connConfig);
		}

	}

	public boolean isConnect() {
		return connection != null;

	}

	private class startConnectBackground extends AsyncTask<String, Void, Void> {
		@Override
		protected Void doInBackground(String... params) {
			String userName = params[0];
			String password = params[1];
			prepareConnection();
			try {
				connection.connect();
				// Log.i("HAW_App Chat connect ",
				// "Connected to " + connection.getHost());
			} catch (XMPPException ex) {
				// Log.e("HAW_App Chat connect ", "Failed to connect to "
				// + connection.getHost());
				// Log.e("HAW_App Chat connect", ex.toString());
				sendError(0);
			}
			try {
				// SASLAuthentication.supportSASLMechanism("PLAIN", 0);
				connection.login(userName, password);
				loginActivity.changeActivity();
				// Log.i("HAW_App Chat connect",
				// "Logged in as " + connection.getUser());
				// Set the status to available
				Presence presence = new Presence(Presence.Type.available);
				connection.sendPacket(presence);
				setConnection(connection);

			} catch (XMPPException ex) {
				// Log.e("HAW_App Chat connect", "Failed to log in as " +
				// userName);
				// Log.e("HAW_App Chat connect", ex.toString());
				sendError(1);
				setConnection(null);
			}
			return null;
		}
	}

	public void startConnect(String userName, String password) {
		startConnectBackground connectTask = new startConnectBackground();
		connectTask.execute(new String[] { userName, password });
	}

	private class registerUserBackground extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			String userName = params[0];
			String password = params[1];
			prepareConnection();

			try {
				connection.connect();
				// Log.i("HAW_App Chat register",
				// "Connected to " + connection.getHost());
			} catch (XMPPException ex) {
				// Log.e("HAW_App Chat register", "Failed to connect to "
				// + connection.getHost());
				// Log.e("HAW_App Chat register", ex.toString());
				sendError(0);
				setConnection(null);
			}

			try {
				AccountManager manager = new AccountManager(connection);
				manager.createAccount(userName, password);
				// Log.i("HAW App Chat register", "Account " + userName
				// + " registriert");
				connection.disconnect();
				startConnect(userName, password);
			} catch (XMPPException ex) {
				// Log.e("HAW_App Chat register", "Failed to register to "
				// + connection.getHost());
				// Log.e("HAW_App Chat register", ex.toString());
				sendError(2);
				setConnection(null);
			}
			return null;
		}
	}

	public void registerUser(String userName, String password) {
		registerUserBackground registerTask = new registerUserBackground();
		registerTask.execute(new String[] { userName, password });
	}

	private class ContactFetcher extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {

			Roster roster = connection.getRoster();
			for (RosterEntry entry : roster.getEntries()) {
				// if (roster.getPresence(entry.getUser()).isAvailable()) {
				// String[] fromUser = entry.getUser().split("@");
				// //contactList.add(fromUser[0]);
				// chatActivity.addContact(fromUser[0]);
				// }
				String[] fromUser = entry.getUser().split("@");
				if (roster.getPresence(entry.getUser()).isAvailable()) {
					chatActivity.addContact(fromUser[0]+"-(Online)");
				} else {
					chatActivity.addContact(fromUser[0]+"-(Offline)");
				}
			}
			// chatActivity.setContact(contactList);
			chatActivity.setContactAdapter();

			roster.addRosterListener(new RosterListener() {
				public void entriesAdded(Collection<String> addresses) {
					System.out.println("entriesAdded");
				}

				public void entriesDeleted(Collection<String> addresses) {
					System.out.println("entriesDeleted");
				}

				public void entriesUpdated(Collection<String> addresses) {
					System.out.println("entriesUpdated");
				}

				public void presenceChanged(Presence presence) {
					//System.out.println("Presence changed: "
					//		+ presence.getFrom() + " " + presence);
					String[] fromUser = presence.getFrom().split("@");
					if (presence.isAvailable()) {
						// chatActivity.addContact(fromUser[0]);
						chatActivity.changeContactOnline(fromUser[0]);
						chatActivity.setContactAdapter();
					} else {
						// chatActivity.removeContact(fromUser[0]);
						chatActivity.changeContactOffline(fromUser[0]);
						chatActivity.setContactAdapter();
					}

				}
			});

			// contactList.clear();
			//
			// Roster people = connection.getRoster();
			//
			// for (RosterEntry entry : people.getEntries()) {
			// //System.out.println("entry.getUser : " + entry.getUser());
			// //System.out.println("entry.getName() : " + entry.getName());
			// //System.out.println("people.getPresence(entry.getUser()) : " +
			// people.getPresence(entry.getUser()));
			// //System.out.println("people.getPresence(entry.getUser()).isAvailable() : "
			// + people.getPresence(entry.getUser()).isAvailable());
			// if (people.getPresence(entry.getUser()).isAvailable()){
			// String[] fromUser = entry.getUser().split("@");
			// contactList.add(fromUser[0]);
			// }
			// //String fromName =
			// StringUtils.parseBareAddress(entry.getUser());
			// //contactList.add(entry.getName());
			// //contactList.add(people.getPresence(entry.getUser()));
			//
			//
			// }
			// //System.out.println("contactList : " + contactList.toString());
			// chatActivity.setContact(contactList);
			// chatActivity.setContactAdapter();
			return null;
		}
	}

	public void startContact() {
		ContactFetcher contactTask = new ContactFetcher();
		contactTask.execute();
	}

	public static Chat getInstance() {
		if (instance == null) {
			instance = new Chat();
		}
		return instance;
	}
}