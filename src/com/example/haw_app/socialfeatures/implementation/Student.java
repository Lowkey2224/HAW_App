package com.example.haw_app.socialfeatures.implementation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.haw_app.database.Database;
import com.example.haw_app.socialfeatures.interfaces.IPraktika;
import com.example.haw_app.socialfeatures.interfaces.IStudent;
import com.example.haw_app.stisysManager.IStiSysManager;
import com.example.haw_app.stisysManager.StiSysManagerFactory;

public class Student implements IStudent {

	private static Student instance = null;
	private static IStiSysManager sm = null;
	private Map<String, IPraktika> praktikas = new HashMap<String, IPraktika>();

	private Student() {
		updatePraktikas();
	}

	@Override
	public boolean updatePraktikas() {
		Map<String, String[]> ret = new HashMap<String, String[]>();
		Set<String> newLecture = new HashSet<String>();
		String[] valuePraktika;

		sm = StiSysManagerFactory.getInstance();
		ret = sm.getRegisteredTrainings();
		newLecture = ret.keySet();

		for (String sLecture : newLecture) {
				valuePraktika = ret.get(sLecture);
				praktikas.put(sLecture, new Praktika(sLecture,
						valuePraktika[0], valuePraktika[1], valuePraktika[2]));
				IPraktika p = praktikas.get(sLecture);
				Database db = null; // DUMMY WEGEN ERROR
				p.getPartnerAll(db);
			
		}
		return true;
	}

	@Override
	public boolean deletePraktika(String lecture) {
		//STISYS bescheid geben das aus DB gelöscht werden soll
		return praktikas.remove(lecture) != null;
	}

	@Override
	public Set<String> getPraktikasLectureSet() {
		if (!praktikas.isEmpty())
			return praktikas.keySet();

		return new HashSet<String>();
	}

	@Override
	public IPraktika getPraktika(String slecture) {
		return praktikas.get(slecture);
	}

	public static Student getInstance() {
		if (instance == null) {
			instance = new Student();
		}
		return instance;
	}

}
