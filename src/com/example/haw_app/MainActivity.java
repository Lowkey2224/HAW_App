package com.example.haw_app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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

	public void veranstaltungsClick(View view) {
		startActivity(new Intent(MainActivity.this,
				VeranstaltungsplanActivity.class));
	}

	public void essensClick(View view) {
		// setContentView(R.layout.essensplan);
		startActivity(new Intent(MainActivity.this, MensaActivity.class));
	}

	public void socialClick(View view) {
		startActivity(new Intent(MainActivity.this,
				SocialFeaturesActivity.class));
	}

	
	public void poolClick(View view){ //setContentView(R.layout.pcpool);
		startActivity(new Intent(MainActivity.this,
				PcPoolPlanActivity.class));
	}
	 

	public void stisysClick(View view) {
//		 setContentView(R.layout.activity_stisys);
		 startActivity(new Intent(MainActivity.this,StisysActivity.class));
	}

	public void schnitzelClick(View view) {
//		 setContentView(R.layout.activity_schnitzeljagd);
		startActivity(new Intent(MainActivity.this, SchnitzeljagdActivity.class));
	}

//	public void optionClick(View view) {
//		// setContentView(R.layout.optionen);
//		startActivity(new Intent(MainActivity.this, OptionenActivity.class));
//	}

}
