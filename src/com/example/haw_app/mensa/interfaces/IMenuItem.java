package com.example.haw_app.mensa.interfaces;

import org.joda.time.DateMidnight;

public interface IMenuItem {

	public long getId();

	public void setId(long id);

	public long getDateOfItem();

	public DateMidnight getDateMidnight();

	public void setDateOfItem(long date);

	public int getStudentPrice();

	public void setStudentPrice(int studentPrice);

	public int getNonStudentPrice();

	public void setNonStudentPrice(int nonStudentPrice);

	public String getDescription();

	public void setDescription(String description);

	public String getCategory();

	public void setCategory(String category);

	public String getAttributes();

	public void setAttributes(String attributes);

}
