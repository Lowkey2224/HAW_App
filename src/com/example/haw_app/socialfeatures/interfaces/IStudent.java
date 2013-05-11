package com.example.haw_app.socialfeatures.interfaces;

import java.util.Set;

/**
 * Schnittstelle für das Social Features.
 * Ermöglicht Zugriff und updaten von Student Praktikas
 * @version 1.0
 * @author Aria Rafi Nazari
 */

public interface IStudent {
	
	/**
	 * Durch Aufruf des des update, werden die Praktikas aktualisiert.
	 * Alte Praktikas bleiben erhalten und werden nicht überschrieben.
	 * Jede Praktika bekommt eine eindeutige ID zugewiesen.
	 * @return boolean
	 */
	public boolean updatePraktikas();
	
	/**
	 * Praktika löschen.
	 * Partner die für diesen Praktika eingetragen wurden sind, werden mitgelöscht.
	 * @param lecture Praktika
	 * @return boolean
	 */
	public boolean deletePraktika(String lecture);

	
	/**
	 * Gibt die eindeutige ID vom Praktika.
	 * ID wird durch DB bestimmt.
	 * @param lecture Praktika
	 * @return ID vom Praktika
	 */
	public Integer getPraktikaID(String lecture);
	
	/**
	 * Gibt eine Menge der Praktika zurück die in seine Liste hat.
	 * @return Set<String> Menge von Praktikas
	 */
	public Set<String> getPraktikasLectureSet();
	
	/**
	 * Gibt die Daten des entsprechenden Praktia zurück
	 * @param lecture Praktika
	 * @return IPraktika Daten des Praktikum
	 */
	public IPraktika getPraktika(String lecture);
	
	/**
	 * Matrikelnummer des Studenten holen
	 * @return matNr
	 */
	public int getmatNr();
	
}
