package com.example.haw_app.socialfeatures.interfaces;

/**
 * Schnittstelle f�r das Social Features.
 * Erm�glicht Zugriff und eintragen von Praktikas
 * @version 0.1
 * @author Aria Rafi Nazari
 */

public interface IPraktika {
	/**
	 * Erzeugt einen neuen Partner ohne Daten bis auf Matrikelnummer.
	 * Die Partner sind eindeutig unter den Matrikelnummer unterscheidbar.
	 * Daf�r wird ein Hashmap genutzt und key ist die matNr.
	 * @param matNr
	 * @return boolean true wenn es ein neuen Partner erzeugt wurde
	 */
	public boolean createPartner(String matNr);
	

	/**
	 * L�scht einen Partner eindeutig nach dem Matrikelnummer.
	 * @param matNr
	 * @return
	 */
	public boolean deletePartner(String matNr);
	
	/**
	 * Gibt den Vorlesungzur�ck
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
	 * Gibt den Namen des Professors zur�ck
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
	 * Gibt den Gruppen Nummer des Praktikas zur�ck
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
	 * Gibt den Status des Praktikas zur�ck
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
