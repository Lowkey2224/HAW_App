package com.example.haw_app.schnitzeljagd;

import java.util.List;

import com.example.haw_app.schnitzeljagd.http.Ziel;
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
	private static final String tableName = "Goals";

	/*
	private Integer id;
    private String name;
    private Double longitude;
    private Double latitude;
    private String code;
    private Double radius;
    private Schnitzeljagd schnitzeljagdId;
	*/
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + tableName +"(IDgoal INT,name VARCHAR, longitutde REAL, latitude REAL, code String, radius REAL, idSchnizeljagt INT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	boolean saveGoals(List<Ziel> goalList)
	{
		return false;
	}
	
}
