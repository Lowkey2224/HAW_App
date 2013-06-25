package com.example.haw_app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.haw_app.socialfeatures.implementation.Student;
import com.example.haw_app.socialfeatures.interfaces.IPraktika;
import com.example.haw_app.socialfeatures.interfaces.IStudent;

public class SocialFeaturesPartnerErstellenActivity extends Activity {
	IStudent student;
	String intentString;
	IPraktika praktikum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sf_partner_erstellen);

		student = Student.getInstance(this.getApplicationContext());
		intentString = getIntent().getStringExtra("selected").toString();
		praktikum = student.getPraktika(intentString);
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
		getMenuInflater().inflate(R.menu.social_features, menu);
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

	public void partnerHinzufuegenClick(View view) {
		EditText editVorname = (EditText) findViewById(R.id.editText1);
		String vorname = editVorname.getText().toString();
		EditText editNachname = (EditText) findViewById(R.id.editText2);
		String nachname = editNachname.getText().toString();
		EditText editMatNr = (EditText) findViewById(R.id.editText3);
		String matNr = editMatNr.getText().toString();
		EditText editEmail = (EditText) findViewById(R.id.editText4);
		String email = editEmail.getText().toString();
		EditText editHandy = (EditText) findViewById(R.id.editText5);
		String handy = editHandy.getText().toString();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(
				"Partner wurde erfolgreich erstellt!").setNeutralButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
//		try {
			praktikum.createPartner(matNr, vorname, nachname, email, handy);
//		} catch (Exception e) {
//
//			builder.setMessage(
//					"Partner konnte nicht erstellt werden! Error: "
//							+ e).setNeutralButton("OK",
//					new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog, int id) {
//						}
//					});
//		}
//		AlertDialog alertDialog = builder.create();
//		alertDialog.show();
		startActivity(new Intent(SocialFeaturesPartnerErstellenActivity.this,SocialFeaturesPraktikaActivity.class));
	}
}
