package com.example.haw_app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.haw_app.mensa.implementation.UpdateMenuItemsTask;
import com.example.haw_app.mensa.interfaces.IMenuItem;

public class MenuItemsActivity extends Activity {

	private ArrayAdapter<IMenuItem> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		long dateMs = extras.getLong("date");
		Date date = new Date(dateMs);
		setContentView(R.layout.activity_menuitems);
		adapter = new ArrayAdapter<IMenuItem>(this,
				android.R.layout.simple_list_item_1, new ArrayList<IMenuItem>());
		final ListView listview = (ListView) findViewById(R.id.menuitems_listview);
		Log.w(this.getClass().getSimpleName(), "ListView: " + listview);
		Log.w(this.getClass().getSimpleName(), "ListView: " + adapter);
		listview.setAdapter(adapter);
		new UpdateMenuItemsTask(date).execute(this);
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

	public void setMenuItems(List<IMenuItem> list) {
		adapter.clear();
		for (IMenuItem item : list) {
			Log.w(this.getClass().getSimpleName(), "Added item: " + item);
			adapter.add(item);
		}
		adapter.notifyDataSetChanged();
	}

}
