package com.example.haw_app.socialfeatures.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.haw_app.database.Database;
import com.example.haw_app.socialfeatures.interfaces.IPartner;
import com.example.haw_app.socialfeatures.interfaces.IPraktika;

public class Praktika implements IPraktika {

	private String lecture = "";
	private String profName = "";
	private String groupNr = "";
	private String status = "not ok";

	private static final String DB_TABLE_PARTNER = Database.DB_TABLE_PARTNER;

	private Map<String, IPartner> partners = new HashMap<String, IPartner>();

	public Praktika(String lecture, String profName, String groupNr,
			String status) {
		this.lecture = lecture;
		this.profName = profName;
		this.groupNr = groupNr;
		this.status = status;
	}

	@Override
	public boolean createPartner(String matNr, String firstname,
			String surname, String email, String handy,
			Database dbSF) {
		SQLiteDatabase db = dbSF.getWritableDatabase();
		db.execSQL("INSERT INTO " + DB_TABLE_PARTNER
				+ "(matNr,firstname, surname,email,handy,lecture) VALUES('"
				+ matNr + "','" + firstname + "','" + surname + "','" + email
				+ "','" + handy + "','" + lecture + "')");
		db.close();
		return partners.put(matNr, new Partner(matNr, firstname, surname,
				email, handy, lecture)) != null;
	}

	@Override
	public boolean updatePartner(String matNr, String firstname,
			String surname, String email, String handy,
			Database dbSF) {

		SQLiteDatabase db = dbSF.getWritableDatabase();
		db.execSQL("UPDATE " + DB_TABLE_PARTNER + " SET firstname = '"
				+ firstname + "', surname = '" + surname + "', matNr = '"
				+ matNr + "'," + "email = '" + email + "', handy = '" + handy
				+ "' " + "WHERE matNr = '" + matNr + "'");
		db.close();

		IPartner p = partners.get(matNr);
		p.setFirstname(firstname);
		p.setSurname(surname);
		p.setEMail(email);
		p.setHandy(handy);
		partners.put(matNr, p);
		return true;
	}

	@Override
	public boolean deletePartner(String matNr, Database dbSF) {
		SQLiteDatabase db = dbSF.getWritableDatabase();
		db.delete(DB_TABLE_PARTNER, "matNr" + " = '" + matNr+"'", null);
		db.close();

		return partners.remove(matNr) != null;
	}

	@Override
	public void getPartnerAll(Database dbSF) {
		SQLiteDatabase db = dbSF.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT * FROM " + DB_TABLE_PARTNER
				+ " WHERE lecture = '" + lecture + "'", null);

		if (c != null) {
			if (c.moveToFirst()) {
				do {
					String matNr = c.getString(c.getColumnIndex("matNr"));
					String firstname = c.getString(c
							.getColumnIndex("firstname"));
					String surname = c.getString(c.getColumnIndex("surname"));
					String email = c.getString(c.getColumnIndex("email"));
					String handy = c.getString(c.getColumnIndex("handy"));
					partners.put(matNr, new Partner(matNr, firstname, surname,
							email, handy, lecture));
				} while (c.moveToNext());
			}
		}
		db.close();
	}

	@Override
	public List<IPartner> getPartner(String slecture) {
		Set<String> matNrSet = partners.keySet();
		List<IPartner> pList = new ArrayList<IPartner>();
		for (String matNr : matNrSet) {
			IPartner p = partners.get(matNr);
			if (p.getLecture().contains(slecture))
				pList.add(p);
		}
		return pList;
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
