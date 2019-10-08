package com.example.kroasiden20.ui.volunteer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Volunteer {

    String id;
    String fName;
    String lName;
    String role;
    String email;
    String password;
    String phone;
    String lastVol;


    static final String TABLE_NAME = "volunteer";
    static final String COL_VOLUNTEER_ID = "ID";
    static final String COL_VOLUNTEER_FIRSTNAME = "Firstname";
    static final String COL_VOLUNTEER_LASTNAME = "Lastname";
    static final String COL_VOLUNTEER_ROLE = "user_type";
    static final String COL_VOLUNTEER_EMAIL = "Email";
    static final String COL_VOLUNTEER_PASS = "Password";
    static final String COL_VOLUNTEER_PHONE = "nr";
    static final String COL_VOLUNTEER_lastVol = "";

    public Volunteer(String id, String firstname, String lastname, String role, String email, String password, String phone, String lastVol) {
        this.id = id;
        this.fName = firstname;
        this.lName = lastname;
        this.role = role;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.lastVol = lastVol;

    }

    public Volunteer(JSONObject jsonVolunteer){
        this.id = jsonVolunteer.optString(COL_VOLUNTEER_ID);
        this.fName = jsonVolunteer.optString(COL_VOLUNTEER_FIRSTNAME);
        this.lName = jsonVolunteer.optString(COL_VOLUNTEER_LASTNAME);
        this.role = jsonVolunteer.optString(COL_VOLUNTEER_ROLE);
        this.email = jsonVolunteer.optString(COL_VOLUNTEER_EMAIL);
        this.password = jsonVolunteer.optString(COL_VOLUNTEER_PASS);
        this.phone = jsonVolunteer.optString(COL_VOLUNTEER_PHONE);
        this.lastVol = jsonVolunteer.optString(COL_VOLUNTEER_lastVol);
    }
    public Volunteer() {}

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
            jsonVolunteer.put(COL_VOLUNTEER_FIRSTNAME, this.fName);
            jsonVolunteer.put(COL_VOLUNTEER_LASTNAME, this.lName);
            jsonVolunteer.put(COL_VOLUNTEER_PHONE, this.phone);
            jsonVolunteer.put(COL_VOLUNTEER_EMAIL, this.email);
            jsonVolunteer.put(COL_VOLUNTEER_PASS, this.password);
            jsonVolunteer.put(COL_VOLUNTEER_ROLE, this.role);
            jsonVolunteer.put(COL_VOLUNTEER_lastVol, this.lastVol);
        }
        catch (JSONException e) {
            return null;
        }
        return jsonVolunteer;
    }

    String getID() { return id; }

    String getfName() {
        return fName;
    }

    String getlName() {
        return lName;
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

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLastVol(String lastVol) {
        this.lastVol = lastVol;
    }
}
