package com.example.haw_app.veranstaltungsplan.implementations;

import java.util.Date;

public class Termin implements com.example.haw_app.veranstaltungsplan.interfaces.Itermin{
	
	private int semester;
	private String name;
	private Date von;
	private Date bis;
	private String wochentag;
	private int start;
	private int ende;
	private String prof;
	private String assistent;
	private int raum;
	
	private Termin(int semester,String name, Date von,Date bis,String wochentag,int start,int ende,String prof,String assistent,int raum){
		this.semester = semester;
		this.name = name;
		this.von = von;
		this.bis = bis;
		this.wochentag = wochentag;
		this.start = start;
		this.ende = ende;
		this.prof = prof;
		this.assistent = assistent;
		this.raum = raum;
	}
	
	public static Termin valueOf(int semester,String name, Date von,Date bis,String wochentag,int start,int ende,String prof,String assistent,int raum) {
        return new Termin(semester,name,von,bis,wochentag,start,ende,prof,assistent,raum);
    }

	@Override
	public int semester(){
		return semester;
	}
	
	@Override
	public String name(){
		return name;
	}
	
	@Override
	public Date von(){
		return von;
	}
	
	@Override
	public Date bis(){
		return bis;
	}
	
	@Override
	public String wochentag(){
		return wochentag;
	}
	
	@Override
	public int start(){
		return start;
	}
	
	@Override
	public int ende(){
		return ende;
	}
	
	@Override
	public String prof(){
		return prof;
	}
	
	@Override
	public String assistent(){
		return assistent;
	}
	
	@Override
	public int raum(){
		return raum;
	}
	
}
