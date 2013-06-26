package com.example.haw_app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.haw_app.veranstaltungsplan.implementations.Veranstaltungsplan;

public class PcPoolPlanActivity extends Activity {
	Veranstaltungsplan vp;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pc_pool_plan);
		try{
		pcPoolPlannAktualisieren();
		}catch(Exception e){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(
				"Veranstaltungsplan.txt wurde nicht gefunden. Probiere bitte erst den Veranstaltungsplan zu aktualisieren!")
				.setNeutralButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
							}
						});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
		
	}

	public void pcPoolPlannAktualisieren(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage("Aktualisiert!").setNeutralButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		
		try {
			pcPoolPlannAktualisieren();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			builder.setMessage(
					"Veranstaltungsplan.txt wurde nicht gefunden. Probiere bitte erst den Veranstaltungsplan zu aktualisieren!")
					.setNeutralButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
								}
							});
		}
		
		AlertDialog alertDialog = builder.create();
		alertDialog.show();

	}

	public void pcPoolPlannAktualisieren() throws Exception {
		
			vp = VeranstaltungsplanActivity.getVp();
			if (vp.termine.size() == 0 || vp.equals(null)) {
				throw new Exception();
			} else {
				List<String> belegungsListe = new ArrayList<String>();
				for (Entry<String, String> entry : vp.pcPoolAktuell().entrySet()) {
					belegungsListe.add(entry.getKey() + "\n" + "Status: "
							+ entry.getValue() + "\n");
				}
				Collections.sort(belegungsListe);
				final ListView lv = (ListView) findViewById(R.id.pc_pool_plan_listview);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						R.layout.vp_simple_list_item, belegungsListe);

				lv.setAdapter(adapter);
			}
		
		

		
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
