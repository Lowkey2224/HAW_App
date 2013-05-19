package com.example.haw_app.socialfeatures.interfaces;

import org.jivesoftware.smack.XMPPConnection;

import com.example.haw_app.ChatActivity;

/**
 * Schnittstelle für das Social Features.
 * Ermöglicht Zugriff und den Chat
 * @version 0.5
 * @author Aria Rafi Nazari
 */

public interface IChat {
	
	public void setChatActivity(ChatActivity chatActivity);

	public void sendMessage(String to, String text);

	public void setConnection(XMPPConnection connection);
	
	public void chatDisconnect();

	public void prepareConnection();
	
	public void connect(final String userName,final String password);
	
	public void registerUser(final String userName, final String password);

}
