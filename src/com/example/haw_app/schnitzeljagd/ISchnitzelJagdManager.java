package com.example.haw_app.schnitzeljagd;

public interface ISchnitzelJagdManager {

	/**
	 * Liest ueber die Kamera einen QR Code und speichert ihn local. 
	 * @return gibt den Gescannten QR Code zurueck. Gibt null im Fehlerfall zurueck 
	 */
	public String scanQRCode();
	
	/**
	 * Bestimmt die GPS Koordinate und speichert sie local.
	 * @return Gibt ein Array mit [Latitude,Longitude] zurueck .Gibt null im Fehlerfall zurueck
	 */
	public double[] getGPSPosition();
	
	/**
	 * Erfuellt ein Ziel mit dem letzten gescannten Code, und der letzten Bestimmten GPS Position.
	 * @return gibt true zurueck wenn das Ziel erfuellt wurde, false wenn es nicht erfüllt wurde.	 * 
	 * @throws CouldNotConnectToServerException Wirft eine CouldNotConnectToServerException falls der Server nicht erreicht werden konnte.
	 */
	public boolean accomplishGoal(String barcode) /*throws CouldNotConnectToServerException*/;
	
	public int getNumberOfGoals();
	int getNumberOfAccomplishedGoals();
}