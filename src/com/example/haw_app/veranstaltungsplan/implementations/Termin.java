package com.example.haw_app.veranstaltungsplan.implementations;

import org.joda.time.DateTime;

public class Termin implements com.example.haw_app.veranstaltungsplan.interfaces.Itermin{
	
	private String semesterGruppe;
	private String name;
	private DateTime von;
	private DateTime bis;
	private String prof;
	private String raum;
	
	private Termin(String semesterGruppe,String name, DateTime von,DateTime bis,String prof,String raum){
		this.semesterGruppe = semesterGruppe;
		this.name = name;
		this.von = von;
		this.bis = bis;
		this.prof = prof;
		this.raum = raum;
	}
	
	public static Termin valueOf(String semesterGruppe,String name, DateTime von,DateTime bis,String prof,String raum) {
        return new Termin(semesterGruppe,name,von,bis,prof,raum);
    }

	@Override
	public String semesterGruppe(){
		return semesterGruppe;
	}
	
	@Override
	public String name(){
		return name;
	}
	
	@Override
	public DateTime von(){
		return von;
	}
	
	@Override
	public DateTime bis(){
		return bis;
	}
	
	@Override
	public String prof(){
		return prof;
	}
	
	@Override
	public String raum(){
		return raum;
	}
	
}
