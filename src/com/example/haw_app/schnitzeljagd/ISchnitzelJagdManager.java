package com.example.haw_app.schnitzeljagd;

public interface ISchnitzelJagdManager {

	/**
	 * Liest ueber die Kamera einen QR Code und speichert ihn local. 
	 * @return Gibt true bei Erfolg, false bei Misserfolg zurueck 
	 */
	public boolean scanQRCode();
	
	/**
	 * Bestimmt die GPS Koordinate und speichert sie local.
	 * @return Gibt true bei Erfolg, false bei Misserfolg zurueck
	 */
	public boolean getGPSPosition();
	
	/**
	 * Erfuellt ein Ziel mit dem letzten gescannten Code, und der letzten Bestimmten GPS Position.
	 * @return gibt true zurueck wenn das Ziel erfuellt wurde, false wenn es nicht erfüllt wurde.	 * 
	 * @throws CouldNotConnectToServerException Wirft eine CouldNotConnectToServerException falls der Server nicht erreicht werden konnte.
	 */
	public boolean accomplishGoal() throws CouldNotConnectToServerException;
	
	
}
