package com.example.haw_app.stisysManager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StiSysManager implements IStiSysManager{

	private static StiSysManager sm = null;
	@Override
	public int getAverageGrade() {
		return 22;
	}

	@Override
	public int getDifferenceToBetterGrade() {
		return 125;
	}

	@Override
	public Map<String, Date[]> getNextApplicationDates() {
		Map<String, Date[]> ret = new HashMap<String, Date[]>();
		Date[] ary  = new Date[2];
		ary[0] = new Date(2013, 06, 10, 10, 0);
		ary[1] = new Date(2013, 06, 16, 10, 0);
		ret.put("Projekte", ary);
		return ret;
	}

	@Override
	public String getStudentName() {		
		return "Coolio McCool";
	}

	@Override
	public String getUserName() {
		return "ace111";
	}

	@Override
	public Date getPasswordExpirationDate() {
		return new Date(2013, 11, 11);
	}

	@Override
	public int getMatriculationNumber() {
		return 1337111;
	}

	@Override
	public Date getBirthday() {
		return new Date(1937, 03, 15);
	}

	@Override
	public int getPrintCredit() {
		return 4835;
	}

	@Override
	public Map<String, String[]> getRegisteredSocialSciencesCourses() {
		Map<String, String[]> ret = new HashMap<String, String[]>();
		String[] ary = new String[2];
		ary[0]="zu Guttenberg";
		ary[1]="erf";
		ret.put("Copying 101", ary);
		return ret;
	}

	@Override
	public Map<String, String[]> getRegisteredTests() {
		Map<String, String[]> ret = new HashMap<String, String[]>();
		String[] ary = new String[2];
		ary[0]="Stallman";
		ary[1]="OK";
		ret.put("Compiler und Interpreter", ary);
		return ret;
	}

	@Override
	public Map<String, String[]> getRegisteredChosenCourses() {
		Map<String, String[]> ret = new HashMap<String, String[]>();
		String[] ary = new String[2];
		ary[0]="Morgan";
		ary[1]="OK";
		ret.put("Künstliche Intelligenzen", ary);
		return ret;
	}

	@Override
	public Map<String, String[]> getRegisteredTrainings() {
		Map<String, String[]> ret = new HashMap<String, String[]>();
		String[] ary = new String[3];
		ary[0]="Stallman";
		ary[1]="3";
		ary[3]="OK";
		ret.put("Compiler und Interpreter", ary);
		return ret;
	}

	@Override
	public Map<String, Integer> getSolvedTests() {
		return new HashMap<String, Integer>(){{
			put("Mathe Gundlagen", 15);
			put("Logik und Berechenbarkeit", 15);
		}};
	}

	@Override
	public Map<String, Boolean> getSolvedTrainings() {
		return new HashMap<String, Boolean>(){{
			put("Mathe Gundlagen", true);
			put("Logik und Berechenbarkeit", true);
			put("Betriebswirtschaft 2",false);
		}};
	}

	@Override
	public Map<String,String> getMailingLists() {
		return new HashMap<String,String>(){{
			put("Compiler und Interpreter","bai4cip@haw-hamburg.de");
			put("Projekt Künstliche Intelligenzen","po_mgn@haw-hamburg.de");
			put("Semestergruppe bai4","bai4@haw-hamburg.de");
		}};		
	}
	
	private StiSysManager() {}
	
	protected static IStiSysManager getInstance()
	{
		if (sm == null)
			sm = new StiSysManager();
		return sm;
	}

}
