package com.example.haw_app.stisysManager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

public class StiSysManager implements IStiSysManager{

	private static StiSysManager sm = null;
	@Override
	public double getAverageGrade() {
		return 22;
	}

	@Override
	public double getDifferenceToBetterGrade() {
		return 125;
	}

	@Override
	public Map<String, DateTime[]> getNextApplicationDates() {
		Map<String, DateTime[]> ret = new HashMap<String, DateTime[]>();
		DateTime[] ary  = new DateTime[2];
		ary[0] = new DateTime(2013, 06, 10, 10, 0);
		ary[1] = new DateTime(2013, 06, 16, 10, 0);
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
	public DateTime getPasswordExpirationDate() {
		return new DateTime(2013, 11, 11,0,0);
	}

	@Override
	public int getMatriculationNumber() {
		return 1337111;
	}

	@Override
	public DateTime getBirthday() {
		return new DateTime(1937, 03, 15,0,0);
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
		ary[0]="Astley";
		ary[1]="OK";
		ret.put("Never gonna give you up", ary);
		ret.put("Never gonna let you down ", ary);
		ret.put("Never gonna run around and desert you", ary);
		ret.put("Never gonna make you cry,", ary);
		ret.put("Never gonna say goodbye,", ary);
		ret.put("Never gonna tell a lie and hurt you", ary);
		
		
//		Never gonna say goodbye
//		Never gonna tell a lie and hurt you 
		return ret;
	}

	@Override
	public Map<String, String[]> getRegisteredChosenCourses() {
		Map<String, String[]> ret = new HashMap<String, String[]>();
		String[] ary = new String[2];
		ary[0]="Astley";
		ary[1]="OK";
		ret.put("Never gonna give you up", ary);
		ret.put("Never gonna let you down ", ary);
		ret.put("Never gonna run around and desert you", ary);
		ret.put("Never gonna make you cry,", ary);
		ret.put("Never gonna say goodbye,", ary);
		ret.put("Never gonna tell a lie and hurt you", ary);
		return ret;
	}

	@Override
	public Map<String, String[]> getRegisteredTrainings() {
		Map<String, String[]> ret = new HashMap<String, String[]>();
		String[] ary = new String[3];
		ary[0]="Astley";
		ary[1]="OK";
		ary[2]="1";
		ret.put("Never gonna give you up", ary);
		ret.put("Never gonna let you down ", ary);
		ret.put("Never gonna run around and desert you", ary);
		ret.put("Never gonna make you cry,", ary);
		ret.put("Never gonna say goodbye,", ary);
		ret.put("Never gonna tell a lie and hurt you", ary);
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
			put("Projekt Kuenstliche Intelligenzen","po_mgn@haw-hamburg.de");
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

	@Override
	public int getSolvedCP() {		
		return 30;
	}

	@Override
	public void syncData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribeCourse(String name) {
		//TODO Auto-generated method stub
	}

	@Override
	public void unsubscribeCourse(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getUnsubscribeableCourses() {
		return new ArrayList<String>(){/**
			 * 
			 */
			private static final long serialVersionUID = 7696659396862865869L;

		{
			add("Fach1");
			add("Fach2");
			add("Fach3");
		}};
		
	}

	@Override
	public List<String> getSubscribeableCourses() {
		return new ArrayList<String>(){/**
			 * 
			 */
			private static final long serialVersionUID = -4142609465343678617L;

		{
			add("Fach4");
			add("Fach5");
			add("Fach6");
		}};
		
	}



}
