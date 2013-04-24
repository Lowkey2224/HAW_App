package com.example.haw_app.socialfeatures.implementation;


import java.util.HashMap;
import java.util.Map;

import com.example.haw_app.socialfeatures.interfaces.IPartner;
import com.example.haw_app.socialfeatures.interfaces.IPraktika;

public class Praktika implements IPraktika{
	
	String lecture = "";
	String profName = "";
	String groupNr = "";
	boolean status = false;
	Map<String,IPartner> partners = new HashMap<String,IPartner>();

	@Override
	public boolean createPartner(String matNr) {
		return partners.put(matNr,new Partner(matNr)) != null;
	}

	@Override
	public boolean deletePartner(String matNr) {
		return partners.remove(matNr) != null;
	}

	@Override
	public String getLecture() {
		return lecture;
	}

	@Override
	public boolean setLecture(String lecture) {
		this.lecture = lecture;
		return true;
	}

	@Override
	public String getProfName() {
		return profName;
	}

	@Override
	public boolean setProfName(String profName) {
		this.profName = profName;
		return true;
	}

	@Override
	public String getGroupNr() {
		return groupNr;
	}

	@Override
	public boolean setGroupNr(String groupNr) {
		this.groupNr = groupNr;
		return true;
	}

	@Override
	public boolean getStatus() {
		return status;
	}

	@Override
	public boolean setStatus(Boolean status) {
		this.status = status;
		return true;
	}

}
