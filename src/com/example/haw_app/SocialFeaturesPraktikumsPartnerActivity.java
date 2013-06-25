package com.example.haw_app;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.haw_app.socialfeatures.implementation.Student;
import com.example.haw_app.socialfeatures.interfaces.IPartner;
import com.example.haw_app.socialfeatures.interfaces.IPraktika;
import com.example.haw_app.socialfeatures.interfaces.IStudent;

public class SocialFeaturesPraktikumsPartnerActivity extends Activity {
	IStudent student;
	String intentString;
	List<IPartner> partner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sf_praktikumspartner);
		// Show the Up button in the action bar.
		setupActionBar();
		student = Student.getInstance(this.getApplicationContext());
		partnerAktualisieren();
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

	@Override
	protected void onResume() {
		super.onResume();
		partnerAktualisieren();
	}

	public void partnerHinzufuegenClick(View view) {
		Intent intent = new Intent();
		intent.setClassName(getPackageName(), getPackageName()
				+ ".SocialFeaturesPartnerErstellenActivity");
		intent.putExtra("selected", intentString);
		startActivity(intent);
	}

	public void partnerAktualisieren() {
		intentString = getIntent().getStringExtra("selected").toString();
		IPraktika praktikum = student.getPraktika(intentString);
		// praktikum.createPartner("1234", "test", "surname", "email", "handy");

		partner = praktikum.getPartnerLecture(intentString);
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(partner.toString()).setNeutralButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
		
		
		TextView pratkikumTextView = (TextView) findViewById(R.id.textView1);
		pratkikumTextView.setText(intentString);
		List<String> partnerArray = new ArrayList<String>();
		for (int i = 0; i < partner.size(); i++) {
			partnerArray.add(partner.get(i).getFirstname() + " "
					+ partner.get(i).getSurname());
		}
		ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_1, partnerArray);

		final ListView lv = (ListView) findViewById(R.id.sf_praktikumspartner_listview);

		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent intent = new Intent();
				intent.setClassName(getPackageName(), getPackageName()
						+ ".SocialFeaturesPraktikumsPartnerZeigenActivity");
				intent.putExtra("matNr", partner.get(position).getMatNr());
				intent.putExtra("selected", intentString);
				startActivity(intent);
			}
		});
	}

}
