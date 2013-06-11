package com.example.haw_app.veranstaltungsplan.implementations;

import com.example.haw_app.VeranstaltungsplanAnzeigenActivity;

import android.os.AsyncTask;

public class VeranstaltungsplanTask extends
		AsyncTask<VeranstaltungsplanAnzeigenActivity, Void, Veranstaltungsplan> {

	private VeranstaltungsplanAnzeigenActivity activity;

	@Override
	protected Veranstaltungsplan doInBackground(
			VeranstaltungsplanAnzeigenActivity... arg0) {

		this.activity = arg0[0];
		Veranstaltungsplan vp = activity.vp;

		try {
			vp.aktualisieren();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vp;

	}

	@Override
	protected void onPostExecute(Veranstaltungsplan result) {
		activity.vpAktualisieren(result);
	}

}
