package com.example.haw_app.schnitzeljagd;

public class SchnitzelJagdManager implements ISchnitzelJagdManager{

	private static SchnitzelJagdManager sjm = null;
	
	@Override
	public boolean scanQRCode() {
		return true;
	}

	@Override
	public boolean getGPSPosition() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean accomplishGoal() throws CouldNotConnectToServerException {
		// TODO Auto-generated method stub
		return true;
	}
	
	private SchnitzelJagdManager()
	{
		;
	}


	protected static ISchnitzelJagdManager getInstance()
	{
		if (sjm == null)
			sjm = new SchnitzelJagdManager();
		return sjm;
	}
}
