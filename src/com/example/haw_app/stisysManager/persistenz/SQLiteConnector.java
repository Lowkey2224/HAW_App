package com.example.haw_app.stisysManager.persistenz;


import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.haw_app.Util;

public class SQLiteConnector extends SQLiteOpenHelper {
//TODO NextApplicationDates, RegisteredTrainigs, SolvedTrainings,SolvedTests,Mailinglists
	public SQLiteConnector(Context context) {
		super(context, dbName, null, dbversion);
		// TODO Auto-generated constructor stub
	}
	private static final String TABLE_COURSES = "Courses";
	private static final int GWS = 0, REGISTERED_TESTS = 1, WPS = 2;
	private static final String dbName = "StiSysManagerDB";
	private static final int dbversion = 1;
	private static final String[] tableName = new String[] {"Student","NextApplicationDates",TABLE_COURSES,
		"Trainings","SolvedTests","SolvedTrainings","Mailinglists"};
	private static final String[] columns = new String[] {
		"(name VARCHAR, avgGrade REAL, userName VARCHAR, matNr INT, pwExpDate VARCHAR, birthday VARCHAR, printCredit INT)",
		"(type VARCHAR, fromDate VARCHAR, toDate VARCHAR)",//NextApplicationDates
		"(name VARCHAR, type INT, prof VARCHAR, status VARCHAR)",//Courses
		"(name VARCHAR, prof VARCHAR, status VARCHAR, group INT)", //trainings		
		"(name VARCHAR, grade INT)",//solvedTests
		"(name VARCHAR, status VARCHAR)", //solvedTrainings
		"(name VARCHAR, adress VARCHAR)"//mailinglists
		}; 
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<tableName.length;i++)
		{		
			sb.append("CREATE TABLE ").append(tableName[i]).append(" ").append(columns[i]);
			db.execSQL(sb.toString());
		}		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
	
	
	public Map<String, String[]> getSocialSciencesCourses()
	{
		return getCourses(GWS);
	}
	
	public boolean clearSocialSienceCourses()
	{
		return clearCourses(GWS);
	}
	
	public boolean saveSocialSienceCourses(Map<String,String[]> courses)
	{
		return saveCourses(courses, GWS);
	}
	
	public Map<String, String[]> getRegisteredTests()
	{
		return getCourses(REGISTERED_TESTS);
	}
	
	public boolean clearRegisteredTests()
	{
		return clearCourses(REGISTERED_TESTS);
	}
	
	public boolean saveRegisteredTests(Map<String,String[]> tests)
	{
		return saveCourses(tests, REGISTERED_TESTS);
	}
	
	public Map<String, String[]> getChosenCourses()
	{
		return getCourses(WPS);
	}
	
	public boolean clearChosenCourses()
	{
		return clearCourses(WPS);
	}
	
	public boolean saveChosenCourses(Map<String,String[]> courses)
	{
		return saveCourses(courses, WPS);
	}
	
	private Map<String,String[]> getCourses(int type)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Map<String,String[]> ret = null;		
		// name VARCHAR, type INT, prof VARCHAR, status VARCHAR)
		Cursor cursor = db.rawQuery("SELECT name, prof, status FROM `+TABLE_COURSES+` where type = "+ type, null);		
		if (cursor.getCount()!=0)
		{
			ret = new HashMap<String, String[]>();
			while (cursor.moveToNext()){
				String name;
				name = cursor.getString(cursor.getColumnIndex("name"));
				String[] ary = new String[]{cursor.getString(cursor.getColumnIndex("prof")),cursor.getString(cursor.getColumnIndex("status"))};
				ret.put(name, ary);
			}
		}
		return ret;
	}
	
	private boolean clearCourses(int type)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("TABLE_COURSES", "type = ?", new String[]{""+type});
		return false;
	}
	
	private boolean saveCourses(Map<String,String[]> courses,int type)
	{
		boolean ret = true;
		SQLiteDatabase db = this.getWritableDatabase();
		for (Map.Entry<String, String[]> e : courses.entrySet())
		{
			ContentValues values = new ContentValues();
			values.put("name", e.getKey());
			values.put("prof", e.getValue()[0]);
			values.put("status",e.getValue()[1]);
			values.put("type", type);
			if (db.insert(TABLE_COURSES, null, values)==-1)
				ret = false;
		}
		return ret;
	}
	
	
	public String getName()
	{
		return this.getStudent().name;
	}
	
	public String getUserName()
	{
		return this.getStudent().userName;
	}
	
	public double getAverageGrade()
	{
		return this.getStudent().avgGrade;
	}
	
	public int getMatricularNumber()
	{
		return this.getStudent().matNr;
	}
	
	public DateTime getPasswordExpirationDate()
	{
		return this.getStudent().pwExpDate;
	}
		
	public DateTime getBirthday()
	{
		return this.getStudent().birthdate;
	}
	
	public int getPrintCredit()
	{
		return this.getStudent().printCredit;
	}
	
	public boolean setName(String name)
	{
		Student std = this.getStudent();
		std.name = name;		
		return (saveStudent(std)>0)?true:false;
	}
	
	public boolean setUserName(String userName)
	{
		Student std = this.getStudent();
		std.userName= userName;		
		return (saveStudent(std)>0)?true:false;
	}
	
	public boolean setAverageGrade(double avgGrade)
	{
		Student std = this.getStudent();
		std.avgGrade = avgGrade;
		return (saveStudent(std)>0)?true:false;
	}
	
	public boolean setMatriculationNumber(int matNr)
	{
		Student std = this.getStudent();
		std.matNr = matNr;		
		return (saveStudent(std)>0)?true:false;
	}
	
	public boolean setPasswordExpirationDate(DateTime date)
	{
		Student std = this.getStudent();
		std.pwExpDate = date;
		return (saveStudent(std)>0)?true:false;
	}
	
	public boolean setBirthday(DateTime date)
	{
		Student std = this.getStudent();
		std.birthdate = date;
		return (saveStudent(std)>0)?true:false;
	}
	
	public boolean setPrintCredit(int creditsInCent)
	{
		Student std = this.getStudent();
		std.printCredit= creditsInCent;
		return (saveStudent(std)>0)?true:false;
	}
	
	private int saveStudent(Student std)
	{
		 SQLiteDatabase db = this.getWritableDatabase();
		 ContentValues val = new ContentValues();
		 val.put(Student.NAME, std.name);
		 val.put(Student.AVERAGEGRADE, std.avgGrade);
		 val.put(Student.USERNAME, std.userName);
		 val.put(Student.MATRIKELNUMMER, std.matNr);
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
		 val.put(Student.PASSWORD_EXP_DATE, fmt.print(std.pwExpDate));
		 val.put(Student.BIRTHDAY, fmt.print(std.birthdate));
		 val.put(Student.PRINTCREDIT, std.printCredit);		 
		 return db.update(Student.TABLENAME, val, null,null);		
	}
	
	private Student getStudent()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Student std = new Student();		
		Cursor cursor = db.rawQuery("SELECT name, avgGrade, userName,matNr,pwExpDate,birthday,printCredit  FROM Student", null);		
		if (cursor.getCount()!=0)
		{
			cursor.moveToNext();							
			std.name = cursor.getString(cursor.getColumnIndex(Student.NAME));				
			std.avgGrade = cursor.getDouble(cursor.getColumnIndex(Student.AVERAGEGRADE));
			std.userName = cursor.getString(cursor.getColumnIndex(Student.USERNAME));
			std.matNr = cursor.getInt(cursor.getColumnIndex(Student.MATRIKELNUMMER));
			String pwExpDate = cursor.getString(cursor.getColumnIndex(Student.PASSWORD_EXP_DATE));		
			std.pwExpDate= Util.createDateTimeFromString(pwExpDate);
			String birthday = cursor.getString(cursor.getColumnIndex(Student.BIRTHDAY));
			std.birthdate= Util.createDateTimeFromString(birthday);
			std.printCredit = cursor.getInt(cursor.getColumnIndex(Student.PRINTCREDIT));											
		}else{
			
			ContentValues val = new ContentValues();
			val.put(Student.NAME, std.name);
			val.put(Student.AVERAGEGRADE, std.avgGrade);
			val.put(Student.USERNAME, std.userName);
			val.put(Student.MATRIKELNUMMER, std.matNr);
			DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
			val.put(Student.PASSWORD_EXP_DATE, fmt.print(std.pwExpDate));
			val.put(Student.BIRTHDAY, fmt.print(std.birthdate));
			val.put(Student.PRINTCREDIT, std.printCredit);	
			db.insert(Student.TABLENAME, null, val);
		}
		return std;
	}
}

class Student{
	static final String NAME = "name", USERNAME = "userName", AVERAGEGRADE ="avgGrade", MATRIKELNUMMER = "matNr", 
			PASSWORD_EXP_DATE = "pwExpDate", BIRTHDAY = "birthday", PRINTCREDIT = "printCredit";
	static final String TABLENAME ="Student";
	String name,userName;
	double avgGrade;
	int matNr, printCredit;
	DateTime birthdate, pwExpDate;
	
	static String columnList()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(NAME).append(", ").append(USERNAME).append(", ").append(AVERAGEGRADE).append(", ").append(MATRIKELNUMMER)
		.append(", ").append(PASSWORD_EXP_DATE).append(", ").append(BIRTHDAY).append(", ").append(PRINTCREDIT);
		return sb.toString();
	}
	
	public Student()
	{
		name = null;
		userName = null;
		avgGrade = 0;
		matNr = 0;
		printCredit = 0;
		birthdate = null;
		pwExpDate = null;
	}
}