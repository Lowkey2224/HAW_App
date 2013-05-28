package com.example.haw_app.stisysManager;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;


	/**
	 * Schnittstelle fuer das Studierendeninformationssystem.
	 * Ermoeglicht Zugriff auf die dort befindlichen Daten und Informationen 
	 * @version 0.1.1
	 * @author Marcus Jenz, Dominike Manietzki
	 */

	public interface IStiSysManager {

		/**
		* Berechnet die Durchschnittsnote 
		* @return Liefert die Durchschnittsnote um Faktor 10 erhoeht zurueck. Bsp.: 2,7 wird als 27 zurueckgegeben.
		*/
		public double getAverageGrade();
		/**
		* Gibt die Anzahl der Credits die man zur naechst Besseren Note braucht zurueck.
		* @return Die Anzahl der Credits.
		*/
		public double getDifferenceToBetterGrade();
	    
	    /** 
	     * Gibt eine Map mit Typ und Datum/Zeit der naechsten Anmeldetermine zurueck
	     * @return Map<String,Date[2]> - Map mit Typ -> Array<Date & Time von, Date & Time bis>
	     */
	    public Map<String,DateTime[]> getNextApplicationDates();
	     
	    
	    /**
	     * Liefert den Vor- und Nachnamen des Studenten zurueck
	     * @return String - Vorname,Nachname des Studenten
	     */
	    public String getStudentName();
	    
	    /**
	     * Liefert den Benutzernamen des Studenten zurueck
	     * @return String - eindeutige Stundentenkennung durch die HAW (z.b. aaa123)
	     */
	    public String getUserName();
	    
	    /**
	     * Liefert das Ablaufdatum des Passwortes zurueck
	     * @return Date - Datum des Verfalltages
	     */
	    public DateTime getPasswordExpirationDate();
	    
	    /**
	     * Liefert die Martikelnummer des Studenten zurueck
	     * @return int - Martikelnummer des Studenten
	     */
	    public int getMatriculationNumber();
	    
	    /**
	     * Liefert Geburtstag des Studenten
	     * @return Date - Datum des Geburtstages
	     */
	    public DateTime getBirthday();
	    
	    /**
	     * Liefert Druckguthaben des Studenten
	     * @return int - Guthaben in cent
	     */
	    public int getPrintCredit();
	    
	    /**
	     * Liefert die gesellschaftswissenschaftlichen Kurse zurueck, zu denen der Student angemeldet ist
	     * @return Map<String,String[2]> - Map mit Kursname -> Array<Prof,Status>
	     */
	    public Map<String,String[]> getRegisteredSocialSciencesCourses();
	    
	     /**
	     * Liefert die Pruefungen zurueck, zu denen der Student angemeldet ist
	     * @return Map<String,String[2]> - Map mit Kursname -> Array<Prof,Status>
	     */
	    public Map<String,String[]> getRegisteredTests();
	    
	    
	     /**
	     * Liefert die Wahlpflichtprojekte zurueck, zu denen der Student angemeldet ist(exklusive Praktika)
	     * @return Map<String,String[2]> - Map mit Kursname -> Array<Prof,Status>
	     */
	    public Map<String,String[]> getRegisteredChosenCourses();    
	    
	 
	     /**
	     * Liefert die Praktika zurueck, zu denen der Student angemeldet ist(inklusive WP-Praktika)
	     * @return Map<String,String[3]> - Map mit Kursname -> Array<Prof,Gruppe,Status>
	     */
	    public Map<String,String[]> getRegisteredTrainings();
	    
	     /**
	     * Liefert die bereits abgelegten Pruefungen zurueck
	     * @return Map<String,Integer> - Map: Pruefung -> Ergebnis
	     */
	    public Map<String,Integer> getSolvedTests();    
	    
	     /**
	     * Liefert die bereits abgelegten Praktika zurueck
	     * @return Map<String,Boolean> - Map: Praktikum -> Status
	     */
	    public Map<String,Boolean> getSolvedTrainings();
	    
	    
	     /**
	     * Liefert die angemeldeten MailingListen zurueck 
	     * @return List<String> - Liste mit den Namen der angemeldeten Mailinglisten - Faecher
	     */
	    public Map<String,String> getMailingLists();

	    
        /**
         * Liefert die bereits erreichten CP des Studenten
         * @return int - erreichte CP
         */
        public int getSolvedCP();
        
        
        /**
         * Beschafft Seiteninformationen neu
         */
        public void syncData();

        
        /**
         * Meldet Studenten bei Uebergebenem Kurs an
         * @param name - Uebergebener Kurs
         */
        public void subscribeCourse(String name);
        
         /**
         * Meldet Studenten bei Uebergebenem Kurs ab
         * @param name - Uebergebener Kurs
         */
        public void unsubscribeCourse(String name);
        
        /**
        * Zeigt anmeldbare Kurse
        * @param name - Uebergebener Kurs
        */
       public List<String> getUnsubscribeableCourses();       
       
       /**
        * Zeigt anmeldbare Kurse
        * @param name - Uebergebener Kurs
        */
       public List<String> getSubscribeableCourses();

}
