package com.example.haw_app;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.haw_app.veranstaltungsplan.implementations.Termin;
import com.example.haw_app.veranstaltungsplan.implementations.Veranstaltungsplan;

public class VeranstaltungsplanAnzeigenActivity extends
		VeranstaltungsplanActivity {
	
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		vpAnzeigenLayout();
	}

	private void vpAnzeigenLayout() {

		setContentView(R.layout.vp_anzeigen);
		
	}
	
	public void vpAktualisieren(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage("Veranstaltungsplan konnte nicht angezeigt werden!")
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});

		try {
			List<Termin> termineList = getTermineList();
			String[] termineArray = new String[termineList.size()];

			for (int i = 0; i < termineList.size(); i++) {
				termineArray[i] = termineList.get(i).name();
			}
			ListAdapter adapter = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_list_item_1, termineArray);

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
	
	public List<Termin> getTermineList() throws Exception {
		Veranstaltungsplan vp = Veranstaltungsplan.getInstance();
		vp.aktualisieren();
		return vp.termine;
	}
	
	
}