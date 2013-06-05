package com.example.haw_app.socialfeatures.interfaces;

import java.util.List;

/**
 * Schnittstelle für das Social Features. 
 * Ermöglicht Zugriff und eintragen von Praktikas
 * 
 * @version 1.0
 * @author Aria Rafi Nazari
 */

public interface IPraktika {

	/**
	 * Erzeugen eines neuen Praktikumpartner
	 * Durch DB bekommt jede Partner eine Eindeutige ID
	 * 
	 * @param matNr
	 * @param firstname
	 * @param surname
	 * @param email
	 * @param handy
	 * @return
	 */
	public boolean createPartner(String matNr, String firstname,
			String surname, String email, String handy);

	/**
	 * Praktikumspartner die Daten ändern/updaten
	 * @param matNr
	 * @param firstname
	 * @param surname
	 * @param email
	 * @param handy
	 * @return
	 */
	public boolean updatePartner(String matNr, String firstname,
			String surname, String email, String handy);

	/**
	 * Partner mit den entsprechende Matrikelnummer aus 
	 * jede Praktika gelöscht, wo er eingetragen ist.
	 * @param matNr
	 * @return
	 */
	public boolean deletePartnerID(String matNr);
	
	/**
	 * Jeder Partner der den entsprechenden Praktika besucht wird gelöscht.
	 * Bleibt aber bei den anderen Praktika noch erhalten.
	 * @param lecture
	 * @return
	 */
	public boolean deletePartnerLecture(String lecture);

	/**
	 * Beim App ausführung müssen die Praktikas und die ensprechende
	 * Praktikumspartner wieder neu eingetragen werden.
	 * Aufruf im Praktika
	 */
	public void getPartnerfromDB();
	
	/**
	 * Holt die ID die im DB eingetragen ist 
	 * @param matNr
	 * @return
	 */
	public Integer getPartnerID(String matNr);

	/**
	 * Gibt alle Partner an, mit der die Praktium bearbeitet
	 * @param slecture
	 * @return
	 */
	public List<IPartner> getPartnerLecture(String slecture);
	
	/**
	 * Gibt Partner mit dem entsprechenden Matrikelnummer zurück
	 * @param matNr
	 * @return
	 */
	public IPartner getPartner(String matNr);

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
