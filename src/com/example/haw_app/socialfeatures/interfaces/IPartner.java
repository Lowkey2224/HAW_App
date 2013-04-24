package com.example.haw_app.socialfeatures.interfaces;

/**
 * Schnittstelle für das Social Features.
 * Ermöglicht Zugriff und eintragen von Praktikumspartnern
 * @version 0.1
 * @author Aria Rafi Nazari
 */

public interface IPartner {
	
	public boolean create();
	public boolean update();
	public boolean delete();
	
	/**
	 * Gibt den Vorname zurück
	 * @return firstname
	 */
	public String getFirstname();
	
	/**
	 * Vorname setzen
	 * @param firstname
	 * @return boolean Zeigt ob Vorname setzen funktioniert hat
	 */
	public boolean setFirstname(String firstname);
	
	/**
	 * Gibt den Nachname zurück
	 * @return surname
	 */
	public String getSurname();
	
	/**
	 * Nachname setzen
	 * @param surname
	 * @return boolean Zeigt ob Nachname setzen funktioniert hat
	 */
	public boolean setSurname(String surname);
	
	/**
	 * Gibt den Matrikelnummer zurück
	 * @return matNr
	 */
	public String getMatNr();
	
	/**
	 * Matrikelnummer setzen
	 * @param matNr
	 * @return boolean Zeigt ob Matrikelnummer setzen funktioniert hat
	 */
	public boolean setMatNr(String matNr);
	
	/**
	 * Gibt den Matrikelnummer zurück
	 * @return email
	 */
	public String getEMail();
	
	/**
	 * EMail-Adresse setzen
	 * @param email
	 * @return boolean Zeigt ob  EMail-Adresse setzen funktioniert hat
	 */
	public boolean setEMail(String email);
	
	/**
	 * Gibt den Handynummer zurück
	 * @return handy
	 */
	public String getHandy();
	
	/**
	 * Handynummer setzen
	 * @param handy
	 * @return boolean Zeigt ob  Handynummer setzen funktioniert hat
	 */
	public boolean setHandy (String handy);
}
