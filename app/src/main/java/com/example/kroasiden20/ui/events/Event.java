package com.example.kroasiden20.ui.events;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Event {

    // Member variables representing the title and information about the sport.
     String evtId;
     String evtName;
     String evtDate;
     String evtStart;
     String evtEnd;
     String evtTxt;
     String evtSecGot;
     String evtSec;
     String evtBarGot;
     String evtBar;
     String evtCrwGot;
     String evtCrw;
     String evtTchGot;
     String evtTch;

    static final String TABELL_NAVN         = "event";
    static final String KOL_ID              = "ID";
    static final String KOL_NAME            = "Name";
    static final String KOL_DATE            = "Date";
    static final String KOL_START           = "Time_Start";
    static final String KOL_END             = "Time_End";
    static final String KOL_TEXT            = "Event_text";
    static final String KOL_SEC_GOT         = "Event_sec_got";
    static final String KOL_SEC             = "Event_sec";
    static final String KOL_BAR_GOT         = "Event_bar_got";
    static final String KOL_BAR             = "Event_bar";
    static final String KOL_CRW_GOT         = "Event_crew_got";
    static final String KOL_CRW             = "Event_crew";
    static final String KOL_TCH_GOT         = "Event_tech_got";
    static final String KOL_TCH             = "Event_tech";


    //  Constructor
    Event(String evtId, String evtName, String evtDate, String evtStart, String evtEnd, String evtTxt, String evtSecGot, String evtSec, String evtBarGot, String evtBar, String evtCrwGot, String evtCrw, String evtTchGot, String evtTch) {
        this.evtId = evtId;
        this.evtName = evtName;
        this.evtDate = evtDate;
        this.evtStart = evtStart;
        this.evtEnd = evtEnd;
        this.evtTxt = evtTxt;
        this.evtSecGot = evtSecGot;
        this.evtSec = evtSec;
        this.evtBarGot = evtBarGot;
        this.evtBar = evtBar;
        this.evtCrwGot = evtCrwGot;
        this.evtCrw = evtCrw;
        this.evtTchGot = evtTchGot;
        this.evtTch = evtTch;

    }

    // Empty Constructor
    Event() {}

    public Event(JSONObject jsonEvent) {
        this.evtId          = jsonEvent.optString(KOL_ID);
        this.evtName        = jsonEvent.optString(KOL_NAME);
        this.evtDate        = jsonEvent.optString(KOL_DATE);
        this.evtStart       = jsonEvent.optString(KOL_START);
        this.evtEnd         = jsonEvent.optString(KOL_END);
        this.evtTxt         = jsonEvent.optString(KOL_TEXT);
        this.evtSecGot      = jsonEvent.optString(KOL_SEC_GOT);
        this.evtSec         = jsonEvent.optString(KOL_SEC);
        this.evtBarGot      = jsonEvent.optString(KOL_BAR_GOT);
        this.evtBar         = jsonEvent.optString(KOL_BAR);
        this.evtCrwGot      = jsonEvent.optString(KOL_CRW_GOT);
        this.evtCrw         = jsonEvent.optString(KOL_CRW);
        this.evtTchGot      = jsonEvent.optString(KOL_TCH_GOT);
        this.evtTch         = jsonEvent.optString(KOL_TCH);
    }

    public static ArrayList<Event> lagEventListe(String jsonEventString)
            throws JSONException, NullPointerException {

        ArrayList<Event> eventListe = new ArrayList<Event>();
        JSONObject jsonData = new JSONObject(jsonEventString);
        JSONArray jsonEventTabell = jsonData.optJSONArray(TABELL_NAVN);
        System.out.println(jsonEventString);

        for(int i=0; i< jsonEventTabell.length(); i++) {
            JSONObject jsonEvent = (JSONObject) jsonEventTabell.get(i);

            Event detteEventet = new Event(jsonEvent);
            eventListe.add(detteEventet);

        }
        return eventListe;
    }



    JSONObject toJSONObject() {
        JSONObject jsonEvent = new JSONObject();
        try {
            jsonEvent.put(KOL_ID, this.evtId);
            jsonEvent.put(KOL_NAME, this.evtName);
            jsonEvent.put(KOL_DATE, this.evtDate);
            jsonEvent.put(KOL_START, this.evtStart);
            jsonEvent.put(KOL_END, this.evtEnd);
            jsonEvent.put(KOL_TEXT, this.evtTxt);
            jsonEvent.put(KOL_SEC_GOT, this.evtSecGot);
            jsonEvent.put(KOL_SEC, this.evtSec);
            jsonEvent.put(KOL_BAR_GOT, this.evtBarGot);
            jsonEvent.put(KOL_BAR, this.evtBar);
            jsonEvent.put(KOL_CRW_GOT, this.evtCrwGot);
            jsonEvent.put(KOL_CRW, this.evtCrw);
            jsonEvent.put(KOL_TCH_GOT, this.evtTchGot);
            jsonEvent.put(KOL_TCH, this.evtTch);
        }
        catch (JSONException e) {
            return null;
        }
        return jsonEvent;
    }

    public static Event lagEventFromJSONString(String jsonEventString) {
        Event nyEvent = null;
        try {
            JSONObject jsonEventObject = new JSONObject(jsonEventString);
            if (jsonEventObject!=null) {
                Log.d("JSONEventObject", jsonEventObject.toString());
                nyEvent = new Event(jsonEventObject);
                Log.d("nyEvent:", nyEvent.toString());
            }
        }
        catch (Exception e) {
        }
        return nyEvent;
    }



    public String getEvtSecGot() {
        return evtSecGot;
    }

    public String getEvtBarGot() {
        return evtBarGot;
    }

    public String getEvtCrwGot() {
        return evtCrwGot;
    }

    public String getEvtTchGot() {
        return evtTchGot;
    }

    public String getEvtId() {
        return evtId;
    }

    public String getEvtName() {
        return evtName;
    }

    public String getEvtDate() {
        return evtDate;
    }

    public String getEvtStart() {
        return evtStart;
    }

    public String getEvtEnd() {
        return evtEnd;
    }

    public String getEvtTxt() {
        return evtTxt;
    }

    public String getEvtSec() {
        return evtSec;
    }

    public String getEvtBar() {
        return evtBar;
    }

    public String getEvtCrw() {
        return evtCrw;
    }

    public String getEvtTch() {
        return evtTch;
    }

}
