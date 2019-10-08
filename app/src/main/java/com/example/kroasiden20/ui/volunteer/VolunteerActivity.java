/**
 ** This activity governs the page which shows up when clicking on a Volunteer card in the volunteer list
 */

package com.example.kroasiden20.ui.volunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kroasiden20.R;
import org.json.JSONObject;

public class VolunteerActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {

    // Initializes Volunteer object.
    private Volunteer curVol = new Volunteer();

    public final static String ENDPOINT = "https://itfag.usn.no/~163357/api.php";
    Boolean updateOK = false;
    Spinner volRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        TextView volName = findViewById(R.id.volunteerName);
        volRole = findViewById(R.id.userRole);
        TextView volEmail = findViewById(R.id.volunteerEmail);
        TextView volPhone = findViewById(R.id.volunteerPhone);
        TextView volLastVol = findViewById(R.id.lastVol);
        Button btnDelVol = findViewById(R.id.btnDelVol);
        Button btnUpdVol = findViewById(R.id.btnUpdVol);


        //Spinner "User Role" dropdown menu for changing of volunteer roles. Values should be gotten from database. But as JOIN in API is buggy, this is a quick fix
        String[] roles = new String[]{"Root", "Daglig Leder", "Volunteer Coordinator", "Event Manager", "Manager", "Volunteer"};
        ArrayAdapter<String> volRoleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        volRole.setAdapter(volRoleAdapter);
        String curRole = getIntent().getStringExtra("Role");
        int spinnerPosition = Integer.parseInt(curRole)-1;
        volRole.setSelection(spinnerPosition);

        // Sets volunteer info
        volName.setText(getIntent().getStringExtra("Name"));
        volEmail.setText(getIntent().getStringExtra("Email"));
        volPhone.setText(getIntent().getStringExtra("Phone"));
        volLastVol.setText(getIntent().getStringExtra("LastVol"));


        // Button for deleting volunteer, sends you back to previous screen on complete, method in VolunteerFragment refreshes data.
        btnDelVol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               deleteVol(getIntent().getStringExtra("ID"));
               finish();

            }

        });

        // Button for updating volunteer
        btnUpdVol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String volID = getIntent().getStringExtra("ID");
                updateVolunteerRole();
                updateVol(volID, curVol);
            }

        });


    }

    // Updates the role of Volunteer object
    private void updateVolunteerRole() {
        int thisRole = volRole.getSelectedItemPosition()+1;
        curVol.role = String.valueOf(thisRole);

    }


    // Does nothing for now
    @Override
    public void onResponse(String response) {

    }


    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Volley feilet!", Toast.LENGTH_LONG).show();
    }


    // Method for deleting volunteer
    private void deleteVol( String volID) {

        String volunteerDelete_URL = ENDPOINT + "/volunteer/";
        if(isOnline()) {
            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest stringRequest =
                    new StringRequest(Request.Method.DELETE, volunteerDelete_URL + volID, this, this);
            queue.add(stringRequest);
            Toast.makeText(this, "User Deleted successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "ERROR: NO CONNECTION. STATUS: INOPERATIVE", Toast.LENGTH_SHORT).show();
        }

    }

    // Method for updating volunteer role
    private void updateVol(String volID, Volunteer curVol) {
        String volunteerUpdateURL = ENDPOINT + "/volunteer/" + volID;
        JSONObject jsonVolunteer = curVol.toJSONObject();
        if(isOnline()) {
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.PUT, volunteerUpdateURL, jsonVolunteer, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    updateOK = true;
                }
            },  new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    updateOK = false;
                }
            });

            queue.add(jsonObjRequest);
            System.out.println(jsonObjRequest);
            Toast.makeText(this, "User role updated successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "ERROR: NO CONNECTION. STATUS: INOPERATIVE", Toast.LENGTH_SHORT).show();
        }
    }

    // Sjekker om nettverkstilgang
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) this.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
