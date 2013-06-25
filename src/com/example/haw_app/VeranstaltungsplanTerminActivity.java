package com.example.haw_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class VeranstaltungsplanTerminActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.vp_termin);

	    Intent intent = getIntent();
	    TextView textview = (TextView)(findViewById(R.id.textView1));
	    textview.setText(intent.getStringExtra("selected"));
	    textview.setMovementMethod(new ScrollingMovementMethod());
	}
}
