package com.example.kroasiden20.ui.volunteer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class Volunteer {

    // Member variables representing the title and information about the sport.
    private String id;
    private String name;
    private String role;
    private String email;
    private String phone;
    private String lastVol;


    static final String TABLE_NAME = "volunteer";
    static final String COL_VOLUNTEER_ID = "ID";
    static final String COL_VOLUNTEER_FIRSTNAME = "Firstname";
    static final String COL_VOLUNTEER_LASTNAME = "Lastname";
    static final String COL_VOLUNTEER_ROLE = "user_type";
    static final String COL_VOLUNTEER_EMAIL = "Email";
    static final String COL_VOLUNTEER_PHONE = "nr";
    static final String COL_VOLUNTEER_lastVol = "";

    Volunteer(String id, String firstname, String lastname, String role, String email, String phone, String lastVol) {
        this.id = id;
        this.name = firstname + " " + lastname;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.lastVol = lastVol;

    }

    public Volunteer(JSONObject jsonVolunteer){
        this.id = jsonVolunteer.optString(COL_VOLUNTEER_ID);
        this.name = jsonVolunteer.optString(COL_VOLUNTEER_FIRSTNAME) + " " + jsonVolunteer.optString(COL_VOLUNTEER_LASTNAME);
        this.role = jsonVolunteer.optString(COL_VOLUNTEER_ROLE);
        this.email = jsonVolunteer.optString(COL_VOLUNTEER_EMAIL);
        this.phone = jsonVolunteer.optString(COL_VOLUNTEER_PHONE);
        this.lastVol = jsonVolunteer.optString(COL_VOLUNTEER_lastVol);
    }

    public static ArrayList<Volunteer> populateVolunteerList(String jsonVolunteerString)
            throws JSONException, NullPointerException {
        ArrayList<Volunteer> volunteerList = new ArrayList<Volunteer>();
        JSONObject jsonData  = new JSONObject(jsonVolunteerString);
        JSONArray jsonVareTabell = jsonData.optJSONArray(TABLE_NAME);
        for(int i = 0; i < jsonVareTabell.length(); i++) {
            JSONObject jsonVolunteer = (JSONObject) jsonVareTabell.get(i);
            Volunteer thisVolunteer = new Volunteer(jsonVolunteer);
            volunteerList.add(thisVolunteer);
        }
        return volunteerList;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonVolunteer = new JSONObject();
        try {
            jsonVolunteer.put(COL_VOLUNTEER_ID, this.id);
            jsonVolunteer.put(COL_VOLUNTEER_FIRSTNAME + " " + COL_VOLUNTEER_LASTNAME, this.name);
            jsonVolunteer.put(COL_VOLUNTEER_ROLE, this.role);
            jsonVolunteer.put(COL_VOLUNTEER_EMAIL, this.email);
            jsonVolunteer.put(COL_VOLUNTEER_PHONE, this.phone);
            jsonVolunteer.put(COL_VOLUNTEER_lastVol, this.lastVol);
        }
        catch (JSONException e) {
            return null;
        }
        return jsonVolunteer;
    }

    String getID() { return id; }

    String getName() {
        return name;
    }

    String getRole() {
        return role;
    }


    String getEmail() {
        return email;
    }

    String getPhone() { return phone; }

    String getLastVol() {
        return lastVol;
    }



}
