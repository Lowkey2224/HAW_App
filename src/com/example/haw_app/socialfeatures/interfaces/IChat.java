package com.example.haw_app.socialfeatures.interfaces;

/**
 * Schnittstelle für das Social Features.
 * Ermöglicht Zugriff und den Chat
 * @version 0.1
 * @author Aria Rafi Nazari
 */

public interface IChat {
	
	public boolean startChat();
	public boolean sendMessage();//empfänger noch eintragen
	public boolean stopChat();
	public boolean showMessage();

}
