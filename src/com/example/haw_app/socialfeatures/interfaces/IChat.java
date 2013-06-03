package com.example.haw_app.socialfeatures.interfaces;

import com.example.haw_app.ChatActivity;
import com.example.haw_app.LoginActivity;

/**
 * Schnittstelle für das Social Features.
 * Ermöglicht Zugriff und den Chat
 * @version 0.8 
 * @author Aria Rafi Nazari
 */

public interface IChat {
	
	/**
	 * Setzt den Activity vom Chat
	 * um Daten auf der UI anzuzeigen
	 * @param chatActivity
	 */
	public void setChatActivity(ChatActivity chatActivity);

	/**
	 * Setzt den Activity vom Login
	 * um Daten auf der UI anzuzeigen
	 * @param loginActivity
	 */
	public void setLoginActivity(LoginActivity loginActivity);

	/**
	 * Sendet eine Nachricht an Zielperson
	 * @param to Message an wen es gehen soll
	 * @param text Nachricht
	 */
	public void sendMessage(String to, String text);
	
	/**
	 * Beim start werden erstmal die Offlinemessage ausgegeben.
	 * Aufruf über die Activity.
	 */
	public void showOfflineMessages();

	/**
	 * Den Verbindung zum Server beenden
	 */
	public void chatDisconnect();

	/**
	 * Verbindung zum Server vorbereiten
	 */
	public void prepareConnection();

	/**
	 * Prüfen ob es eine Verbindung zum Server gib.
	 * Aber nicht ob der Nutzer schon eingeloggt ist.
	 * @return true falls Verbindung zum Server steht sonst false
	 */
	public boolean isConnect();

	/**
	 * Verbindet zum Server und loggt den Nutzer ein.
	 * @param userName
	 * @param password
	 */
	public void startConnect(String userName, String password);

	/**
	 * Verbindet zum Server, registiert den Nutzer ein und
	 * loggt sich dann ein.
	 * @param userName
	 * @param password
	 */
	public void registerUser(String userName, String password);

	/**
	 * Start der Kontakt liste und der aktualisierung.
	 */
	public void startContact();

}
