package com.example.haw_app;

import java.util.ArrayList;

import com.example.haw_app.socialfeatures.implementation.Chat;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class ChatActivity extends Activity implements OnItemSelectedListener {

	private Spinner recipient;
	private EditText textMessage;
	private ListView listview;
	private String selectTo ="";
	private Chat ci;
	private ArrayList<String> messages = new ArrayList<String>();
	private ArrayList<String> contactList = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sf_chat);
		contactList.add(" ");
		ci = Chat.getInstance();
		ci.setChatActivity(this);
	    
		recipient = (Spinner) this.findViewById(R.id.toET);
		recipient.setOnItemSelectedListener(this);
		textMessage = (EditText) this.findViewById(R.id.chatET);
		listview = (ListView) this.findViewById(R.id.listMessages);
		ci.startContact();
		setContactAdapter();
		
		// Set a listener to send a chat text message
		Button send = (Button) this.findViewById(R.id.sendBtn);
		send.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//String to = recipient.getText().toString();
				String to = selectTo;
				String text = textMessage.getText().toString();
				ci.sendMessage(to, text);
			}
		});
	}

	public void setListAdapter() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.sf_listitem, messages);
		listview.setAdapter(adapter);
	}

	public void setMessage(String text) {
		messages.add(text);
	}
	
	public void setContactAdapter() {
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		//		R.layout.sf_listitem, messages);
		//listview.setAdapter(adapter);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,contactList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		recipient.setAdapter(adapter);

		//adapter.notifyDataSetChanged();
	}

//	public void setContact(ArrayList<String> contactList) {
//		this.contactList.clear();
//		this.contactList=contactList;
//	}
	
	public void addContact(String text) {
		contactList.add(text);
	}
	
	public void removeContact(String text) {
		contactList.remove(contactList.indexOf(text));
		if (selectTo.equals(text))
			recipient.setSelection(0);
	}
	
	public void changeContactOnline(String text) {
		String textChange = (text+"-(Online)");
		text = (text+"-(Offline)");
		contactList.set(contactList.indexOf(text), textChange);
	}
	
	public void changeContactOffline(String text) {
		String textChange = (text+"-(Offline)");
		text = (text+"-(Online)");
		contactList.set(contactList.indexOf(text), textChange);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ci.chatDisconnect();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		String temp = parent.getItemAtPosition(pos).toString();
		String[] tempcon = temp.split("-");
		selectTo = tempcon[0];
		//System.out.println("AUSWAHL");
		//System.out.println("selectTo : " + selectTo);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		//System.out.println("NICHTS AUSWAHL");
		// TODO Auto-generated method stub
		
	}

}
