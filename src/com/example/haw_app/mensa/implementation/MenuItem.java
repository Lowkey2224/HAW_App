package com.example.haw_app.mensa.implementation;

import org.joda.time.DateMidnight;

import com.example.haw_app.mensa.interfaces.IMenuItem;

public class MenuItem implements IMenuItem {

	public MenuItem() {
	}

	public MenuItem(long date, int studentPrice, int nonStudentPrice,
			String description, String category, String attributes) {
		super();
		this.dateOfItem = date;
		this.studentPrice = studentPrice;
		this.nonStudentPrice = nonStudentPrice;
		this.description = description;
		this.category = category;
		this.attributes = attributes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDateOfItem() {
		return dateOfItem;
	}

	public DateMidnight getDateMidnight() {
		return new DateMidnight(dateOfItem);
	}

	public void setDateOfItem(long date) {
		this.dateOfItem = date;
	}

	public int getStudentPrice() {
		return studentPrice;
	}

	public void setStudentPrice(int studentPrice) {
		this.studentPrice = studentPrice;
	}

	public int getNonStudentPrice() {
		return nonStudentPrice;
	}

	public void setNonStudentPrice(int nonStudentPrice) {
		this.nonStudentPrice = nonStudentPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	private long id;

	private long dateOfItem;

	private int studentPrice;

	private int nonStudentPrice;

	private String description;

	private String category;

	private String attributes;

	@Override
	public String toString() {
		return "Kategorie:" + category + "\n" + "Preis (Student): "
				+ studentPrice + "\nPreis (sonstige): " + nonStudentPrice
				+ "\nBeschreibung: " + description;
	}

}