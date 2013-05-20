package com.example.haw_app.schnitzeljagd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteConnector extends SQLiteOpenHelper {

	public SQLiteConnector(Context context) {
		super(context, dbName, null, dbversion);
		// TODO Auto-generated constructor stub
	}

	private static final String dbName = "GPSSB";
	private static final int dbversion = 1;
	private static final String tableName = "loc";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + tableName +"(longitude REAL,latitude REAL)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
