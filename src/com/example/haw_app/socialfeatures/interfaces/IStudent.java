package com.example.haw_app.socialfeatures.interfaces;

/**
 * Schnittstelle für das Social Features.
 * Ermöglicht Zugriff und updaten von Student Praktikas
 * @version 0.1
 * @author Aria Rafi Nazari
 */

public interface IStudent {
	
	/**
	 * Durch Aufruf des des update, werden die Praktikas aktualisiert.
	 * Stand des aktualisierung hängt vom letzten Aufruf vom Stisys ab.
	 * Alte Praktikas werden gelöscht, wenn oldDelete true ist.
	 * @param oldDelete
	 * @return
	 */
	public boolean updatePraktikas(Boolean oldDelete);
	
	/**
	 * Einzelne Praktika löschen.
	 * Eindeutig durch den Fachname.
	 * @param lecture
	 * @return
	 */
	public boolean deletePraktika(String lecture);

}
