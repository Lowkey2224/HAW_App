package com.example.haw_app.veranstaltungsplan.interfaces;

import java.util.List;

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
	 * Methode zum aendern der belegten Faecher.(Nur belegte Faecher werden auch exportiert.) 
	 * @param faecher Liste der Namen der belgeten Faecher z.B. "BAI4-SE2".
	 */
	public void setzeBelegteFaecher(List<String> faecher);
	
	/**
	 * Methode zum aendern der Datei die geparst werden soll.
	 * @param file Datei die geparst wird.
	 */
	public void setzeDatei(String file);

}
