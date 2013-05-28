package com.example.haw_app.stisysManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.joda.time.DateTime;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StisysUtil {

	// Helper Method for Converting DateTimes
	public static Map<String, DateTime[]> tableToDateMap(Elements table) {
		Map<String, DateTime[]> app = new HashMap<String, DateTime[]>();

		for (Element e : table) {
			Iterator<Element> ite = e.select("td").iterator();
			DateTime[] date = new DateTime[2];

			String key = ite.next().text(); // 0.elem key
			ite.next(); // 1 elem skip
			date[0] = getDate(ite.next().text()); // 2 elem date 1
			ite.next(); // 3 elem skip
			date[1] = getDate(ite.next().text()); // 4 elem date 2

			app.put(key, date);
		}
		return app;
	}

	// Helper Method for converting DateTimes
	public static DateTime getDate(String date) {
		int day = Integer.parseInt("" + date.charAt(0) + date.charAt(1));
		int month = Integer.parseInt("" + date.charAt(3) + date.charAt(4));
		int year = Integer.parseInt("" + date.charAt(6) + date.charAt(7)
				+ date.charAt(8) + date.charAt(9));
		int hour = Integer.parseInt("" + date.charAt(11) + date.charAt(12));
		int minute = Integer.parseInt("" + date.charAt(14) + date.charAt(15));
		return new DateTime(year, month, day, hour, minute, 0, 0);
	}

	// Helper Method for Converting DateTimes
	public static DateTime getDateNoTime(String date) {
		int day = Integer.parseInt("" + date.charAt(0) + date.charAt(1));
		int month = Integer.parseInt("" + date.charAt(3) + date.charAt(4));
		int year = Integer.parseInt("" + date.charAt(6) + date.charAt(7)
				+ date.charAt(8) + date.charAt(9));
		return new DateTime(year, month, day, 0, 0, 0, 0);
	}
	
	
	public static double getPerCent(double w, double g){
		return (w/g)*100d;
	}

}
