package com.example.haw_app.veranstaltungsplan.implementations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.joda.time.DateTime;

import android.util.Log;

import static com.example.haw_app.veranstaltungsplan.implementations.Utility.*;

public class Veranstaltungsplan implements com.example.haw_app.veranstaltungsplan.interfaces.Iveranstaltungsplan{
	
	private static Veranstaltungsplan instance = null;
	
	private File datei = new File("veranstaltungsplan.txt");
	
	private String url = "http://ec2-176-34-76-54.eu-west-1.compute.amazonaws.com:8080/HawServer/files/Sem_I.txt";
	
	private List<String> belegteFaecher = new ArrayList<String>();
	
	public List<Termin> termine = new ArrayList<Termin>();
	
	private List<Integer> kws = new ArrayList<Integer>();
	
	private String semesterGruppe = null;
	
	private int wochentag = 0;
	 
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
	public void exportieren() throws Exception {
		
		File file = new File("veranstaltungsplan.ics");
		
		FileWriter fw= new FileWriter(file);
		
		//Kalender
		
		fw.write("BEGIN:VCALENDAR");
		fw.write(System.getProperty("line.separator"));
		
		fw.write("PRODID:HAW-APP");
		fw.write(System.getProperty("line.separator"));
		
		fw.write("VERSION:1.0");
		fw.write(System.getProperty("line.separator"));
		
		fw.write("CALSCALE:GREGORIAN");
		fw.write(System.getProperty("line.separator"));
		
		fw.write("METHOD:PUBLISH");
		fw.write(System.getProperty("line.separator"));
		
		fw.write("X-WR-CALNAME:HAW");
		fw.write(System.getProperty("line.separator"));
		
		fw.write("X-WR-TIMEZONE:Europe/Berlin");
		fw.write(System.getProperty("line.separator"));
		
		fw.write("X-WR-CALDESC:HAW-Veranstaltungen. Erstellt mit HAW-App");
		fw.write(System.getProperty("line.separator"));
		
		//Termine
		
		Date jdkNow = new Date(System.currentTimeMillis());
		DateTime now = new DateTime(jdkNow);
		
		for(Termin termin : termine) {
			
			if(belegteFaecher.contains(termin.name())) {
				
				UUID uid = UUID.randomUUID();
				
				fw.write("BEGIN:VEVENT");
				fw.write(System.getProperty("line.separator"));
				
				fw.write("DTSTART:" + termin.von().toString("yyyyMMdd") + "T" + termin.von().toString("HHmmss") + "+01:00");
				fw.write(System.getProperty("line.separator"));
				
				fw.write("DTEND:" + termin.bis().toString("yyyyMMdd") + "T" + termin.bis().toString("HHmmss") + "+01:00");
				fw.write(System.getProperty("line.separator"));
				
				fw.write("DTSTAMP:" + now.toString("yyyyMMdd") + "T" + now.toString("HHmmss") + "+01:00");
				fw.write(System.getProperty("line.separator"));
				
				fw.write("UID:" + uid + "@haw-app");
				fw.write(System.getProperty("line.separator"));
				
				fw.write("CREATED:" + now.toString("yyyyMMdd") + "T" + now.toString("HHmmss") + "+01:00");
				fw.write(System.getProperty("line.separator"));
				
				fw.write("DESCRIPTION:" + termin.prof());
				fw.write(System.getProperty("line.separator"));
				
				fw.write("LAST-MODIFIED:" + now.toString("yyyyMMdd") + "T" + now.toString("HHmmss") + "+01:00");
				fw.write(System.getProperty("line.separator"));
				
				fw.write("LOCATION:" + termin.raum());
				fw.write(System.getProperty("line.separator"));
				
				fw.write("SEQUENCE:0");
				fw.write(System.getProperty("line.separator"));
				
				fw.write("STATUS:CONFIRMED");
				fw.write(System.getProperty("line.separator"));
				
				fw.write("SUMMARY:" + termin.name());
				fw.write(System.getProperty("line.separator"));
				
				fw.write("TRANSP:OPAQUE");
				fw.write(System.getProperty("line.separator"));
				
				fw.write("END:VEVENT");
				fw.write(System.getProperty("line.separator"));
				
			}
			
		}
		
		fw.write("END:VCALENDAR");
		
		fw.flush();
		fw.close();
		
	}
	
	private static InputStreamReader retrieveReader(String url) {

		DefaultHttpClient client = new DefaultHttpClient();

		HttpGet getRequest = new HttpGet(url);

		try {

			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(Veranstaltungsplan.class.getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();
			return new InputStreamReader(getResponseEntity.getContent());

		} catch (IOException e) {
			getRequest.abort();
			Log.w(Veranstaltungsplan.class.getSimpleName(), "Error for URL " + url, e);
		}

		return null;

	}
	
	private static void saveFile(File file, InputStreamReader is) throws IOException{
					
			BufferedReader br = new BufferedReader(is);
			
			FileWriter fw = new FileWriter(file);
			
			String line = br.readLine();
			
			while (line != null) {
				fw.write(line);
				br.readLine();
			}
			
			fw.flush();
			fw.close();
			
			br.close();
			
	}

	@Override
	public void aktualisieren() throws Exception {
		
		InputStreamReader is = retrieveReader(url);
		
		saveFile(datei,is);
		
		this.parsen();
		
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
	
	@Override
	public void setzeUrl(String url) {
		this.url = url;
	}
	
	private void parsen() throws Exception {
		
		termine.clear();
		
		BufferedReader br = new BufferedReader(new FileReader(datei));
		
		String line = br.readLine();
		
		while(line != null){
			
			if(		   line.equals("") 
					|| line.startsWith("Stundenplan")  
					|| line.startsWith("Name")) { // Unwichtige Zeilen
				
			} else if(line.startsWith("Semestergruppe")) { // Semester Zeile
				
				List<String> splittedLine = Arrays.asList(line.split("  "));
				
				if(splittedLine.size() != 2){
					System.out.println(line);
					br.close();
					throw new Exception("Fehlerhafte Datei");
				}
				
				semesterGruppe = splittedLine.get(1);
				
				
			} else if(line.matches("\\d.*")) { // KW Zeilen
				
				kws.clear();
				
				List<String> splittedLine = Arrays.asList(line.split(","));
				
				for(String s : splittedLine) {
					s = s.replace(" ", "");
					
					if(s.contains("-")) {
						
						List<String> splittedKws = Arrays.asList(line.split("-"));
						int start = Integer.parseInt(splittedKws.get(0));
						int ende = Integer.parseInt(splittedKws.get(1));
						
						for(int i = start; i <= ende; i++) {
							kws.add(i);
						}
						
					} else {
						kws.add(Integer.parseInt(s));
					}
					
				}
				
				
			} else if( line.startsWith("BAI") 
					|| line.startsWith("BTI") 
					|| line.startsWith("BWI") 
					|| line.startsWith("INF") 
					|| line.startsWith("MINF") 
					|| line.startsWith("GW")) { // Veranstaltungs Zeilen
				
				List<String> splittedLine = Arrays.asList(line.split(","));
				
				if(splittedLine.size() != 6){
					System.out.println(line);
					br.close();
					throw new Exception("Fehlerhafte Datei");
				}
				
				if(splittedLine.get(3).equals("Mo")){
					wochentag = 1;
				} else if(splittedLine.get(3).equals("Di")){
					wochentag = 2;
				} else if(splittedLine.get(3).equals("Mi")){
					wochentag = 3;
				} else if(splittedLine.get(3).equals("Do")){
					wochentag = 4;
				} else if(splittedLine.get(3).equals("Fr")){
					wochentag = 5;
				} else if(splittedLine.get(3).equals("Sa")){
					wochentag = 6;
				} else if(splittedLine.get(3).equals("So")){
					wochentag = 7;
				} else {
					System.out.println(line);
					br.close();
					throw new Exception("Fehlerhafte Datei");
				}
				
				List<String> splittedStartTime = Arrays.asList(splittedLine.get(4).split(":"));
				List<String> splittedEndTime = Arrays.asList(splittedLine.get(5).split(":"));
				
				int startHour = Integer.parseInt(splittedStartTime.get(0));
				int startMin = Integer.parseInt(splittedStartTime.get(1));
				
				int endHour = Integer.parseInt(splittedEndTime.get(0));
				int endMin = Integer.parseInt(splittedEndTime.get(1));
				
				Date jdkNow = new Date(System.currentTimeMillis());
				
				DateTime now = new DateTime(jdkNow);
				
				for(int kw : kws){
					
					DateTime von = now.withWeekOfWeekyear(kw).withDayOfWeek(wochentag).withHourOfDay(startHour).withMinuteOfHour(startMin).withSecondOfMinute(0).withMillisOfSecond(0);
					DateTime bis = now.withWeekOfWeekyear(kw).withDayOfWeek(wochentag).withHourOfDay(endHour).withMinuteOfHour(endMin).withSecondOfMinute(0).withMillisOfSecond(0);
					
					termine.add(termin(semesterGruppe,splittedLine.get(0),von,bis,splittedLine.get(1),splittedLine.get(2)));
					
				}
				
			} else { // Nicht bedachte Zeilen
				System.out.println(line + "\n" + "wurde nicht beachtet!");
			}
			
			line = br.readLine(); // naechste Zeile
		}
		
		br.close();
		
	}
	
}
