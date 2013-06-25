package com.example.haw_app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.haw_app.veranstaltungsplan.implementations.Veranstaltungsplan;
import com.example.haw_app.veranstaltungsplan.implementations.VeranstaltungsplanTask;

public class VeranstaltungsplanActivity extends Activity {
	public static Veranstaltungsplan vp;
	private ProgressDialog mDialog;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vp_menu);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		try {
			vp = Veranstaltungsplan.getInstance();
		} catch (Exception e) {
			builder.setMessage(
					"Fehler beim laden des Veranstaltungsplans! Error: "
							+ e.toString()).setNeutralButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
						}
					});
		}
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	public void vpAktualisieren(Veranstaltungsplan vpnew) {
		vp = vpnew;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage("Veranstaltungsplan.txt wurde aktualisiert!")
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
		mDialog.dismiss();
	}

	public static Veranstaltungsplan getVp() {
		return vp;
	}

	public void anzeigenClick(View view) {
		startActivity(new Intent(this, VeranstaltungsplanAnzeigenActivity.class));
	}

	public void exportierenClick(View view) {
		startActivity(new Intent(this, VeranstaltungsplanExportierenActivity.class));
	}

	public void aktualisierenClick(View view) {
		mDialog = ProgressDialog.show(VeranstaltungsplanActivity.this,
				"Downloade Veranstaltungsplan", "Loading...");
		try {
			new VeranstaltungsplanTask().execute(this);
		} catch (Exception e) {
			mDialog.dismiss();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage(
					"Veranstaltungsplan.txt konnte nicht aktualisiert werden! Error: "
							+ e).setNeutralButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
						}
					});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		}
		;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.optionen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
