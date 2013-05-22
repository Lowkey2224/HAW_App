package com.example.haw_app.stisysManager;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.joda.time.DateTime;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StiSysManager2 implements IStiSysManager {

    private Session session;
    private Map<String, String> profile;
    private int printQuote;
    private Map<String, String[]> courses;
    private Map<String, Boolean> solvedTrainings;
    private Map<String, Integer> solvedTests;
    private Map<String, DateTime[]> nextApps;
    private Map<String, String> mailLists;
    private Document profileDoc;
    private Document coursesDoc;
    private Document printDoc;
    private Document solvedDoc;
    private Document mailListDocSEM;
    private Document mailListDocPRA;
    private Document mailListDocWP;
    private Document mailListDocPRO;

    public StiSysManager2(String userName, String password)
    {
        //nextApplications();
    	
        session = new Session(userName, password);
        refreshInformations();

        /*
         for (Map.Entry<String, DateTime[]> entry : nextApps.entrySet())
         {
         for(int i = 0; i < entry.getValue().length; i++){
         System.out.println(entry.getKey() + "-> " + entry.getValue()[i]);
         }
         }
         */
    }
    
    
    private boolean isSameUser(String username){
    	return session.isSameUser(username);
    }
    
    private void refreshInformations(){
        getProfile();
        getPrintQuote();
        getCourses();
        getSolvedOnes();
    }
    

    // noch nciht fertig
    @Override
    public int getAverageGrade()
    {
        //if key == proggen ,projekt etc., value * 0.75 oder was auch immer
        double sum = 0;
        for (Map.Entry<String, Integer> entry : solvedTests.entrySet())
        {
            sum += entry.getValue();

        }
        println(sum / (double) solvedTests.size());
        return 0;
    }

    @Override
    public int getDifferenceToBetterGrade()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Map<String, DateTime[]> getNextApplicationDates()
    {
        return nextApps;
    }

    @Override
    public String getStudentName()
    {
        return profile.get("Name");

    }

    @Override
    public String getUserName()
    {
        return profile.get("HAW-Kennung");
    }

    @Override
    public DateTime getPasswordExpirationDate()
    {
        return getDateNoTime(profile.get("Ablaufdatum des Passwortes"));
    }

    @Override
    public int getMatriculationNumber()
    {
        return Integer.parseInt(profile.get("Martrikelnummer"));
    }

    @Override
    public DateTime getBirthday()
    {
        return getDateNoTime(profile.get("Geburtstag").split(", ")[1]);
    }

    @Override
    public int getPrintCredit()
    {
        return printQuote;
    }

    @Override
    public Map<String, String[]> getRegisteredSocialSciencesCourses()
    {
        Map<String, String[]> ssc = new HashMap<String, String[]>();
        for (Map.Entry<String, String[]> entry : courses.entrySet())
        {
            if (entry.getValue()[1].length()==0 && entry.getValue()[2].length()==0)
            {
                ssc.put(entry.getKey(), entry.getValue());
            }
        }
        return ssc;
    }

    @Override
    public Map<String, String[]> getRegisteredTests()
    {
        Map<String, String[]> tr = new HashMap<String, String[]>();
        String f = " ";
        
        for (Map.Entry<String, String[]> entry : courses.entrySet())
        {
            if (entry.getValue()[2].length()==0 && entry.getValue()[1].contains("AI"))
            {
                tr.put(entry.getKey(), entry.getValue());
            }
        }
        return tr;
    }

    @Override
    public Map<String, String[]> getRegisteredChosenCourses()
    {
        Map<String, String[]> tr = new HashMap<String, String[]>();
        for (Map.Entry<String, String[]> entry : courses.entrySet())
        {
            if (entry.getValue()[2].equals("-"))
            {
                tr.put(entry.getKey(), entry.getValue());
            }
        }
        return tr;
    }

    @Override
    public Map<String, String[]> getRegisteredTrainings()
    {
        Map<String, String[]> tr = new HashMap<String, String[]>();
        for (Map.Entry<String, String[]> entry : courses.entrySet())
        {
            if (!(entry.getValue()[2].length()==0) && !(entry.getValue()[2].equals("-")))
            {
                tr.put(entry.getKey(), entry.getValue());
            }
        }
        return tr;
    }

    @Override
    public Map<String, Integer> getSolvedTests()
    {
        return solvedTests;
    }

    @Override
    public Map<String, Boolean> getSolvedTrainings()
    {
        return solvedTrainings;
    }

    //Seitenaufruf funktioniert nicht
    @Override
    public Map<String, String> getMailingLists()
    {
        getMailLists();
        return null;
    }

    private Document profileDoc()
    {
        if (profileDoc == null)
        {
            profileDoc = session.goToSite(Websites.STISYS_PROFIL);
        }
        return profileDoc;
    }

    private Document coursesDoc()
    {
        if (coursesDoc == null)
        {
            coursesDoc = session.goToSite(Websites.STISYS_ANMELDUNGEN);
        }
        return coursesDoc;
    }

    private Document printDoc()
    {
        if (printDoc == null)
        {
            printDoc = session.goToSite(Websites.STISYS_DRUCKQUOTEN);
        }
        return printDoc;
    }

    private Document solvedDoc()
    {
        if (solvedDoc == null)
        {
            solvedDoc = session.goToSite(Websites.STISYS_ERGEBNISSE);
        }
        return solvedDoc;

    }

    //does not work, site not available
    private Document mailListDocSEM()
    {
        if (mailListDocSEM == null)
        {
            mailListDocSEM = session.goToSite(Websites.STISYS_MAILLISTEN_SEMESTERGRP);
        }
        return mailListDocSEM;
    }

    //does not work, site not available
    private Document mailListDocPRA()
    {
        if (mailListDocPRA == null)
        {
            mailListDocPRA = session.goToSite(Websites.STISYS_MAILLISTEN_PRAKTIKA);
        }
        return mailListDocPRA;
    }

    //does not work, site not available    
    private Document mailListDocWP()
    {
        if (mailListDocWP == null)
        {
            mailListDocWP = session.goToSite(Websites.STISYS_MAILLISTEN_WP);
        }
        return mailListDocWP;
    }

    //does not work, site not available    
    private Document mailListDocPRO()
    {
        if (mailListDocPRO == null)
        {
            mailListDocPRO = session.goToSite(Websites.STISYS_MAILLISTEN_PROJEKT);
        }
        return mailListDocPRO;
    }

    //andere Änderungen:, Notenänderung, Printquoten
    //Profil-Aktualisierung:
    //Passwort neu, Hochzeit, Pr�fungsordnung
    //1. Tag abpassen, an dem das Passwort neu gesetzt werden muss -> getProfile()
    //2.PW manuell ge�ndert -> mit altem vgl, falls auf dauerhaft gespeichert eingestellt -> falls eingegebenes != altes pw, -> getProfile()
    //anderen 3 F�lle: da PW jedes halbe Jahr ge�ndert -> einfach mit�ndern, da alle Daten neu gezogen werden
    private void getProfile()
    {
        profile = new HashMap<String, String>();
        Elements table1 = profileDoc().select("tr[class=tablecontentbackdark]").select("td[align=left]");
        Elements table2 = profileDoc().select("tr[class=tablecontentbackdark]").select("td[align=right]");
        Elements table3 = profileDoc().select("tr[class=tablecontentbacklight]").select("td[align=left]");
        Elements table4 = profileDoc().select("tr[class=tablecontentbacklight]").select("td[align=right]");
        
        for (int i = 0; i < table1.size(); i++)
        {
            profile.put(table1.get(i).text(), table2.get(i).text());
        }
        for (int i = 0; i < table3.size(); i++)
        {
            profile.put(table3.get(i).text(), table4.get(i).text());
        }
    }

    private void getCourses()
    {
        courses = new HashMap<String, String[]>();
        Elements table1 = coursesDoc().select("tr[class=tablecontentbacklight]").select("span[class=thin]");
        Elements table2 = coursesDoc().select("tr[class=tablecontentbackdark]").select("span[class=thin]");
        
        for (int i = 0; i < table1.size(); i += 5)
        {
            courses.put(table1.get(i).text(), new String[]
                    {
                        table1.get(i + 1).text(), table1.get(i + 2).text(), table1.get(i + 3).text(), table1.get(i + 4).text()
                    });
        }
        for (int i = 0; i < table2.size(); i += 5)
        {
            courses.put(table2.get(i).text(), new String[]
                    {
                        table2.get(i + 1).text(), table2.get(i + 2).text(), table2.get(i + 3).text(), table2.get(i + 4).text()
                    });
        }
    }

    private void getSolvedOnes()
    {
        solvedTrainings = new HashMap<String, Boolean>();
        solvedTests = new HashMap<String, Integer>();
        Elements table1 = solvedDoc().select("tr[class=tablecontentbacklight]").select("td");
        Elements table2 = solvedDoc().select("tr[class=tablecontentbackdark]").select("td");

        for (int i = 1; i < table1.size(); i += 8)
        {
        	if (table1.get(i + 6).text().contains("n.e.")) continue;
            if (table1.get(i + 6).text().contains("erf"))
            {
                solvedTrainings.put(table1.get(i).text(), Boolean.TRUE);
            } else
            {
                solvedTests.put(table1.get(i).text(), Integer.parseInt(table1.get(i + 6).text()));
            }
        }

        for (int i = 1; i < table2.size(); i += 8)
        {
        	if (table2.get(i + 6).text().contains("n.e.")) continue;
            if (table2.get(i + 6).text().contains("erf"))
            {
                solvedTrainings.put(table2.get(i).text(), Boolean.TRUE);
            } else
            {
                solvedTests.put(table2.get(i).text(), Integer.parseInt(table2.get(i + 6).text()));
            }
        }
    }

    private void getPrintQuote()
    {
        String table1 = printDoc().select("table[class=tablecontent]").select("td[align=right]").first().text();
        String a = table1.split("i")[0];
        printQuote = Integer.parseInt((a.split(",")[0] + a.split(",")[1].substring(0, 2)));
    }
    
    
    //TODO:does not work, no site available
        private void getMailLists()
    {
        mailLists = new HashMap<String, String>();
        //Praktika
        Elements table1 = mailListDocPRA().select("tr[class=tablecontentbacklight]").select("td[class=thinline]");
        Elements table2 = mailListDocPRA().select("tr[class=tablecontentbackdark]").select("td[class=thinline]");
        for (int i = 0; i < table1.size(); i++)
        {
            println(table1.get(i).text());
        }

    }

   
//TODO:problems with session -> no need for first site
    private void nextApplications()
    {
        nextApps = new HashMap<String, DateTime[]>();
        try
        {
            URL url = new URL(Websites.STISYS_FIRSTSITE);
            Document doc = Jsoup.parse(url, 3000);
            nextApps = tableToDateMap(doc.select("tr[class=tablecontentbackdark]"));
            Map<String, DateTime[]> _tmp = tableToDateMap(doc.select("tr[class=tablecontentbacklight]"));
            nextApps.putAll(_tmp);
        } catch (IOException e)
        {
            println(e.getMessage());
        }
    }
    
    
    //Helper Method for Converting DateTimes
     private Map<String, DateTime[]> tableToDateMap(Elements table)
    {
        Map<String, DateTime[]> app = new HashMap<String, DateTime[]>();

        for (Element e : table)
        {
            Iterator<Element> ite = e.select("td").iterator();
            DateTime[] date = new DateTime[2];

            String key = ite.next().text(); // 0.elem key
            ite.next(); // 1 elem skip
            date[0] = getDate(ite.next().text()); // 2 elem date 1
            ite.next(); // 3 elem skip
            date[1] = getDate(ite.next().text()); // 4 elem date 2

            app.put(key, date);
        }
        return app;
    }

    //Helper Method for Converting DateTimes
     private DateTime getDate(String date)
    {
        int day = Integer.parseInt("" + date.charAt(0) + date.charAt(1));
        int month = Integer.parseInt("" + date.charAt(3) + date.charAt(4));
        int year = Integer.parseInt("" + date.charAt(6) + date.charAt(7) + date.charAt(8) + date.charAt(9));
        int hour = Integer.parseInt("" + date.charAt(11) + date.charAt(12));
        int minute = Integer.parseInt("" + date.charAt(14) + date.charAt(15));
        return new DateTime(year, month, day, hour, minute, 0, 0);
    }
     
    //Helper Method for Converting DateTimes
    private DateTime getDateNoTime(String date)
    {
        int day = Integer.parseInt("" + date.charAt(0) + date.charAt(1));
        int month = Integer.parseInt("" + date.charAt(3) + date.charAt(4));
        int year = Integer.parseInt("" + date.charAt(6) + date.charAt(7) + date.charAt(8) + date.charAt(9));
        return new DateTime(year, month, day, 0, 0, 0, 0);
    }


    
    

    private class Session {

        private String userName;
        private String password;
        private String sessionId;

        public Session(String userName, String password)
        {
            this.userName = userName;
            this.password = password;
            makeLogin();
        }

        private void makeLogin()
        {
            try
            {
                Connection.Response res = Jsoup.connect(Websites.STISYS_FIRSTSITE).data("username", userName, "password", password).method(Method.POST).execute();
                sessionId = res.cookie("JSESSIONID");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        private boolean isSessionTimeOut(Document doc)
        {
            return doc.body().select("h1").text().contains("StiSys Session Timeout");
        }

        private Document goToSite(String site)
        {
            Document doc = null;
            try
            {
                doc = Jsoup.connect(site).cookie("JSESSIONID", sessionId).execute().parse();
                //doc = Jsoup.connect(site).cookie("JSESSIONID", sessionId).get();
                if (isSessionTimeOut(doc))
                {
                    makeLogin();
                    doc = Jsoup.connect(site).cookie("JSESSIONID", sessionId).execute().parse();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return doc;
        }
        private boolean isSameUser(String username){
        	return userName.equals(username);
        }
    }
    


    private void println(Object o)
    {
        System.out.println(o);
    }
}
