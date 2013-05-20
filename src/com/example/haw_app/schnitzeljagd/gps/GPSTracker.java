package com.example.haw_app.schnitzeljagd.gps;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GPSTracker extends Service implements LocationListener{
	
	boolean isGPSEnabled = false,isNetworkEnabled = false, canGetLocation=false;
	private final Context mContext;
	Location location;
	double latitude;
	double longitude;
	
	private final static long MIN_DISTANCE_FOR_UPDATES = 10; //10 Meter
	
	
	private final static long MIN_TIME_BW_UPDATES = 1000*60*1; //1 Minute
	
	protected LocationManager locationManager;
	
	public GPSTracker (Context context)
	{
		this.mContext  =context;
		getLocation();
	}
	
	public Location getLocation()
	{
		try{
			locationManager = (LocationManager)mContext.getSystemService(LOCATION_SERVICE);
			
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			if (!isGPSEnabled && isNetworkEnabled)
			{
				
			}else{
				this.canGetLocation = true;
				if(isNetworkEnabled){
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_FOR_UPDATES, this);
					Log.d("Network", "Network");
					if (locationManager!=null)
					{
						location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if(location!=null)
						{
							latitude = location.getLatitude();
							longitude=location.getLongitude();
						}
					}
				}
				
				if(isGPSEnabled)
				{
					if(location == null)
					{
						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_FOR_UPDATES, this);
						Log.d("GPS Enabled", "GPS Enabled");
						if(locationManager!=null)
						{
							location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if(location!=null)
							{
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return location;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getLatitude ()
	{
		if (location!=null)
		{
			latitude = location.getLatitude();
		}
		return latitude;
	}
	
	public double getLongitude()
	{
		if(location !=null)
		{
			longitude = location.getLongitude();
		}
		return longitude;
	}
	
	public boolean canGetLocation()
	{
		return this.canGetLocation;
	}
	
	public void showSettingsAlert(final ICallback cb)
	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
		alertDialog.setTitle("GPS is Settings");
		alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu");
		
		alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				mContext.startActivity(intent);
				cb.call(true);
			}
		});
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				cb.call(false);
			}
		});
		alertDialog.show();
	}
	
	public void stopUsingGPS()
	{
		if(locationManager!=null)
		{
			locationManager.removeUpdates(GPSTracker.this);
		}
	}
	
	
}
