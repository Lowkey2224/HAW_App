package com.example.haw_app.schnitzeljagd;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.haw_app.schnitzeljagd.gps.GPSTracker;
import com.example.haw_app.schnitzeljagd.gps.ICallback;
import com.example.haw_app.schnitzeljagd.http.GetRequest;

public class SchnitzelJagdManager implements ISchnitzelJagdManager  {

	Context mParent;
	double lat=0, lon=0;
	String code=null;
	ICallback cb;

	public SchnitzelJagdManager(Context parent) {
		mParent = parent;
	}

	@Override
	public String scanQRCode() {
		{
			return startScan();
		}
	}

	
	
	private String startScan()
	{
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE",
				"QR_CODE_MODE");
//		mParent.startActivity(intent);
		((Activity)mParent).startActivityForResult(intent, 0);
		return null;
	}


	@Override
	public double[] getGPSPosition() {
		GPSTracker gm = new GPSTracker(mParent);			
		if (gm.canGetLocation()) {
			lat = gm.getLatitude();
			lon = gm.getLongitude();
			// text.setVisibility(1);
		} else {
			ICallback cb = new ICallback() {
				
				@Override
				public void call(boolean b) {
					if (b)
						getGPSPosition();
					
				}
			};
			gm.showSettingsAlert(cb);
		}
	
		return new double[]{lat,lon};
	}

	@Override
	public boolean accomplishGoal(String code) {
		if (code != null && lat != 0 && lon != 0) {
			GetRequest gt = new GetRequest(mParent);
			AsyncTask<String, Void, String> at = gt.execute("");
			try {
				String str3 = at.get();
				return SchnitzelJagdGoalTools.checkIfGoalExists(str3, code, lat, lon);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		return false;
	}

}
