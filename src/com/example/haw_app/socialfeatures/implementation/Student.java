package com.example.haw_app.socialfeatures.implementation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.haw_app.database.DatabaseSocialFeatures;
import com.example.haw_app.socialfeatures.interfaces.IPraktika;
import com.example.haw_app.socialfeatures.interfaces.IStudent;
import com.example.haw_app.stisysManager.IStiSysManager;
import com.example.haw_app.stisysManager.StiSysManagerFactory;

@SuppressLint("UseSparseArrays")
public class Student implements IStudent {

	private static Student instance = null;
	private static IStiSysManager sm = null;
	private String userName = "";
	private Map<Integer, IPraktika> praktikas = new HashMap<Integer, IPraktika>();
	private static final String DB_TABLE_PRAKTIKA = DatabaseSocialFeatures.DB_TABLE_PRAKTIKA;
	DatabaseSocialFeatures dbSF;



	
	private Student(Context context) {
		sm = StiSysManagerFactory.getInstance();
		userName = sm.getUserName();
		//Datenbankverbindung
		dbSF = new DatabaseSocialFeatures(context);
		//Daten vom DB holen
		getPraktikasfromDB();
	}

	@Override
	public boolean updatePraktikas() {
		Integer pID = null;
		Map<String, String[]> ret = new HashMap<String, String[]>();
		//Praktikas die der Student in seiner Liste hat.
		Set<String> oldLecture = getPraktikasLectureSet();
		Set<String> newLecture;
		String[] valuePraktika = new String[3];;
		SQLiteDatabase db = dbSF.getWritableDatabase();
		
		
		ret = sm.getRegisteredTrainings();
		newLecture = ret.keySet();
		
		//alte praktikas löschen
		/*for (String sLecture : oldLecture) {
			if (!newLecture.contains(sLecture)) {
				deletePraktika(sLecture);
				praktikas.remove(sLecture);
			}

		}*/
		
		//Über die Praktia iterieren.
		//Praktika die er noch in seine Liste hat, werden nicht überschrieben.
		//Die neuen Praktika werden im DB gespeichert.
		for (String sLecture : newLecture) {
			if (!oldLecture.contains(sLecture)) {
				//Map<String,String[2]> - Map mit Kursname -> Array<Prof,Status>
				valuePraktika = ret.get(sLecture);
				db.execSQL("INSERT INTO " + DB_TABLE_PRAKTIKA
						+ " (lecture, profName,groupNr,status) VALUES ('"
						+ sLecture + "','" + valuePraktika[0] + "','"
						+ valuePraktika[1] + "','" + valuePraktika[2] + "');");
				//ID des Praktikas holen die es beim Insert bekommen hat
				pID = getPraktikaID(sLecture);
				praktikas.put(pID, new Praktika(sLecture, valuePraktika[0],
						valuePraktika[1], valuePraktika[2], dbSF));
			}
		}
		db.close();
		return true;
	}

	@Override
	public boolean deletePraktika(String lecture) {
		Integer id = getPraktikaID(lecture);
		IPraktika p = getPraktika(lecture);
		//alle Partner löschen mit der er diesen Praktika zusammen bearbeitet
		p.deletePartnerLecture(lecture);
		//praktika aus dem hashmap löschen
		praktikas.remove(id);
		SQLiteDatabase db = dbSF.getWritableDatabase();
		//praktika aus dem DB löschen
		db.delete(DB_TABLE_PRAKTIKA, "id" + " = " + id, null);
		db.close();
		return true;
	}
	
	/**
	 * Beim Start werden alle Daten aus dem DB geholt und im HashMap praktika gespeichert
	 */
	private void getPraktikasfromDB() {

		SQLiteDatabase db = dbSF.getReadableDatabase();
		Cursor c = db.rawQuery(
				"SELECT * FROM "
						+ DB_TABLE_PRAKTIKA, null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					IPraktika p;
					Integer id = c.getInt(c.getColumnIndex("praID"));
					String lecture = c.getString(c.getColumnIndex("lecture"));
					String profName = c.getString(c.getColumnIndex("profName"));
					String groupNr = c.getString(c.getColumnIndex("groupNr"));
					String status = c.getString(c.getColumnIndex("status"));
					praktikas.put(id, p = new Praktika(lecture, profName, groupNr,
							status, dbSF));
					p.getPartnerfromDB();
				} while (c.moveToNext());
			}
		}
		db.close();
	}
	
	@Override
	public Integer getPraktikaID(String slecture) {
		SQLiteDatabase db = dbSF.getReadableDatabase();
		Integer id = 0;

		Cursor c = db.rawQuery("SELECT praID FROM " + DB_TABLE_PRAKTIKA
				+ " WHERE lecture ='" + slecture+"'", null);

		if (c != null) {
			if (c.moveToFirst()) {
				id = c.getInt(c.getColumnIndex("praID"));
			}
		}
		db.close();
		return id;
	}

	@Override
	public Set<String> getPraktikasLectureSet() {

		SQLiteDatabase db = dbSF.getReadableDatabase();
		Set<String> lectureSet = new HashSet<String>();

		Cursor c = db
				.rawQuery("SELECT lecture FROM " + DB_TABLE_PRAKTIKA, null);

		if (c != null) {
			if (c.moveToFirst()) {
				do {
					lectureSet.add(c.getString(c.getColumnIndex("lecture")));
				} while (c.moveToNext());
			}
		}
		db.close();

		return lectureSet;
	}

	@Override
	public IPraktika getPraktika(String slecture) {
		Integer pID = getPraktikaID(slecture);
		return praktikas.get(pID);
	}
	
	@Override
	public String getuserName(){
		return userName;
	}
	
	/**
	 * Die Activity die diesen instance aufruft musst seinen Context mitschicken.
	 * Ohne kann die DB nicht genutzt werden.
	 * @param context 
	 * @return instance Student
	 */
	public static Student getInstance(Context context) {
		if (instance == null) {
			instance = new Student(context);
		}
		return instance;
	}

}
