package com.example.haw_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class VeranstaltungsplanTerminActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.vp_termin);

	    Intent intent = getIntent();

	    ((TextView)(findViewById(R.id.textView1))).setText("Es wurde "+intent.getStringExtra("selected")+ " gewählt!");
	}
	

}
