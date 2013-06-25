package com.example.haw_app;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;

import com.example.haw_app.stisysManager.IStiSysManager;
import com.example.haw_app.stisysManager.StiSysManager;
import com.example.haw_app.stisysManager.StiSysManagerFactory;
import com.example.haw_app.stisysManager.StisysUtil;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

public class StisysActivity extends Activity {
	static boolean isLogged = false;
	EditText editLogin;
	EditText editPassword;
	String loginName; 
	String loginPassword;
	Button buttonLogin;
	IStiSysManager sm;
	boolean untermenue = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		if(!isLogged){
		isLogged= true;
		setContentView(R.layout.act_stisys_login);
		editLogin = (EditText) findViewById(R.id.editLogin);
		editPassword = (EditText) findViewById(R.id.editPassword);
		findViewById(R.id.buttonLogin).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						loginName = editLogin.getText().toString();
						loginPassword = editPassword.getText()
								.toString();
						createInstance(loginName, loginPassword);
						setContentView(R.layout.activity_stisys);

						setupActionBar();
						createPrintquote();
					}
				});
		} else {
			createInstance(loginName, loginPassword);
			setContentView(R.layout.activity_stisys);
			createPrintquote();	
		}
	}
	
	
	private void createInstance(final String username,final String passwrd){
		final Context p = this;
		Thread stisysThread = new Thread() {
			public void run() {				sm = StiSysManagerFactory.getInstance(username, passwrd,p);
				
				
				String _tmp = sm.getUserName();
				if(_tmp==null || !_tmp.equals(username)){
				sm.syncData();
				}
			}
		};
		stisysThread.start();
		try {
			stisysThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
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
		getMenuInflater().inflate(R.menu.stisys, menu);
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

	private void createPrintquote() {
		TextView printsm = (TextView) findViewById(R.id.printNumber);
		double print = (double) sm.getPrintCredit() / 100;
		printsm.setText(new DecimalFormat("#0.00").format(print) + " Euro");
	}

	// TODO -> dateTime auch einbinden
	public void kommendeAnmeldungenClick(View view) {
		setContentView(R.layout.stisys_kommende_anmeldungen);
		untermenue = true;
		ListView lv = (ListView) findViewById(R.id.applicateListsm);
		List<String> apps = new ArrayList<String>(); 
		apps.add("<h>Kommende Anmeldungen");
		  for (Map.Entry<String, DateTime[]> entry : sm.getNextApplicationDates().entrySet()) {
			  for(int i = 0; i < entry.getValue().length; i+=2){
			  apps.add(entry.getKey() + ": von " + entry.getValue()[i] + " bis " + entry.getValue()[i+1]);
			  }
		  }
		 lv.setAdapter(new StiSysListAdapter(StisysActivity.this, R.layout.stisyslistview, apps));		  
		

	}

	public void meineAnmeldungenClick(View view) {
		setContentView(R.layout.stisys_meine_anmeldungen);
		untermenue = true;
		// ##### Lustige Listensache Anfang
		ListView praktikasm = (ListView) findViewById(R.id.praktikaList);
		List<String> content = new ArrayList<String>();
		content.add("<h>Praktika");
		content.addAll(sm.getRegisteredTrainings().keySet());
		content.add("<h>Klausuren");
		content.addAll(sm.getRegisteredTests().keySet());		
		content.add("<h>WP-Kurse");
		content.addAll(sm.getRegisteredChosenCourses().keySet());
		content.add("<h>GW-Kurse");
		content.addAll(sm.getRegisteredSocialSciencesCourses().keySet());
		content.add("<h>Projekte");
		content.add("TODO");
		content.add("<h>Seminare");
		content.add("TODO");
		praktikasm.setAdapter(new StiSysListAdapter(StisysActivity.this, R.layout.stisyslistview, content));
		//##### Lustige Listensache Ende
		
//		// Klausuren Anzeige
//		ListView klausurensm = (ListView) findViewById(R.id.klausurenList);
//		List<String> klausuren = new ArrayList<String>(sm.getRegisteredTests().keySet());
//		klausurensm.setAdapter(new StiSysListAdapter(StisysActivity.this, R.layout.stisyslistview, klausuren));
//		// WP-Kurse Anzeige
//		TextView wpsm = (TextView) findViewById(R.id.wpKurseList);
//		Set<String> wp = sm.getRegisteredChosenCourses().keySet();
//		wpsm.setText(wp.toString());
//		// GW-Kurse Anzeige
//		TextView gwsm = (TextView) findViewById(R.id.gwKurseList);
//		Set<String> gw = sm.getRegisteredSocialSciencesCourses().keySet();
//		gwsm.setText(gw.toString());
//		// Projekte-Kurse Anzeige
//		TextView projektesm = (TextView) findViewById(R.id.projekteList);
//		Set<String> projekte = sm.getRegisteredTrainings().keySet();
//		projektesm.setText("TODO");
//		// Seminar-Kurse Anzeige
//		TextView seminarsm = (TextView) findViewById(R.id.seminareList);
//
//		Set<String> seminar = sm.getRegisteredTrainings().keySet();
//		seminarsm.setText("TODO");
	}

	public void anmeldenClick(View view) {
		untermenue = true;
		setContentView(R.layout.stisys_anmelden);
		List<String> l = new ArrayList<String>();
		l.add("<h>Anmeldbare Kurse");
		l.addAll(sm.getUnsubscribeableCourses());
		ListView lv = (ListView)findViewById(R.id.lvAnmeldesm);
		StiSysListAdapter adapter = new StiSysListAdapter(StisysActivity.this, R.layout.stisyslistview, l);
		lv.setAdapter(adapter);
	}

	public void abmeldenClick(View view) {
		untermenue = true;
		setContentView(R.layout.stisys_abmelden);
		List<String> l = new ArrayList<String>();
		l.add("<h>Abmeldbare Kurse");
		l.addAll(sm.getUnsubscribeableCourses());
		ListView lv = (ListView)findViewById(R.id.lvAbmeldesm);
		StiSysListAdapter adapter = new StiSysListAdapter(StisysActivity.this, R.layout.stisyslistview, l);
		lv.setAdapter(adapter);
	}

	public void meineNotenClick(View view) {
		untermenue = true;
		setContentView(R.layout.stisys_meine_noten);
		
		TextView notensm = (TextView) findViewById(R.id.Noten);
		double average = sm.getAverageGrade();
		notensm.setText("" + average);
		
		TextView cpsm = (TextView) findViewById(R.id.CP);
		double cp = sm.getSolvedCP();
		cpsm.setText((int)cp + "/180 CP, " + Math.round(StisysUtil.getPerCent(cp, 180d)) + "% des Studiums");
		
	}
	
	public void einstellungenStisysClick(View view){
		untermenue = true;
		setContentView(R.layout.stisys_einstellungen);
	}
	
	public void resyncClick(View view){
		sm.syncData();
	}
	
	public void passwordClick(View view){
		untermenue = true;
		setContentView(R.layout.test);
		//TODO LOKI: Dauerhafte Speicherung Username/Passwort - Kombi
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_BACK && untermenue == true)
		{
			setContentView(R.layout.activity_stisys);
			untermenue = false;
			return true;
		}
		
		return super.onKeyDown(keyCode,event);
	}
}