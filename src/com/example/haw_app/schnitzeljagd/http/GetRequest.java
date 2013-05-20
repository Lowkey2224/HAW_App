package com.example.haw_app.schnitzeljagd.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;

public class GetRequest extends AsyncTask<String, Void, String> {
	Context parent;
	public GetRequest(Context parent)
	{
		this.parent = parent;
	}

	
	@Override
	protected String doInBackground(String... params) {
		String ret = null;
		String str = "http://ec2-176-34-76-54.eu-west-1.compute.amazonaws.com:8080/HawServer/rest/ziel/all/10";
		try{
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response = httpclient.execute(new HttpGet(str));
	    StatusLine statusLine = response.getStatusLine();
	    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        response.getEntity().writeTo(out);
	        out.close();
	        ret = out.toString();
	        //..more logic
	    } else{
	        //Closes the connection.
	        response.getEntity().getContent().close();
	        throw new IOException(statusLine.getReasonPhrase());
	    }	    
		}catch(Exception e){
			
		}
		return ret;
	}
	
}
