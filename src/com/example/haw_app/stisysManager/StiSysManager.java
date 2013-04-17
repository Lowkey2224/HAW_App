package com.example.haw_app.stisysManager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StiSysManager implements IStiSysManager{

	private static StiSysManager sm = null;
	@Override
	public int getAverageGrade() {
		// TODO Auto-generated method stub
		return 22;
	}

	@Override
	public int getDifferenceToBetterGrade() {
		// TODO Auto-generated method stub
		return 125;
	}

	@Override
	public Map<String, Date[]> getNextApplicationDates() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return "ace111";
	}

	@Override
	public Date getPasswordExpirationDate() {
		// TODO Auto-generated method stub
		return new Date(2013, 11, 11);
	}

	@Override
	public int getMatriculationNumber() {
		// TODO Auto-generated method stub
		return 1337111;
	}

	@Override
	public Date getBirthday() {
		// TODO Auto-generated method stub
		return new Date(1937, 03, 15);
	}

	@Override
	public int getPrintCredit() {
		// TODO Auto-generated method stub
		return 4835;
	}

	@Override
	public Map<String, String[]> getRegisteredSocialSciencesCourses() {
		Map<String, String[]> ret = new HashMap<String, String[]>();
		String[] ary = new String[2];
		ary[0]="zu Guttenberg";
		ary[1]="erf";
		ret.put("Copying 101", ary);
		// TODO Auto-generated method stub
		return ret;
	}

	@Override
	public Map<String, String[]> getRegisteredTests() {
		// TODO Auto-generated method stub
		Map<String, String[]> ret = new HashMap<String, String[]>();
		String[] ary = new String[2];
		ary[0]="Stallman";
		ary[1]="OK";
		ret.put("Compiler und Interpreter", ary);
		return ret;
	}

	@Override
	public Map<String, String[]> getRegisteredChosenCourses() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub		
		return new HashMap<String, Integer>(){{
			put("Mathe Gundlagen", 15);
			put("Logik und Berechenbarkeit", 15);
		}};
	}

	@Override
	public Map<String, Boolean> getSolvedTrainings() {
		// TODO Auto-generated method stub
		return new HashMap<String, Boolean>(){{
			put("Mathe Gundlagen", true);
			put("Logik und Berechenbarkeit", true);
			put("Betriebswirtschaft 2",false);
		}};
	}

	@Override
	public Map<String,String> getMailingLists() {
		// TODO Auto-generated method stub
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
