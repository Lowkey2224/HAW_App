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
import android.widget.TextView;

import com.example.haw_app.socialfeatures.implementation.Student;
import com.example.haw_app.socialfeatures.interfaces.IPartner;
import com.example.haw_app.socialfeatures.interfaces.IPraktika;
import com.example.haw_app.socialfeatures.interfaces.IStudent;

public class SocialFeaturesPraktikumsPartnerZeigenActivity extends Activity {
	IStudent student;
	IPraktika praktikum;
	IPartner partner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sf_praktikumspartner_zeigen);
		// Show the Up button in the action bar.
		setupActionBar();
		student = Student.getInstance(this.getApplicationContext());
		Intent intent = getIntent();
		praktikum = student.getPraktika(intent.getStringExtra(
				"selected").toString());

		partner = praktikum.getPartner(intent.getStringExtra("matNr")
				.toString());

		TextView textViewUeberschrift = (TextView) findViewById(R.id.textView6);
		textViewUeberschrift.setText(praktikum.getLecture());

		TextView textViewVorname = (TextView) findViewById(R.id.textView1);
		textViewVorname.setText("Vorname: " + partner.getFirstname());

		TextView textViewNachname = (TextView) findViewById(R.id.textView2);
		textViewNachname.setText("Nachname: " + partner.getSurname());

		TextView textViewMatNr = (TextView) findViewById(R.id.textView3);
		textViewMatNr.setText("MatNr: " + partner.getMatNr());

		TextView textViewEmail = (TextView) findViewById(R.id.textView4);
		textViewEmail.setText("E-Mail: " + partner.getEMail());

		TextView textViewHandy = (TextView) findViewById(R.id.textView5);
		textViewHandy.setText("Handy-Nr.: " + partner.getHandy());
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

	public void partnerLoeschenClick(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(
				"Möchtest du diesen Partner wirklich löschen?")
				.setPositiveButton("Ja",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								praktikum.deletePartnerID(partner.getMatNr());
								startActivity(new Intent(SocialFeaturesPraktikumsPartnerZeigenActivity.this,SocialFeaturesPraktikaActivity.class));
							}
						})
				.setNeutralButton("Nein",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int id) {
					}
				});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

}
