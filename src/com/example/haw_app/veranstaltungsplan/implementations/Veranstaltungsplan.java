package com.example.haw_app.veranstaltungsplan.implementations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Veranstaltungsplan implements com.example.haw_app.veranstaltungsplan.interfaces.Iveranstaltungsplan{
	
	private static Veranstaltungsplan instance = null;
	
	private File datei = null;
	private List<String> belegteFaecher = null;
	public List<Termin> termine = null;
	 
    /**
     * Default-Konstruktor, der nicht außerhalb dieser Klasse
     * aufgerufen werden kann
     */
    private Veranstaltungsplan() {
    	
    }
 
    /**
     * Statische Methode, liefert die einzige Instanz dieser
     * Klasse zurück
     */
    public static Veranstaltungsplan getInstance() {
        if (instance == null) {
            instance = new Veranstaltungsplan();
        }
        return instance;
    }
	
	@Override
	public void exportieren() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aktualisieren() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setzeBelegteFaecher(List<String> faecher) {
		belegteFaecher = faecher;
	}
	

	@Override
	public void setzeDatei(String file) {
		File f = new File(file);
		datei = f;
	}
	
	private void parsen() throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader(datei));
		
		String line = br.readLine();
		
		while(line != null){
			
			if(		   line.equals("") 
					|| line.startsWith("Stundenplan") 
					|| line.startsWith("Semestergruppe") 
					|| line.startsWith("Name")) { // Unwichtige Zeilen
				
				
			} else if(line.matches("\\d.*")) { // KW Zeilen
				
				//TODO KW Berechnung
				
			} else if( line.startsWith("BAI") 
					|| line.startsWith("BTI") 
					|| line.startsWith("BWI") 
					|| line.startsWith("INF") 
					|| line.startsWith("MINF") 
					|| line.startsWith("GW")) { // Veranstaltungs Zeilen
				
				List<String> splittedLine = Arrays.asList(line.split(","));
				
				if(splittedLine.size() != 6){
					System.out.print(line + "\n");
					br.close();
					throw new Exception("Fehlerhafte Datei");
				}
				
				//TODO Termine erstellen
				
			} else { // Nicht bedachte Zeilen
				System.out.print(line + "\n" + "wurde nicht beachtet!");
			}
			
			line = br.readLine(); // naechste Zeile
		}
		
		br.close();
		
	}
	
}
