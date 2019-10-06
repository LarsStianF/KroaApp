package com.example.kroasiden20.ui.profile;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Stats {
    String name;
    int unit;
    int worked;
    int total;
    String jippi;

    static final String TABELL_NAVN         = "Volunteer";
    static final String KOL_VOL_NAME        = "Firstname";
    static final String KOL_VOL_UNIT        = "Unit";

    static final String TABELL_NAVN2        = "Event";
    static final String KOL_DATE            = "Date";

    static final String TABELL_NAVN3        = "event_volunteer";
    static final String KOL_EV_VOL_ID       = "month";

    static final String KOL_EV_VOL_ID_TOTAL = "total";
    static final String KOL_JIPPI           = "Jippi";

    public Stats(String name, int unit, int worked, int total, String jippi) {
        this.name = name;
        this.unit = unit;
        this.worked = worked;
        this.total = total;
        this.jippi = jippi;
    }

    public Stats() {

    }

    public Stats(JSONObject JsonStats) {
        this.name = JsonStats.optString(KOL_VOL_NAME);
        this.unit = JsonStats.optInt(KOL_VOL_UNIT);
        this.worked = JsonStats.optInt(KOL_EV_VOL_ID);
        this.total = JsonStats.optInt(KOL_EV_VOL_ID_TOTAL);
        this.jippi = JsonStats.optString(KOL_JIPPI);
    }

    public static ArrayList<Stats> lagStats(String jsonStatsString) throws JSONException, NullPointerException {
        ArrayList<Stats> statsList = new ArrayList<>();
        JSONObject jsonData = new JSONObject(jsonStatsString);
        JSONArray jsonVolTable = jsonData.optJSONArray(TABELL_NAVN);
        for (int i = 0; i < jsonVolTable.length(); i++) {
            JSONObject jsonStats = (JSONObject) jsonVolTable.get(i);
            Stats stats = new Stats(jsonStats);
            statsList.add(stats);
        }
        return statsList;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonStats = new JSONObject();
        try {
            jsonStats.put(KOL_VOL_UNIT, this.unit);
            jsonStats.put(KOL_VOL_NAME, this.name);
            jsonStats.put(KOL_EV_VOL_ID, this.worked);
            jsonStats.put(KOL_EV_VOL_ID_TOTAL, this.total);
            jsonStats.put(KOL_JIPPI, this.jippi);
        }
        catch (JSONException e) {
            return null;
        }
        return jsonStats;
    }




}
