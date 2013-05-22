package com.example.haw_app.schnitzeljagd;

import java.lang.reflect.Type;
import java.util.Collection;

import com.example.haw_app.schnitzeljagd.http.Ziel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SchnitzelJagdGoalTools {
	 
	public static boolean checkIfGoalExists(String json,String code, double lat, double lon){
		Collection<Ziel> ints2 = getGoalsFromJsonString(json);
		for(Ziel z : ints2)
		{
			String oCode = z.getCode();
			if (oCode.equals(code))
			{
				double oLat = z.getLatitude();
				double oLon = z.getLongitude();
				double orad = z.getRadius();
				if(checkCoordinates(lat,lon,oLat,oLon,orad))
				{
					return true;
				}
			}
		}
		
		return false;
	}

	static Collection<Ziel> getGoalsFromJsonString(String jsonString)
	{
		Gson gson = new Gson();
		Type collectionType = new TypeToken<Collection<Ziel>>(){}.getType();
		Collection<Ziel> ints2 = gson.fromJson(jsonString, collectionType);			
		return ints2;
	}
	
	static int getNumberOfGoals(String jsonString)
	{
		Collection<Ziel> c = getGoalsFromJsonString(jsonString);		
		return c.size();
	}
	
	private static boolean checkCoordinates(double lat, double lon,
			double oLat, double oLon, double orad) {
		//Mit Pythagoras die Distanz zwischen Quellpunkt und gescanntem Punkt ermitteln
		double dist = Math.sqrt(Math.pow(oLat-lat, 2)+Math.pow(oLon-lon, 2));		
		return (dist<=orad);
	}
}
