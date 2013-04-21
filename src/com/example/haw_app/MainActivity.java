package com.example.haw_app;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	public void veranstaltungsClick(View view){
	    	setContentView(R.layout.veranstaltungsplan);
	}
	    
	public void essensClick(View view){
	    	setContentView(R.layout.essensplan);
	}
	    
	    public void socialClick(View view){
	    	setContentView(R.layout.socialfeatures);
	    }
	    
	    public void poolClick(View view){
	    	setContentView(R.layout.pcpool);
	    }
	    
	    public void stisysClick(View view){
	    	setContentView(R.layout.stisys);
	    }
	    
	    
	    public void schnitzelClick(View view){
	    	setContentView(R.layout.schnitzeljagd);
	    }
	    
	    public void optionClick(View view){
	    	setContentView(R.layout.optionen);
	    }

	}



