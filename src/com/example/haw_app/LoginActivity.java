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
	private String showErrorText = "";
	//private Student si = null;
	private Chat ci;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_sf_login);
	    //si = Student.getInstance(this.getApplicationContext());
		sm = StiSysManagerFactory.getInstance();
		userName = sm.getUserName();
	    //ci = Chat.getInstance();
	    TextView account_username = (TextView) findViewById(R.id.account_username);
	    account_username.setText(userName);
	    ci = Chat.getInstance();
		ci.setLoginActivity(this);
	}
	
	public void sendLogin(View view) {
	    EditText account_password = (EditText) findViewById(R.id.account_password);
	    String a_password = account_password.getText().toString();

	    ci.startConnect(userName, a_password);
	}
	
	public void sendRegistration(View view) {
		EditText account_password = (EditText) findViewById(R.id.account_password);
	    String a_password = account_password.getText().toString();

		ci.registerUser(userName, a_password);
	}
	
	
	public void setError(String text) {
		showErrorText = text;
		
	}
	
	public void errorText(){
		TextView textLoginFailure = (TextView) findViewById(R.id.login_failure);
		textLoginFailure.setText(showErrorText);
	}
	
	public void changeActivity(){
		Intent intent = new Intent(this.getApplicationContext(), ChatActivity.class);
		startActivity(intent);
	}
	
	//@Override
	//protected void onDestroy() {
	//	super.onDestroy();
	//	ci.chatDisconnect();
	//}
}
