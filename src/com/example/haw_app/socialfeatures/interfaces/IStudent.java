package com.example.haw_app.socialfeatures.interfaces;

import java.util.Set;

/**
 * Schnittstelle f�r das Social Features.
 * Erm�glicht Zugriff und updaten von Student Praktikas
 * @version 0.7
 * @author Aria Rafi Nazari
 */

public interface IStudent {
	
	/**
	 * Durch Aufruf des des update, werden die Praktikas aktualisiert.
	 * Stand des aktualisierung h�ngt vom letzten Aufruf vom Stisys ab.
	 * Alte Praktikas werden gel�scht, wenn oldDelete true ist.
	 * @param oldDelete
	 * @return
	 */
	public boolean updatePraktikas();
	
	/**
	 * Einzelne Praktika l�schen.
	 * Eindeutig durch den Fachname.
	 * @param lecture
	 * @return
	 */
	public boolean deletePraktika(String lecture);
	
	/**
	 * Gibt alle Praktia an die der Student besucht
	 * @return
	 */
	public Set<String> getPraktikasLectureSet();
	
	/**
	 * Gibt die Daten des entsprechenden Praktia zur�ck
	 * @param slecture
	 * @return
	 */
	public IPraktika getPraktika(String slecture);
	
}
