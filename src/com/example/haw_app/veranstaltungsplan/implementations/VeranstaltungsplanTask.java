package com.example.haw_app.veranstaltungsplan.implementations;

import com.example.haw_app.VeranstaltungsplanActivity;

import android.os.AsyncTask;

public class VeranstaltungsplanTask extends
		AsyncTask<VeranstaltungsplanActivity, Void, Veranstaltungsplan> {

	private VeranstaltungsplanActivity activity;

	@Override
	protected Veranstaltungsplan doInBackground(
			VeranstaltungsplanActivity... arg0) {

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
