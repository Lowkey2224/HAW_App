package com.example.haw_app.schnitzeljagd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.haw_app.schnitzeljagd.http.Ziel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GoalSQLiteConnector extends SQLiteOpenHelper {

	public GoalSQLiteConnector(Context context) {
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
		db.execSQL("CREATE TABLE " + tableName +"(idGoal INT,name VARCHAR, longitutde REAL, latitude REAL, code String, radius REAL, erfuellt INT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	boolean saveGoals(Collection<Ziel> goalList)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		for(Ziel z : goalList)
		{
			ContentValues values = new ContentValues();
			values.put("idGoal", z.getId());
			values.put("name", z.getName());
			values.put("longitutde", z.getLongitude());
			values.put("latitude", z.getLatitude());
			values.put("code", z.getCode());
			values.put("radius", z.getRadius());
			values.put("erfuellt", (z.getErfuellt())?1:0);
			db.insert(tableName, null, values);
		}
		
		return true;
	}
	
	List<Ziel> getGoals()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("Select idGoal,code,latitude,longitutde,radius,name,erfuellt FROM `"+tableName+"`",null);
		List<Ziel> ret = null;
		if (cursor.getCount()>0)
		{
			ret = new ArrayList<Ziel>();
			while(cursor.moveToNext())
			{
				Ziel z = new Ziel();
				z.setId(cursor.getInt(cursor.getColumnIndex("idGoal")));
				z.setCode(cursor.getString(cursor.getColumnIndex("code")));
				z.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
				z.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitutde")));
				z.setRadius(cursor.getDouble(cursor.getColumnIndex("radius")));
				z.setName(cursor.getString(cursor.getColumnIndex("name")));				
				z.setErfuellt((cursor.getInt(cursor.getColumnIndex("erfuellt"))!=0)?true:false);
				ret.add(z);
			}
		}
		return ret;
	}
	
	boolean deleteGoals()
	{
		return (this.getWritableDatabase().delete(tableName, null, null)>0);
	}
}
