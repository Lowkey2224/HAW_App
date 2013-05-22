package com.example.haw_app.schnitzeljagd;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import android.content.Context;

import com.example.haw_app.schnitzeljagd.http.Ziel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SchnitzelJagdGoalTools {
	 
	private Context context;
	private String json;
	private GoalSQLiteConnector sql;
	public SchnitzelJagdGoalTools(Context parent, String json) {
		this.context = parent;
		this.json = json;
		sql = new GoalSQLiteConnector(context);
		sql.deleteGoals();
		saveGoals();
	}



	public  boolean checkIfGoalExists(String code, double lat, double lon){
		
		List<Ziel>ints2 = sql.getGoals();
		if (ints2==null)
		{
			saveGoals();
			ints2 = sql.getGoals();
		}
		for(Ziel z : ints2)
		{
			if(!z.getErfuellt()){
				String oCode = z.getCode();
				if (oCode.equals(code))
				{
					double oLat = z.getLatitude();
					double oLon = z.getLongitude();
					double orad = z.getRadius();
					if(checkCoordinates(lat,lon,oLat,oLon,orad))
					{
						z.setErfuellt(true);
						sql.deleteGoals();
						sql.saveGoals(ints2);
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	

	private void saveGoals()
	{
		Gson gson = new Gson();
		Type collectionType = new TypeToken<Collection<Ziel>>(){}.getType();
		Collection<Ziel> ints2 = gson.fromJson(json, collectionType);
		GoalSQLiteConnector sql = new GoalSQLiteConnector(context);
		sql.saveGoals(ints2);		
	}
	
	int getNumberOfGoals()
	{
		Collection<Ziel> c = sql.getGoals();
		if(c==null)
		{
			saveGoals();
			c=sql.getGoals();
		}
		return c.size();
	}
	
	 int getNumberOfAccomplishedGoals()
	{		
		List<Ziel> l = sql.getGoals();
		if(l==null)
		{			
			saveGoals();
			l=sql.getGoals();
		}
		int i = 0;
		for(Ziel z : l)
		{
			i+=(z.getErfuellt())?1:0;
		}
		return i;
	}
	
	private  boolean checkCoordinates(double lat, double lon,
			double oLat, double oLon, double orad) {
		//Mit Pythagoras die Distanz zwischen Quellpunkt und gescanntem Punkt ermitteln
		double dist = Math.sqrt(Math.pow(oLat-lat, 2)+Math.pow(oLon-lon, 2));		
		return (dist<=orad);
	}	
}
