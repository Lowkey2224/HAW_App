package com.example.haw_app.stisysManager;

import android.content.Context;

public class StiSysManagerFactory {

	static IStiSysManager sm = null;
	

	public static IStiSysManager getInstance(Context mParent)
	{
		sm = new StiSysManager2("abe180","1Oliv3r1",mParent);
		return sm;
	}
	
	
	public static IStiSysManager getInstance(String username, String password, Context mParent)
	{
		if (sm == null) 
			sm = new StiSysManager2(username, password, mParent);
		return sm;
	}
}
