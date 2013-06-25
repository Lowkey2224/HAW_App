package com.example.haw_app;

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

import com.example.haw_app.stisysManager.IStiSysManager;
import com.example.haw_app.stisysManager.StiSysManagerFactory;

public class SocialFeaturesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_features);
		IStiSysManager sm = StiSysManagerFactory.getInstance(this.getApplicationContext());
		if (sm.getUserName() == null) {
			findViewById(R.id.sf_chat).setVisibility(View.GONE);
			findViewById(R.id.sf_praktika).setVisibility(View.GONE);
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage(
					"Bitte erst in Stisys einloggen!")
					.setNeutralButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
								}
							});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		} else {
			findViewById(R.id.sf_chat).setVisibility(View.VISIBLE);
			findViewById(R.id.sf_praktika).setVisibility(View.VISIBLE);
			// Show the Up button in the action bar.
			setupActionBar();
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
	
    public void sfChatClick(View view){
    	
    	startActivity(new Intent(SocialFeaturesActivity.this,LoginActivity.class));
    }
    
    public void sfPraktikaClick(View view){
    	
    	startActivity(new Intent(SocialFeaturesActivity.this,SocialFeaturesPraktikaActivity.class));
    }

}
