package com.example.haw_app.veranstaltungsplan.interfaces;

import java.util.List;

public interface Iveranstaltungsplan {
	
	public void exportieren();
	
	public void aktualisieren();
	
	public void setzeBelegteFaecher(List<String> faecher);
	
	public void setzeDatei(String file);

}
