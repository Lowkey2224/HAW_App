package com.example.haw_app.mensa.implementation;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.example.haw_app.mensa.interfaces.IMenuItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public final class MensaUtils {

	private static final String DEFAULT_SERVER_URL = "http://ec2-176-34-76-54.eu-west-1.compute.amazonaws.com:8080/HawServer/";
	private static final String MENU_BASE_PATH = DEFAULT_SERVER_URL
			+ "rest/menu";

	private MensaUtils() {
	}

	@SuppressWarnings("unchecked")
	public static List<? extends IMenuItem> getMenuItemsForDate(Date date) {
		Reader reader = retrieveReader(MENU_BASE_PATH + "/" + date.getTime());
		if (reader != null) {
			Type token = new TypeToken<List<MenuItem>>() {
			}.getType();
			Gson gson = new Gson();
			try {
				return (List<MenuItem>) gson.fromJson(reader, token);
			} catch (Exception e) {
				Log.w(MensaUtils.class.getSimpleName(),
						"Error retrieving available menus", e);
				return new ArrayList<IMenuItem>();
			}
		} else
			return new ArrayList<IMenuItem>();
	}

	@SuppressWarnings("unchecked")
	public static List<Date> getAvailableMenus() {
		Reader reader = retrieveReader(MENU_BASE_PATH);
		if (reader != null) {
			Type token = new TypeToken<List<Long>>() {
			}.getType();
			Gson gson = new Gson();
			try {
				List<Long> listLong = (List<Long>) gson.fromJson(reader, token);
				List<Date> result = new ArrayList<Date>();
				for (long timeMs : listLong) {
					result.add(new Date(timeMs));
				}
				return result;
			} catch (Exception e) {
				Log.w(MensaUtils.class.getSimpleName(),
						"Error retrieving available menus", e);
				return new ArrayList<Date>();
			}
		} else
			return new ArrayList<Date>();

	}

	private static InputStreamReader retrieveReader(String url) {

		DefaultHttpClient client = new DefaultHttpClient();

		HttpGet getRequest = new HttpGet(url);

		try {

			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(MensaUtils.class.getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();
			return new InputStreamReader(getResponseEntity.getContent());

		} catch (IOException e) {
			getRequest.abort();
			Log.w(MensaUtils.class.getSimpleName(), "Error for URL " + url, e);
		}

		return null;

	}

}
