package com.example.haw_app.socialfeatures.interfaces;

/**
 * Schnittstelle f�r das Social Features.
 * Erm�glicht Zugriff und den Chat
 * @version 0.1
 * @author Aria Rafi Nazari
 */

public interface IChat {
	
	public boolean startChat();
	public boolean sendMessage();//empf�nger noch eintragen
	public boolean stopChat();
	public boolean showMessage();

}
