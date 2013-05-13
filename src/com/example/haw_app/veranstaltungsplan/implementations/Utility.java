package com.example.haw_app.veranstaltungsplan.implementations;

import org.joda.time.DateTime;

public class Utility {
	
	public static Termin termin(String semesterGruppe,String name, DateTime von,DateTime bis,String prof,String raum) {
		return Termin.valueOf(semesterGruppe, name, von, bis, prof, raum);
	}

}
