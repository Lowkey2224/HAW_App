package com.example.haw_app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSocialFeatures extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 7;
	// Database Name
	public static final String DATABASE_NAME = "haw_app";

	// Contacts table name
	public static final String DB_TABLE_PRAKTIKA = "praktika";
	public static final String DB_TABLE_PARTNER = "partner";

	private static final String CREATE_TABLE_PRAKTIKA = "create table praktika (praID integer primary key autoincrement, "
			+ "lecture"	+ " TEXT,"
			+ "profName"+ " TEXT,"
			+ "groupNr"	+ " TEXT," 
			+ "status" + " TEXT" + ");";

	private static final String CREATE_TABLE_PARTNER = "create table partner (parID integer primary key autoincrement, "
			+ "firstname" + " TEXT,"
			+ "surname"	+ " TEXT,"
			+ "matNr"	+ " TEXT,"
			+ "email" + " TEXT," 
			+ "handy" + " TEXT,"
			+ "lecture" + " TEXT" + ");";

	public DatabaseSocialFeatures(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_PRAKTIKA);
		db.execSQL(CREATE_TABLE_PARTNER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PRAKTIKA);
		db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PARTNER);

		// Create tables again
		onCreate(db);
	}

}
