package com.example.haw_app.socialfeatures.implementation;

import com.example.haw_app.socialfeatures.interfaces.IPartner;

public class Partner implements IPartner {
	
	private String firstname = "";
	private String surname = "";
	private String matNr = "";
	private String email = "";
	private String handy = "";
	private String lecture = "";
	
	public Partner(String matNr,String firstname, String surname,
			String email, String handy, String lecture){
		this.matNr = matNr;
		this.firstname = firstname;
		this.surname = surname;
		this.matNr = matNr;
		this.email = email;
		this.handy = handy;
		this.lecture = lecture;
	}

	@Override
	public String getFirstname() {
		return firstname;
	}

	@Override
	public boolean setFirstname(String firstname) {
		this.firstname = firstname;
		return true;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	@Override
	public boolean setSurname(String surname) {
		this.surname = surname;
		return true;
	}

	@Override
	public String getMatNr() {
		return matNr;
	}

	@Override
	public boolean setMatNr(String matNr) {
		this.matNr = matNr;
		return true;
	}

	@Override
	public String getEMail() {
		return email;
	}

	@Override
	public boolean setEMail(String email) {
		this.email = email;
		return true;
	}

	@Override
	public String getHandy() {
		return handy;
	}

	@Override
	public boolean setHandy(String handy) {
		this.handy = handy;
		return true;
	}
	
	@Override
	public String getLecture() {
		return lecture;
	}
}
