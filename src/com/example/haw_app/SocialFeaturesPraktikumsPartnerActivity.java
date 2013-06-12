package com.example.haw_app;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.haw_app.socialfeatures.implementation.Student;
import com.example.haw_app.socialfeatures.interfaces.IPartner;
import com.example.haw_app.socialfeatures.interfaces.IPraktika;
import com.example.haw_app.socialfeatures.interfaces.IStudent;

public class SocialFeaturesPraktikumsPartnerActivity extends Activity {
	IStudent student;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sf_praktikumspartner);
		// Show the Up button in the action bar.
		setupActionBar();
		student = Student.getInstance(this.getApplicationContext());
		Intent intent = getIntent();
		IPraktika praktikum = student.getPraktika(intent.getStringExtra("selected").toString());
		praktikum.createPartner("1234", "test", "surname", "email", "handy");
		List<IPartner> partner = praktikum.getPartnerLecture(intent.getStringExtra("selected").toString());
		
		try {
			
			String[] partnerArray = new String[partner.size()];
			for (int i = 0; i < partner.size(); i++) {
				partnerArray[i] = partner.get(i).getFirstname() + " " + partner.get(i).getSurname();
			}
			ListAdapter adapter = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_list_item_1, partnerArray);

			final ListView lv = (ListView) findViewById(R.id.sf_praktikumspartner_listview);

			lv.setAdapter(adapter);
//			lv.setOnItemClickListener(new OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> arg0, View arg1,
//						int arg2, long arg3) {
//					Intent intent = new Intent();
//					intent.setClassName(getPackageName(), getPackageName()
//							+ ".SocialFeaturesPraktikumsPartnerActivity");
//					intent.putExtra("selected", lv.getAdapter().getItem(arg2)
//							.toString());
//					startActivity(intent);
//				}
//			});

		} catch (Exception e) {
//			builder.setMessage(e.toString());
//			AlertDialog alertDialog = builder.create();
//			alertDialog.show();
		}
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
	
    public void sfAktualisieren(View view){
    	
    }
    

}
