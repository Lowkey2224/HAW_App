package com.example.haw_app;

import java.util.List;

import android.app.Activity;
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
import com.example.haw_app.veranstaltungsplan.implementations.VeranstaltungsplanTask;

public class VeranstaltungsplanAnzeigenActivity extends
		Activity {

	public Veranstaltungsplan vp = Veranstaltungsplan.getInstance();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vp.setvpAnzeigeActivity(this);
		vpAnzeigenLayout();
	}

	private void vpAnzeigenLayout() {

		setContentView(R.layout.vp_anzeigen);

	}

	public void vpAktualisieren(View view) {
		new VeranstaltungsplanTask().execute(this);

	}

	public void vpAktualisieren(Veranstaltungsplan vp){
		List<Termin> termineList = vp.termine;
		
		String[] termineArray = new String[termineList.size()];

		for (int i = 0; i < termineList.size(); i++) {
			termineArray[i] = termineList.get(i).name() + "\n" + termineList.get(i).prof() + "\n" + termineList.get(i).raum() + "\n" + termineList.get(i).von() + "-" + termineList.get(i).bis();
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
	}

}