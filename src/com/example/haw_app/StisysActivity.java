package com.example.haw_app;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class StisysActivity extends Activity {
	EditText editLogin;
	EditText editPassword;
	Button buttonLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		setContentView(R.layout.act_stisys_login);
		editLogin = (EditText) findViewById(R.id.editLogin);
		editPassword = (EditText) findViewById(R.id.editPassword);

		findViewById(R.id.buttonLogin).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						String loginName = editLogin.getText().toString();
						Log.d("MainActivity", "onClick'd with loginName: "
								+ loginName);
						String loginPassword = editPassword.getText()
								.toString();
						Log.d("MainActivity", "onClick'd with loginPassword: "
								+ loginPassword);
						
						
						//
						setContentView(R.layout.activity_stisys);
						// Show the Up button in the action bar.
						setupActionBar();
					    //
					}
				});


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
	
    public void notenClick(View view){
    	setContentView(R.layout.test);
    }
    
    public void platzhalterClick(View view){
    	setContentView(R.layout.test);
    }

}
