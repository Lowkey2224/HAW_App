package com.example.haw_app.stisysManager;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Schnittstelle für das Studierendeninformationssystem.
 * Ermöglicht Zugriff auf die dort befindlichen Daten und Informationen 
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
     * Gibt eine Map mit Typ und Datum/Zeit der nächsten Anmeldetermine zurück
     * @return Map<String,Date[2]> - Map mit Typ -> Array<Date & Time von, Date & Time bis>
     */
    public Map<String,Date[]> getNextApplicationDates();
     
    
    /**
     * Liefert den Vor- und Nachnamen des Studenten zurück
     * @return String - Vorname,Nachname des Studenten
     */
    public String getStudentName();
    
    /**
     * Liefert den Benutzernamen des Studenten zurück
     * @return String - eindeutige Stundentenkennung durch die HAW (z.b. aaa123)
     */
    public String getUserName();
    
    /**
     * Liefert das Ablaufdatum des Passwortes zurück
     * @return Date - Datum des Verfalltages
     */
    public Date getPasswordExpirationDate();
    
    /**
     * Liefert die Martikelnummer des Studenten zurück
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
     * Liefert die gesellschaftswissenschaftlichen Kurse zurück, zu denen der Student angemeldet ist
     * @return Map<String,String[2]> - Map mit Kursname -> Array<Prof,Status>
     */
    public Map<String,String[]> getRegisteredSocialSciencesCourses();
    
     /**
     * Liefert die Prüfungen zurück, zu denen der Student angemeldet ist
     * @return Map<String,String[2]> - Map mit Kursname -> Array<Prof,Status>
     */
    public Map<String,String[]> getRegisteredTests();
    
    
     /**
     * Liefert die Wahlpflichtprojekte zurück, zu denen der Student angemeldet ist(exklusive Praktika)
     * @return Map<String,String[2]> - Map mit Kursname -> Array<Prof,Status>
     */
    public Map<String,String[]> getRegisteredChosenCourses();    
    
 
     /**
     * Liefert die Praktika zurück, zu denen der Student angemeldet ist(inklusive WP-Praktika)
     * @return Map<String,String[3]> - Map mit Kursname -> Array<Prof,Gruppe,Status>
     */
    public Map<String,String[]> getRegisteredTrainings();
    
     /**
     * Liefert die bereits abgelegten Prüfungen zurück
     * @return Map<String,Integer> - Map: Prüfung -> Ergebnis
     */
    public Map<String,Integer> getSolvedTests();    
    
     /**
     * Liefert die bereits abgelegten Praktika zurück
     * @return Map<String,Boolean> - Map: Praktikum -> Status
     */
    public Map<String,Boolean> getSolvedTrainings();
    
    
     /**
     * Liefert die angemeldeten MailingListen zurück 
     * @return List<String> - Liste mit den Namen der angemeldeten Mailinglisten - Fächer
     */
    public Map<String,String> getMailingLists();

    
    

}
