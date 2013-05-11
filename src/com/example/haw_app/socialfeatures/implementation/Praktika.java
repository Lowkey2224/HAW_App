package com.example.haw_app.socialfeatures.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.haw_app.database.DatabaseSocialFeatures;
import com.example.haw_app.socialfeatures.interfaces.IPartner;
import com.example.haw_app.socialfeatures.interfaces.IPraktika;

@SuppressLint("UseSparseArrays")
public class Praktika implements IPraktika {

	private String lecture = "";
	private String profName = "";
	private String groupNr = "";
	private String status = "not ok";
	private Map<Integer, IPartner> partners = new HashMap<Integer, IPartner>();

	private static final String DB_TABLE_PARTNER = DatabaseSocialFeatures.DB_TABLE_PARTNER;
	DatabaseSocialFeatures dbSF;

	public Praktika(String lecture, String profName, String groupNr,
			String status, DatabaseSocialFeatures dbSF) {
		this.lecture = lecture;
		this.profName = profName;
		this.groupNr = groupNr;
		this.status = status;
		this.dbSF = dbSF;
	}

	@Override
	public boolean createPartner(String matNr, String firstname,
		String surname, String email, String handy) {
		Integer pID = null;
		if (getPartnerID(matNr) == null) {
			SQLiteDatabase db = dbSF.getWritableDatabase();
			db.execSQL("INSERT INTO "
					+ DB_TABLE_PARTNER
					+ " (matNr,firstname, surname,email,handy,lecture) VALUES ('"
					+ matNr + "','" + firstname + "','" + surname + "','"
					+ email + "','" + handy + "','" + lecture + "');");
			pID = getPartnerID(matNr);
			partners.put(pID, new Partner(matNr, firstname, surname, email,
					handy, lecture));
			db.close();
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updatePartner(String matNr, String firstname,
			String surname, String email, String handy) {
		SQLiteDatabase db = dbSF.getWritableDatabase();
		Integer pID = null;
		db.execSQL("UPDATE " + DB_TABLE_PARTNER + " SET firstname = '"
				+ firstname + "', surname = '" + surname + "', matNr = '"
				+ matNr + "'," + "email = '" + email + "', handy = '" + handy
				+ "' " + "WHERE matNr = '" + matNr + "';");
		db.close();

		IPartner p = partners.get(matNr);
		p.setFirstname(firstname);
		p.setSurname(surname);
		p.setEMail(email);
		p.setHandy(handy);
		pID = getPartnerID(matNr);
		partners.put(pID, p);
		return true;
	}

	@Override
	public boolean deletePartnerID(String matNr) {
		// Sollte der Partner noch in einem anderen Praktika sein, bleibt er
		// noch erhalten
		SQLiteDatabase db = dbSF.getWritableDatabase();
		Integer pID = null;
		pID = getPartnerID(matNr);
		db.delete(DB_TABLE_PARTNER, "pID" + " = '" + pID + "'", null);
		db.close();

		return partners.remove(pID) != null;
	}
	
	@Override
	public boolean deletePartnerLecture(String lecture) {
		List<IPartner> pList = new ArrayList<IPartner>();
		pList = getPartnerLecture(lecture);
		for (IPartner p : pList){
			deletePartnerID(p.getMatNr());
		}
		return true;
	}

	@Override
	public void getPartnerfromDB() {
		SQLiteDatabase db = dbSF.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT * FROM " + DB_TABLE_PARTNER, null);

		if (c != null) {
			if (c.moveToFirst()) {
				do {
					Integer id = c.getInt(c.getColumnIndex("parID"));
					String matNr = c.getString(c.getColumnIndex("matNr"));
					String firstname = c.getString(c
							.getColumnIndex("firstname"));
					String surname = c.getString(c.getColumnIndex("surname"));
					String email = c.getString(c.getColumnIndex("email"));
					String handy = c.getString(c.getColumnIndex("handy"));
					partners.put(id, new Partner(matNr, firstname, surname,
							email, handy, lecture));
				} while (c.moveToNext());
			}
		}
		db.close();
	}

	@Override
	public Integer getPartnerID(String matNr) {
		SQLiteDatabase db = dbSF.getReadableDatabase();
		Integer id = null;

		Cursor c = db.rawQuery("SELECT parID FROM " + DB_TABLE_PARTNER
				+ " WHERE matNr = '" + matNr + "'", null);

		if (c != null) {
			if (c.moveToFirst()) {
				id = c.getInt(c.getColumnIndex("parID"));
			}
		}
		db.close();
		return id;
	}

	@Override
	public List<IPartner> getPartnerLecture(String slecture) {
		List<IPartner> pList = new ArrayList<IPartner>();
		SQLiteDatabase db = dbSF.getReadableDatabase();
		Integer id = 0;

		Cursor c = db.rawQuery("SELECT parID FROM " + DB_TABLE_PARTNER
				+ " WHERE lecture = '" + slecture + "'", null);

		if (c != null) {
			if (c.moveToFirst()) {
				id = c.getInt(c.getColumnIndex("parID"));
				pList.add(partners.get(id));
			}
		}
		db.close();
		return pList;
	}

	@Override
	public IPartner getPartner(String matNr) {
		return partners.get(getPartnerID(matNr));
	}

	@Override
	public String getLecture() {
		return lecture;
	}

	@Override
	public String getProfName() {
		return profName;
	}

	@Override
	public String getGroupNr() {
		return groupNr;
	}

	@Override
	public String getStatus() {
		return status;
	}

}
