package com.example.haw_app.veranstaltungsplan.implementations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadThread extends AsyncTask<File, Void, File>{
	private static final String url = "http://ec2-176-34-76-54.eu-west-1.compute.amazonaws.com:8080/HawServer/files/Sem_I.txt";
	@Override
	protected File doInBackground(File... params) {
		
		InputStreamReader is = new RetrieveReaderThread().doInBackground(url);
		
		BufferedReader br = new BufferedReader(is);
		
		FileWriter fw;
		try {
			fw = new FileWriter(params[0]);
		
		
		String line = br.readLine();
		
		while (line != null) {
			fw.write(line);
			br.readLine();
		}
		
		fw.flush();
		fw.close();
		
		br.close();
		} catch (IOException e) {			
			return null;
		}
		return params[0];
	}

	
	private static InputStreamReader retrieveReader(String url) {

		DefaultHttpClient client = new DefaultHttpClient();

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
