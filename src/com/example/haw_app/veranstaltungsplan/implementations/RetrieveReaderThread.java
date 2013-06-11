package com.example.haw_app.veranstaltungsplan.implementations;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class RetrieveReaderThread extends AsyncTask<String, Void, InputStreamReader>{

	@Override
	protected InputStreamReader doInBackground(String... params) {
		DefaultHttpClient client = new DefaultHttpClient();
		String url = params[0];
		HttpGet getRequest = new HttpGet(url);
		try {
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(Veranstaltungsplan.class.getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();
			return new InputStreamReader(getResponseEntity.getContent());

		} catch (IOException e) {
			getRequest.abort();
			Log.w(Veranstaltungsplan.class.getSimpleName(), "Error for URL " + url, e);
		}

		return null;
	}

}
