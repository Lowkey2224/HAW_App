package com.example.haw_app.socialfeatures.interfaces;

import java.util.List;

import com.example.haw_app.database.Database;

/**
 * Schnittstelle für das Social Features. Ermöglicht Zugriff und eintragen von
 * Praktikas
 * 
 * @version 0.5
 * @author Aria Rafi Nazari
 */

public interface IPraktika {

	/**
	 * Erzeugen eines neuen Praktikumpartner
	 * 
	 * @param matNr  Wichtig um eindeutig die Partner unterscheiden zu können.
	 * @param firstname
	 * @param surname
	 * @param email
	 * @param handy
	 * @param dbSF
	 * @return
	 */
	public boolean createPartner(String matNr, String firstname,
			String surname, String email, String handy,
			Database dbSF);

	/**
	 * Praktikumspartner die Daten ändern/updaten
	 * @param matNr
	 * @param firstname
	 * @param surname
	 * @param email
	 * @param handy
	 * @param dbSF
	 * @return
	 */
	public boolean updatePartner(String matNr, String firstname,
			String surname, String email, String handy,
			Database dbSF);

	/**
	 * Praktikumsparnter löschen
	 * @param matNr Jede Student hat eine eindeutige Nummer
	 * @param dbSF
	 * @return
	 */
	public boolean deletePartner(String matNr, Database dbSF);

	/**
	 * Beim App ausführung müssen die Praktikas und die ensprechende
	 * Praktikumspartner wieder neu eingetragen werden.
	 * @param dbSF
	 */
	public void getPartnerAll(Database dbSF);

	/**
	 * Gibt die Partner zurück mit den er die Praktika macht
	 * @param slecture
	 * @return
	 */
	public List<IPartner> getPartner(String slecture);

	/**
	 * Gibt den Vorlesungzurück
	 * 
	 * @return firstname
	 */
	public String getLecture();

	/**
	 * Gibt den Namen des Professors zurück
	 * 
	 * @return profName
	 */
	public String getProfName();

	/**
	 * Gibt den Gruppen Nummer des Praktikas zurück
	 * 
	 * @return groupNr
	 */
	public String getGroupNr();

	/**
	 * Gibt den Status des Praktikas zurück
	 * 
	 * @return status
	 */
	public String getStatus();
}
