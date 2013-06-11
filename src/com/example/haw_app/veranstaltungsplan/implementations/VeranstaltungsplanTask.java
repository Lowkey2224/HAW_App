package com.example.haw_app.veranstaltungsplan.implementations;

import android.os.AsyncTask;

public class VeranstaltungsplanTask extends AsyncTask<Veranstaltungsplan, Void, Veranstaltungsplan>{

	private Veranstaltungsplan vp;
	
	public VeranstaltungsplanTask() {};
	
	@Override
	protected Veranstaltungsplan doInBackground(Veranstaltungsplan... arg0) {
		
		this.vp = arg0[0];
		
		try {
			vp.aktualisieren();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vp;
	}
	
	public Veranstaltungsplan getVP() {
		return vp;
	}

}
