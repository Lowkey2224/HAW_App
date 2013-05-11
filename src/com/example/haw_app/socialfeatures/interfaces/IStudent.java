package com.example.haw_app.socialfeatures.interfaces;

import java.util.Set;

/**
 * Schnittstelle f�r das Social Features.
 * Erm�glicht Zugriff und updaten von Student Praktikas
 * @version 1.0
 * @author Aria Rafi Nazari
 */

public interface IStudent {
	
	/**
	 * Durch Aufruf des des update, werden die Praktikas aktualisiert.
	 * Alte Praktikas bleiben erhalten und werden nicht �berschrieben.
	 * Jede Praktika bekommt eine eindeutige ID zugewiesen.
	 * @return boolean
	 */
	public boolean updatePraktikas();
	
	/**
	 * Praktika l�schen.
	 * Partner die f�r diesen Praktika eingetragen wurden sind, werden mitgel�scht.
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
	 * Gibt eine Menge der Praktika zur�ck die in seine Liste hat.
	 * @return Set<String> Menge von Praktikas
	 */
	public Set<String> getPraktikasLectureSet();
	
	/**
	 * Gibt die Daten des entsprechenden Praktia zur�ck
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
