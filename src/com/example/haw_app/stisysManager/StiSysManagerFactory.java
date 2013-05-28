package com.example.haw_app.stisysManager;

public class StiSysManagerFactory {

	static IStiSysManager sm = null;
	

	public static IStiSysManager getInstance()
	{
		sm = new StiSysManager2("abe180","1Oliv3r1");
		return sm;
	}
	
	
	public static IStiSysManager getInstance(String username, String password)
	{
		if (sm == null) 
			sm = new StiSysManager2(username, password);
		return sm;
	}
}
