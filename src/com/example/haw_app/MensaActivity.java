package com.example.haw_app;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.haw_app.mensa.implementation.UpdateMenusTask;

public class MensaActivity extends Activity {

	ArrayAdapter<String> adapter;
	private DateFormat df = new SimpleDateFormat("dd.MM.yyyy",Locale.GERMANY);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mensa);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new ArrayList<String>());
		final ListView listview = (ListView) findViewById(R.id.mensa_listview);
		Log.w(this.getClass().getSimpleName(), "ListView: " + listview);
		Log.w(this.getClass().getSimpleName(), "ListView: " + adapter);
		listview.setAdapter(adapter);
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		final MensaActivity self = this;
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(self, MenuItemsActivity.class);

				String rawDate = (String) listview.getItemAtPosition(position);
				Date date=null;
				try{
					date=df.parse(rawDate);
				} catch (ParseException e){
					Log.w(this.getClass().getSimpleName(), e.getMessage());
				}
				if (date != null) {
					intent.putExtra("date", date.getTime());
					startActivity(intent);
				}
			}

		});
		// Show the Up button in the action bar.
		new UpdateMenusTask().execute(this);
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
		getMenuInflater().inflate(R.menu.mensa, menu);
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

	public void setDates(List<Date> dates) {
		adapter.clear();
		for (Date date : dates) {
			Log.w(this.getClass().getSimpleName(), "Added item: " + date);
			adapter.add(df.format(date));
		}
		adapter.notifyDataSetChanged();
	}

}
