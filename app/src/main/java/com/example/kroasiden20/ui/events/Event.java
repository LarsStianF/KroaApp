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
     String evtSec;
     String evtBar;
     String evtCrw;
     String evtTch;

    static final String TABELL_NAVN         = "event";
    static final String KOL_ID              = "ID";
    static final String KOL_NAME            = "Name";
    static final String KOL_DATE            = "Date";
    static final String KOL_START           = "Time_Start";
    static final String KOL_END             = "Time_End";
    static final String KOL_TEXT            = "Event_text";
    static final String KOL_SEC             = "Event_sec";
    static final String KOL_BAR             = "Event_bar";
    static final String KOL_CRW             = "Event_crew";
    static final String KOL_TCH             = "Event_tech";


    //  Constructor
    Event(String evtId, String evtName, String evtDate, String evtStart, String evtEnd, String evtTxt, String evtSec, String evtBar, String evtCrw, String evtTch) {
        this.evtId = evtId;
        this.evtName = evtName;
        this.evtDate = evtDate;
        this.evtStart = evtStart;
        this.evtEnd = evtEnd;
        this.evtTxt = evtTxt;
        this.evtSec = evtSec;
        this.evtBar = evtBar;
        this.evtCrw = evtCrw;
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
        this.evtSec         = jsonEvent.optString(KOL_SEC);
        this.evtBar         = jsonEvent.optString(KOL_BAR);
        this.evtCrw         = jsonEvent.optString(KOL_CRW);
        this.evtTch         = jsonEvent.optString(KOL_TCH);
    }

    public static ArrayList<Event> lagEventListe(String jsonEventString)
            throws JSONException, NullPointerException {

        ArrayList<Event> eventListe = new ArrayList<Event>();
        JSONObject jsonData = new JSONObject(jsonEventString);
        JSONArray jsonEventTabell = jsonData.optJSONArray(TABELL_NAVN);

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
            jsonEvent.put(KOL_SEC, this.evtSec);
            jsonEvent.put(KOL_BAR, this.evtBar);
            jsonEvent.put(KOL_CRW, this.evtCrw);
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

}
