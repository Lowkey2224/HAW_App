package com.example.haw_app.stisysManager;

public class StiSysManagerFactory {

	static IStiSysManager sm = null;
	

	public static IStiSysManager getInstance()
	{
		sm = new StiSysManager2("abe180","1Oliv3r1");
		return sm;
		//return StiSysManager.getInstance();
	}
	
	
	public static IStiSysManager getInstance(String username, String password)
	{
		if (sm == null)
		sm = new StiSysManager2(username, password);
		return sm;
		//return StiSysManager.getInstance();
	}
}

//TODO: wenn sich jemand neu anmeldet, dann die instanzvariablen alle auf null setzen und neu zuweisen
//if abfrage im konstruktor? eher prüfmethode und dann die sachen im kostruktor auslagern