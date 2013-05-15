package com.example.haw_app.veranstaltungsplan.interfaces;

import org.joda.time.DateTime;

public interface Itermin {
	
	/**
	 * Gibt die Semestergruppe der Veranstaltung zur�ck.
	 * @return z.B. "B-AI4"
	 */
	public String semesterGruppe();
	
	/**
	 * Gibt den Namen der Veranstalltung zur�ck
	 * @return z.B. "BAI4-SE2"
	 */
	public String name();
	
	/**
	 * Gibt Startdatum und Uhrzeit der Veranstaltung zur�ck
	 * @return DateTime Object (Joda-Time)
	 */
	public DateTime von();
	
	/**
	 * Gibt Enddatum und Uhrzeit der Veranstaltung zur�ck
	 * @return DateTime Object (Joda-Time)
	 */
	public DateTime bis();
	
	/**
	 * Gibt das Prof. K�rzel zur�ck
	 * @return z.B. "STF"
	 */
	public String prof();
	
	/**
	 * Gibt den Raum der Verantaltung zur�ck
	 * @return z.B. "1101b"
	 */
	public String raum();
	
}
