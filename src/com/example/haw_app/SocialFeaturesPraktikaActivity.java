package com.example.haw_app;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.haw_app.socialfeatures.interfaces.IStudent;
import com.example.haw_app.veranstaltungsplan.implementations.Termin;

public class SocialFeaturesPraktikaActivity extends Activity {
	IStudent student;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sf_menu);
		// Show the Up button in the action bar.
		setupActionBar();
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
    	student.updatePraktikas();
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage("Praktika-Liste konnte nicht angezeigt werden!")
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});

		try {
			Set<String> pratikaSet = student.getPraktikasLectureSet();
			
			String[] pratikaArray = new String[pratikaSet.size()];
			int i = 0;
			for ( Iterator<String> it = pratikaSet.iterator(); it.hasNext(); )
			{
				pratikaArray[i] = (String)it.next();
			    i++;
			}
			ListAdapter adapter = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_list_item_1, pratikaArray);

			final ListView lv = (ListView) findViewById(R.id.veranstaltungsplan_listview);

			lv.setAdapter(adapter);
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent intent = new Intent();
					intent.setClassName(getPackageName(), getPackageName()
							+ ".VeranstaltungsplanTerminActivity");
					intent.putExtra("selected", lv.getAdapter().getItem(arg2)
							.toString());
					startActivity(intent);
				}
			});

		} catch (Exception e) {
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		}
    }
    

}
