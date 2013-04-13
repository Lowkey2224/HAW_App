package com.example.haw_app.stisysManager;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Schnittstelle f�r das Studierendeninformationssystem.
 * Erm�glicht Zugriff auf die dort befindlichen Daten und Informationen 
 * @version 0.1.1
 * @author Marcus Jenz, Dominike Manietzki
 */

public interface IStiSysManager {

	/**
	* Berechnet die Durchschnittsnote 
	* @return Liefert die Durchschnittsnote um Faktor 10 erhoeht zurueck. Bsp.: 2,7 wird als 27 zurueckgegeben.
	*/
	public int getAverageGrade();
	/**
	* Gibt die Anzahl der Credits die man zur naechst Besseren Note braucht zurueck.
	* @return Die Anzahl der Credits.
	*/
	public int getDifferenceToBetterGrade();
    
    /** 
     * Gibt eine Map mit Typ und Datum/Zeit der n�chsten Anmeldetermine zur�ck
     * @return Map<String,Date[2]> - Map mit Typ -> Array<Date & Time von, Date & Time bis>
     */
    public Map<String,Date[]> getNextApplicationDates();
     
    
    /**
     * Liefert den Vor- und Nachnamen des Studenten zur�ck
     * @return String - Vorname,Nachname des Studenten
     */
    public String getStudentName();
    
    /**
     * Liefert den Benutzernamen des Studenten zur�ck
     * @return String - eindeutige Stundentenkennung durch die HAW (z.b. aaa123)
     */
    public String getUserName();
    
    /**
     * Liefert das Ablaufdatum des Passwortes zur�ck
     * @return Date - Datum des Verfalltages
     */
    public Date getPasswordExpirationDate();
    
    /**
     * Liefert die Martikelnummer des Studenten zur�ck
     * @return int - Martikelnummer des Studenten
     */
    public int getMatriculationNumber();
    
    /**
     * Liefert Geburtstag des Studenten
     * @return Date - Datum des Geburtstages
     */
    public Date getBirthday();
    
    /**
     * Liefert Druckguthaben des Studenten
     * @return int - Guthaben in cent
     */
    public int getPrintCredit();
    
    /**
     * Liefert die gesellschaftswissenschaftlichen Kurse zur�ck, zu denen der Student angemeldet ist
     * @return Map<String,String[2]> - Map mit Kursname -> Array<Prof,Status>
     */
    public Map<String,String[]> getRegisteredSocialSciencesCourses();
    
     /**
     * Liefert die Pr�fungen zur�ck, zu denen der Student angemeldet ist
     * @return Map<String,String[2]> - Map mit Kursname -> Array<Prof,Status>
     */
    public Map<String,String[]> getRegisteredTests();
    
    
     /**
     * Liefert die Wahlpflichtprojekte zur�ck, zu denen der Student angemeldet ist(exklusive Praktika)
     * @return Map<String,String[2]> - Map mit Kursname -> Array<Prof,Status>
     */
    public Map<String,String[]> getRegisteredChosenCourses();    
    
 
     /**
     * Liefert die Praktika zur�ck, zu denen der Student angemeldet ist(inklusive WP-Praktika)
     * @return Map<String,String[3]> - Map mit Kursname -> Array<Prof,Gruppe,Status>
     */
    public Map<String,String[]> getRegisteredTrainings();
    
     /**
     * Liefert die bereits abgelegten Pr�fungen zur�ck
     * @return Map<String,Integer> - Map: Pr�fung -> Ergebnis
     */
    public Map<String,Integer> getSolvedTests();    
    
     /**
     * Liefert die bereits abgelegten Praktika zur�ck
     * @return Map<String,Boolean> - Map: Praktikum -> Status
     */
    public Map<String,Boolean> getSolvedTrainings();
    
    
     /**
     * Liefert die angemeldeten MailingListen zur�ck 
     * @return List<String> - Liste mit den Namen der angemeldeten Mailinglisten - F�cher
     */
    public Map<String,String> getMailingLists();

    
    

}
