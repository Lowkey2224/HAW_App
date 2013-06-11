package com.example.haw_app.veranstaltungsplan.implementations;

import java.util.Date;
import java.util.List;

import com.example.haw_app.VeranstaltungsplanAnzeigenActivity;

import android.os.AsyncTask;

public class VeranstaltungsplanTask extends AsyncTask<VeranstaltungsplanAnzeigenActivity, Void, Veranstaltungsplan>{

	private VeranstaltungsplanAnzeigenActivity activity;
	
	@Override
	protected Veranstaltungsplan doInBackground(VeranstaltungsplanAnzeigenActivity... arg0) {
		
		this.activity = arg0[0];
		Veranstaltungsplan vp = activity.vp;
		
		vp.aktualisieren();
		
		return vp;
		
	}
	
	@Override
	protected void onPostExecute(Veranstaltungsplan result) {
		activity.vp = result;
	}
	
	public Veranstaltungsplan getVP() {
		return vp;
	}

}
