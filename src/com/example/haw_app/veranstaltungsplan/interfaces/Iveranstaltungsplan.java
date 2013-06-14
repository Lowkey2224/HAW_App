package com.example.haw_app.veranstaltungsplan.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Iveranstaltungsplan {
	
	/**
	 * Erstellt, mit Hilfe der Liste der belegten Faecher, eine .ics Datei lokal. 
	 */
	public void exportieren()throws Exception;
	
	/**
	 * Downloaded die aktuelle Termin .txt Datei vom Server und parst diese.
	 */
	public void aktualisieren()throws Exception;
	
	/**
	 * Parst die aktuelle .txt Datei.
	 */
	public void aktualisierenOhneDownload()throws Exception;
	
	/**
	 * Methode zum aendern der belegten Faecher.(Nur belegte Faecher werden auch exportiert.) 
	 * @param faecher Liste der Namen der belgeten Faecher z.B. "BAI4-SE2".
	 */
	public void setzeBelegteFaecher(List<String> faecher);
	
	/**
	 * Methode zum aendern der Datei die geparst werden soll.
	 * @param file Datei die geparst wird.
	 */
	public void setzeDatei(String file);
	
	/**
	 * Methode zum aendern der Url von der die Datei gedownloaded wird.
	 * @param url Url von der die Datei gedownloaded wird.
	 */
	public void setzeUrl(String url);
	
	/**
	 * 
	 * @return Set der Namen der Veranstaltungen
	 */
	public Set<String> veranstaltungsSet();
	
	/**
	 * prüft die aktuelle belegung der PC-Pools anhand der vorliegenden Termin Liste und gibt eine Map zuruek
	 * @return Map für die PC-Pools key: Raum value: frei || BAI3-SE2 von 8:15 Uhr bis 11:30 Uhr.
	 */
	public Map<String, String> pcPoolAktuell();

}
