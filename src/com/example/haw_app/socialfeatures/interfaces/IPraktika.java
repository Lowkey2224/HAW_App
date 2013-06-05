package com.example.haw_app.socialfeatures.interfaces;

import java.util.List;

/**
 * Schnittstelle f�r das Social Features. 
 * Erm�glicht Zugriff und eintragen von Praktikas
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
	 * Praktikumspartner die Daten �ndern/updaten
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
	 * jede Praktika gel�scht, wo er eingetragen ist.
	 * @param matNr
	 * @return
	 */
	public boolean deletePartnerID(String matNr);
	
	/**
	 * Jeder Partner der den entsprechenden Praktika besucht wird gel�scht.
	 * Bleibt aber bei den anderen Praktika noch erhalten.
	 * @param lecture
	 * @return
	 */
	public boolean deletePartnerLecture(String lecture);

	/**
	 * Beim App ausf�hrung m�ssen die Praktikas und die ensprechende
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
	 * Gibt Partner mit dem entsprechenden Matrikelnummer zur�ck
	 * @param matNr
	 * @return
	 */
	public IPartner getPartner(String matNr);

	/**
	 * Gibt den Vorlesungzur�ck
	 * 
	 * @return firstname
	 */
	public String getLecture();

	/**
	 * Gibt den Namen des Professors zur�ck
	 * 
	 * @return profName
	 */
	public String getProfName();

	/**
	 * Gibt den Gruppen Nummer des Praktikas zur�ck
	 * 
	 * @return groupNr
	 */
	public String getGroupNr();

	/**
	 * Gibt den Status des Praktikas zur�ck
	 * 
	 * @return status
	 */
	public String getStatus();

}
