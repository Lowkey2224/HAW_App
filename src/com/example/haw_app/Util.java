package com.example.haw_app;

import org.joda.time.DateTime;

import android.app.Activity;
import android.content.Context;
import android.widget.PopupWindow;

public class Util {

	/**
	 * Creates a DateTime Object from the given String. It may be formated
	 * YYYY-MM-DD or YYYY-MM-DD hh:mm 
	 * @param date date must be formated in this way: YYYY-MM-DD or YYYY-MM-DD hh:mm  
	 * @return null if the parameter wasn't formated right
	 */
	public static DateTime createDateTimeFromString(String date)
	{
		if (date.trim().length()!= 10 || date.trim().length()!= 15 )
			return null;
		
		int year, month, day,hours,minutes;
		String[] ary = date.split("-");
		if(ary[0].length()!=4 || ary[1].length()!= 2 || (ary[2].length()!=2 && ary[2].length()!=8))
			return null;
		year = Integer.parseInt(ary[0]);
		month = Integer.parseInt(ary[1]);
		if(ary[2].length()==2){
			day = Integer.parseInt(ary[2]);
			hours = 0; minutes = 0;
		}
		else{
			String[] ary2 = ary[2].split(" ");
			day = Integer.parseInt(ary2[0]);
			String[] _tmp =  ary2[1].split(":");	
			hours = Integer.parseInt(_tmp[0]);
			minutes = Integer.parseInt(_tmp[1]);
		}
		return new DateTime(year,month,day,hours,minutes);
	}
	
	
}
