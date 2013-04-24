package com.example.haw_app.socialfeatures.interfaces;

/**
 * Schnittstelle für das Social Features.
 * Ermöglicht Zugriff und eintragen von Praktikas
 * @version 0.1
 * @author Aria Rafi Nazari
 */

public interface IPraktika {
	/**
	 * Erzeugt einen neuen Partner ohne Daten bis auf Matrikelnummer.
	 * Die Partner sind eindeutig unter den Matrikelnummer unterscheidbar.
	 * Dafür wird ein Hashmap genutzt und key ist die matNr.
	 * @param matNr
	 * @return boolean true wenn es ein neuen Partner erzeugt wurde
	 */
	public boolean createPartner(String matNr);
	

	/**
	 * Löscht einen Partner eindeutig nach dem Matrikelnummer.
	 * @param matNr
	 * @return
	 */
	public boolean deletePartner(String matNr);
	
	/**
	 * Gibt den Vorlesungzurück
	 * @return firstname
	 */
	public String getLecture();
	
	/**
	 * Vorlesung setzen
	 * @param lecture
	 * @return boolean Zeigt ob Vorlesung setzen funktioniert hat
	 */
	public boolean setLecture(String lecture);
	
	/**
	 * Gibt den Namen des Professors zurück
	 * @return profName
	 */
	public String getProfName();
	
	/**
	 * Namen des Professors setzen
	 * @param profName
	 * @return boolean Zeigt ob Namen des Professors setzen funktioniert hat
	 */
	public boolean setProfName(String profName);
	
	/**
	 * Gibt den Gruppen Nummer des Praktikas zurück
	 * @return groupNr
	 */
	public String getGroupNr();
	
	/**
	 * Gruppen Nummer des Praktikas setzen
	 * @param groupNr
	 * @return boolean Zeigt ob Gruppen Nummer des Praktikas setzen funktioniert hat
	 */
	public boolean setGroupNr(String groupNr);
	
	/**
	 * Gibt den Status des Praktikas zurück
	 * @return status
	 */
	public boolean getStatus();
	
	/**
	 * Status des Praktikassetzen
	 * @param status
	 * @return boolean Zeigt ob Status des Praktikas setzen funktioniert hat
	 */
	public boolean setStatus(Boolean status);
}
