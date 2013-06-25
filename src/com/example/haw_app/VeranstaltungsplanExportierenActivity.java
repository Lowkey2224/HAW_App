package com.example.haw_app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.haw_app.veranstaltungsplan.implementations.Veranstaltungsplan;

public class VeranstaltungsplanExportierenActivity extends Activity {

	private ListView mainListView;
	private List<TerminForView> termine;
	private ArrayAdapter<TerminForView> listAdapter;
	private Veranstaltungsplan vp = VeranstaltungsplanActivity.getVp();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vp_exportieren);
		if (vp.termine.size() == 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage(
					"Veranstaltungsplan.txt wurde nicht gefunden. Probiere bitte erst zu aktualisieren!")
					.setNeutralButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
								}
							});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		} else {
		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.veranstaltungsplan_listview);

		// When item is tapped, toggle checked properties of CheckBox and
		// Termin.
		mainListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View item,
							int position, long id) {
						TerminForView termin = listAdapter.getItem(position);
						termin.toggleChecked();
						TerminViewHolder viewHolder = (TerminViewHolder) item
								.getTag();
						viewHolder.getCheckBox().setChecked(termin.isChecked());
					}
				});
		getTermineList();
		listAdapter = new TerminArrayAdapter(this, termine);
		mainListView.setAdapter(listAdapter);
		}
	}

	private static class TerminViewHolder {
		private CheckBox checkBox;
		private TextView textView;

		@SuppressWarnings("unused")
		public TerminViewHolder() {
		}

		public TerminViewHolder(TextView textView, CheckBox checkBox) {
			this.checkBox = checkBox;
			this.textView = textView;
		}

		public CheckBox getCheckBox() {
			return checkBox;
		}

		@SuppressWarnings("unused")
		public void setCheckBox(CheckBox checkBox) {
			this.checkBox = checkBox;
		}

		public TextView getTextView() {
			return textView;
		}

		@SuppressWarnings("unused")
		public void setTextView(TextView textView) {
			this.textView = textView;
		}
	}

	class TerminForView {
		private String termin;
		private boolean checked = false;

		public TerminForView() {
		}

		public TerminForView(String termin) {
			this.termin = termin;
		}

		public TerminForView(String termin, boolean checked) {
			this.termin = termin;
			this.checked = checked;
		}

		public String getTermin() {
			return termin;
		}

		public void setTermin(String termin) {
			this.termin = termin;
		}

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public String toString() {
			return termin;
		}

		public void toggleChecked() {
			checked = !checked;
		}
	}

	/** Custom adapter for displaying an array of Termin objects. */
	private static class TerminArrayAdapter extends ArrayAdapter<TerminForView> {

		private LayoutInflater inflater;

		public TerminArrayAdapter(Context context,
				List<TerminForView> terminList) {
			super(context, R.layout.simplerow, R.id.rowTextView, terminList);
			// Cache the LayoutInflate to avoid asking for a new one each time.
			inflater = LayoutInflater.from(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Termin to display
			TerminForView termin = (TerminForView) this.getItem(position);

			// The child views in each row.
			CheckBox checkBox;
			TextView textView;

			// Create a new row view
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.simplerow, null);

				// Find the child views.
				textView = (TextView) convertView
						.findViewById(R.id.rowTextView);
				checkBox = (CheckBox) convertView.findViewById(R.id.CheckBox01);

				// Optimization: Tag the row with it's child views, so we don't
				// have to
				// call findViewById() later when we reuse the row.
				convertView.setTag(new TerminViewHolder(textView, checkBox));

				// If CheckBox is toggled, update the termin it is tagged with.
				checkBox.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox cb = (CheckBox) v;
						TerminForView termin = (TerminForView) cb.getTag();
						termin.setChecked(cb.isChecked());
					}
				});
			}
			// Reuse existing row view
			else {
				// Because we use a ViewHolder, we avoid having to call
				// findViewById().
				TerminViewHolder viewHolder = (TerminViewHolder) convertView
						.getTag();
				checkBox = viewHolder.getCheckBox();
				textView = viewHolder.getTextView();
			}

			// Tag the CheckBox with the Termin it is displaying, so that we can
			// access the termin in onClick() when the CheckBox is toggled.
			checkBox.setTag(termin);

			// Display termin data
			checkBox.setChecked(termin.isChecked());
			textView.setText(termin.toString());

			return convertView;
		}

	}

	public Object onRetainNonConfigurationInstance() {
		return termine;
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

	public void vpExportieren(View view) {
		List<String> list = new ArrayList<String>();
		AlertDialog.Builder builder = DiaglogBuilder("Exportieren erfolgreich!");
		try {
			for (TerminForView termin : termine) {
				if (termin.checked) {
				list.add(termin.termin);
				}
			}
			vp.setzeBelegteFaecher(list);
			vp.exportieren();
		} catch (Exception e) {
			builder.setMessage("Exportieren war nicht erfolgreich! Error: " + e.toString());
		}
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.veranstaltungsplan, menu);
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

	public void getTermineList() {
		List<TerminForView> termineTmp = new ArrayList<TerminForView>();
		List<String> list = new ArrayList<String>();
		for (String termin : vp.veranstaltungsSet()) {
			list.add(termin);
		}
		Collections.sort(list);
		for (String termin : list) {
			termineTmp.add(new TerminForView(termin));
		}
		termine = termineTmp;
	}
	
	public AlertDialog.Builder DiaglogBuilder(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		return builder.setMessage(message).setNeutralButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
	}

}
