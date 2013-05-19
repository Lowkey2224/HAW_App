package com.example.haw_app;

import com.example.haw_app.socialfeatures.implementation.Chat;
//import com.example.haw_app.socialfeatures.implementation.Student;
import com.example.haw_app.stisysManager.IStiSysManager;
import com.example.haw_app.stisysManager.StiSysManagerFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity{
	public final static String LOGINNAME = "";
	public final static String PASSWORD = "";
	private IStiSysManager sm = null;
	private String userName;
	//private Student si = null;
	private Chat ci;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_login);
	    //si = Student.getInstance(this.getApplicationContext());
		sm = StiSysManagerFactory.getInstance();
		userName = sm.getUserName();
	    //ci = Chat.getInstance();
	    TextView account_username = (TextView) findViewById(R.id.account_username);
	    account_username.setText(userName);
	}
	
	public void sendLogin(View view) {
	    Intent intent = new Intent(this.getApplicationContext(), ChatActivity.class);
	    EditText account_password = (EditText) findViewById(R.id.account_password);
	    String a_password = account_password.getText().toString();
	    intent.putExtra("Loginname", userName);
	    intent.putExtra("Password", a_password);
	    startActivity(intent);
	}
	
	public void sendRegistration(View view) {
		Intent intent = new Intent(this.getApplicationContext(), ChatActivity.class);
		EditText account_password = (EditText) findViewById(R.id.account_password);
	    String a_password = account_password.getText().toString();
	    ci = Chat.getInstance();
		ci.registerUser(userName, a_password);
	    intent.putExtra("Loginname", userName);
	    intent.putExtra("Password", a_password);
	    startActivity(intent);
	}
}
