package com.example.haw_app;
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
import android.widget.Button;
import android.widget.TextView;

import com.example.haw_app.schnitzeljagd.SchnitzelJagdManager;

public class SchnitzeljagdActivity extends Activity {
	private SchnitzelJagdManager sm;
	private String qrCode = null;
	private boolean codeScanned = false, coordinatesScanned = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schnitzeljagd);
		// Show the Up button in the action bar.
		setupActionBar();
		Button b = (Button)findViewById(R.id.btn_acc);
        b.setVisibility(View.INVISIBLE);
        b.setClickable(false);
		sm = new SchnitzelJagdManager(this);
		
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
		getMenuInflater().inflate(R.menu.schnitzeljagd, menu);
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
	
    public void scanClick(View view){
    	sm.scanQRCode();
    	
    }
    
    public void getCoordinatesClick(View view){
    	double[] dary = sm.getGPSPosition();
    	if (dary[0] != 0 && dary[1] != 0){
    		coordinatesScanned = true;
    		TextView stat = (TextView)findViewById(R.id.tvStatus);
    		stat.setText("Position erfasst");
    		if (codeScanned){
	            Button b = (Button)findViewById(R.id.btn_acc);
	            b.setVisibility(View.VISIBLE);
	            b.setClickable(true);
	            }
    	}    	    	    	
    }

    public void accClick(View view){
    	if(coordinatesScanned && codeScanned)
    	{
    		boolean b = sm.accomplishGoal(qrCode);
    		Button btn = (Button)findViewById(R.id.btn_acc);
    		btn.setVisibility(View.INVISIBLE);
    		btn.setClickable(false);
    		Log.w("acc","Ziel erfuellt: "+b);
    		TextView stat = (TextView)findViewById(R.id.tvStatus);
    		stat.setText((b)?"Ziel Erfuellt":"Ziel nicht erfuellt");
    	}
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) 
	{
		Log.d("onActivityResult", intent.getClass().toString());
		if (requestCode == 0) 
	    {
	        if (resultCode == RESULT_OK)
	        {
	            String contents = intent.getStringExtra("SCAN_RESULT");	            
	            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            Log.i("xZing", "contents: "+contents+" format: "+format);
	            qrCode = contents;
	            codeScanned = true;
	            if (coordinatesScanned){
	            Button b = (Button)findViewById(R.id.btn_acc);
	            b.setVisibility(View.VISIBLE);
	            b.setClickable(true);
	            }
	        } 
	        else if (resultCode == RESULT_CANCELED)
	        {
	            // Handle cancel
	            Log.i("xZing", "Cancelled");
	        }
	    }
	}
}
