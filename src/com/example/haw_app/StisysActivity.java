package com.example.haw_app;

import java.text.DecimalFormat;
import java.util.Set;
import com.example.haw_app.stisysManager.IStiSysManager;
import com.example.haw_app.stisysManager.StiSysManagerFactory;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class StisysActivity extends Activity {
	EditText editLogin;
	EditText editPassword;
	Button buttonLogin;
	IStiSysManager sm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_stisys_login);
		editLogin = (EditText) findViewById(R.id.editLogin);
		editPassword = (EditText) findViewById(R.id.editPassword);
		findViewById(R.id.buttonLogin).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						final String loginName = editLogin.getText().toString();
						final String loginPassword = editPassword.getText()
								.toString();
						Thread stisysThread = new Thread() {
							public void run() {

								sm = StiSysManagerFactory.getInstance(
										loginName, loginPassword);

								// sm = StiSysManagerFactory.getInstance();
							}
						};
						stisysThread.start();
						try {
							stisysThread.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						//
						setContentView(R.layout.activity_stisys);
						// Show the Up button in the action bar.

						setupActionBar();
						createPrintquote();
						//
					}
				});
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stisys, menu);
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

	private void createPrintquote() {
		TextView printsm = (TextView) findViewById(R.id.printNumber);
		double print = (double) sm.getPrintCredit() / 100;
		printsm.setText(new DecimalFormat("#0.00").format(print) + " Euro");
	}

	public void kommendeAnmeldungenClick(View view) {
		setContentView(R.layout.test);
	}

	public void meineAnmeldungenClick(View view) {
		setContentView(R.layout.stisys_meine_anmeldungen);
		// Praktika Anzeige
		TextView praktikasm = (TextView) findViewById(R.id.praktikaList);
		Set<String> praktika = sm.getRegisteredTrainings().keySet();
		praktikasm.setText(praktika.toString());
		// Klausuren Anzeige
		TextView klausurensm = (TextView) findViewById(R.id.klausurenList);
		Set<String> klausuren = sm.getRegisteredTests().keySet();
		klausurensm.setText(klausuren.toString());
		// WP-Kurse Anzeige
		TextView wpsm = (TextView) findViewById(R.id.wpKurseList);
		Set<String> wp = sm.getRegisteredChosenCourses().keySet();
		wpsm.setText(wp.toString());
		// GW-Kurse Anzeige
		TextView gwsm = (TextView) findViewById(R.id.gwKurseList);
		Set<String> gw = sm.getRegisteredSocialSciencesCourses().keySet();
		gwsm.setText(gw.toString());
		// Projekte-Kurse Anzeige
		TextView projektesm = (TextView) findViewById(R.id.projekteList);
		Set<String> projekte = sm.getRegisteredTrainings().keySet();
		projektesm.setText("TODO");
		// Seminar-Kurse Anzeige
		TextView seminarsm = (TextView) findViewById(R.id.seminareList);

		Set<String> seminar = sm.getRegisteredTrainings().keySet();
		seminarsm.setText("TODO");
	}

	public void anmeldenClick(View view) {
		setContentView(R.layout.test);
	}

	public void abmeldenClick(View view) {
		setContentView(R.layout.test);
	}

	public void meineNotenClick(View view) {
		setContentView(R.layout.test);
	}

	public void einstellungenStisysClick(View view) {
		setContentView(R.layout.test);
	}
}