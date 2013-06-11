package com.example.haw_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VeranstaltungsplanActivity extends Activity {


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vp_menu);
	}
	
	public void anzeigenClick(View view) {
		startActivity(new Intent(VeranstaltungsplanActivity.this,VeranstaltungsplanAnzeigenActivity.class));
	}

	public void exportierenClick(View view) {
		startActivity(new Intent(VeranstaltungsplanActivity.this,VeranstaltungsplanExportierenActivity.class));
	}

}
