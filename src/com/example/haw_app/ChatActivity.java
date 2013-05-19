package com.example.haw_app;

import java.util.ArrayList;

import com.example.haw_app.socialfeatures.implementation.Chat;
//import com.example.haw_app.socialfeatures.implementation.Student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChatActivity extends Activity {

	private EditText recipient;
	private EditText textMessage;
	private ListView listview;
	//private Student si;
	private Chat ci;
	private ArrayList<String> messages = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		//si = Student.getInstance(this.getApplicationContext());
		//ci = LoginActivity.ci;
		//ci.setChatActivity(this);
		ci = Chat.getInstance();
		ci.setChatActivity(this);
		//ci.prepareConnection();
		
		Intent intent = getIntent();
		String a_loginname =  intent.getStringExtra("Loginname");
	    String a_password = intent.getStringExtra("Password");
	    
		recipient = (EditText) this.findViewById(R.id.toET);
		textMessage = (EditText) this.findViewById(R.id.chatET);
		listview = (ListView) this.findViewById(R.id.listMessages);
		setListAdapter();
		
		// Set a listener to send a chat text message
		Button send = (Button) this.findViewById(R.id.sendBtn);
		send.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				String to = recipient.getText().toString();
				String text = textMessage.getText().toString();
				ci.sendMessage(to, text);
			}
		});
		
		ci.connect(a_loginname,a_password);
	}

	public void setListAdapter() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.listitem, messages);
		listview.setAdapter(adapter);
	}

	public void setMessage(String text) {
		messages.add(text);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ci.chatDisconnect();
	}

}
