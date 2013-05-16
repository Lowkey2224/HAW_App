package com.example.haw_app.mensa.implementation;

import java.util.Date;
import java.util.List;

import com.example.haw_app.MenuItemsActivity;
import com.example.haw_app.mensa.interfaces.IMenuItem;

import android.os.AsyncTask;

public class UpdateMenuItemsTask extends
		AsyncTask<MenuItemsActivity, Void, List<IMenuItem>> {

	private MenuItemsActivity activity;
	private Date date;

	public UpdateMenuItemsTask(Date date) {
		this.date = date;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<IMenuItem> doInBackground(MenuItemsActivity... arg0) {
		this.activity = arg0[0];
		return (List<IMenuItem>) MensaUtils.getMenuItemsForDate(date);
	}

	@Override
	protected void onPostExecute(List<IMenuItem> result) {
		activity.setMenuItems(result);
	}

}
