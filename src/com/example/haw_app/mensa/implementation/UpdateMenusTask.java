package com.example.haw_app.mensa.implementation;

import java.util.Date;
import java.util.List;

import com.example.haw_app.MensaActivity;

import android.os.AsyncTask;

public class UpdateMenusTask extends AsyncTask<MensaActivity, Void, List<Date>> {

	private MensaActivity activity;

	@Override
	protected List<Date> doInBackground(MensaActivity... arg0) {
		this.activity = arg0[0];
		return MensaUtils.getAvailableMenus();
	}

	@Override
	protected void onPostExecute(List<Date> result) {
		activity.setDates(result);
	}

}
