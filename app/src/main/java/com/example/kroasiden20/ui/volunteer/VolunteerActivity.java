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

    private Volunteer curVol = new Volunteer();

    public final static String ENDPOINT = "https://itfag.usn.no/~163357/api.php";
    Boolean updateOK = false;
    Spinner volRole;
    String volIDDelete;
    String postmanTest = "{\n" +
            "\n" +
            "\"Firstname\": \"Postman\",\n" +
            "\"Lastname\": \"Test\",\n" +
            "\"nr\": \"66664444\",\n" +
            "\"Email\": \"postman@test.no\",\n" +
            "\"Password\": \"postman123\",\n" +
            "\"Unit\": 0,\n" +
            "\"user_type\": 6\n" +
            "\n" +
            "}";

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

        volName.setText(getIntent().getStringExtra("Name"));
        //Spinner "User Role" dropdown menu for changing of volunteer roles.
        String[] roles = new String[]{"Root", "Daglig Leder", "Volunteer Coordinator", "Event Manager", "Manager", "Volunteer"};
        ArrayAdapter<String> volRoleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        volRole.setAdapter(volRoleAdapter);
        String curRole = getIntent().getStringExtra("Role");
        System.out.println("Current Role: " + curRole);

        int spinnerPosition = Integer.parseInt(curRole)-1;
        System.out.println("Spinner Position: " + spinnerPosition);

        volRole.setSelection(spinnerPosition);

        volEmail.setText(getIntent().getStringExtra("Email"));
        volPhone.setText(getIntent().getStringExtra("Phone"));
        volLastVol.setText(getIntent().getStringExtra("LastVol"));



        btnDelVol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               deleteVol(getIntent().getStringExtra("ID"));
                finish();

            }

        });

        btnUpdVol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String volID = getIntent().getStringExtra("ID");
                updateVolunteerRole();
                updateVol(volID, curVol);
                //finish();
            }

        });


    }

    private void updateVolunteerRole() {
        int thisRole = volRole.getSelectedItemPosition()+1;
        curVol.role = String.valueOf(thisRole);


        /*
        String thisRole = volRole.getSelectedItem().toString();
        Toast.makeText(this, "Current selected role: " + thisRole , Toast.LENGTH_LONG).show();
        if (thisRole.equals("Root")){
            curVol.role = "1";
        } else if (thisRole.equals("Daglig Leder")){
            curVol.role = "2";
        } else if (thisRole.equals("Volunteer Coordinator")) {
            curVol.role = "3";
        } else if (thisRole.equals("Event Manager")) {
            curVol.role = "4";
        }  else if (thisRole.equals("Manager")) {
            curVol.role = "5";
        } else {
            curVol.role = "6";
        }
*/
        //
    }


    @Override
    public void onResponse(String response) {

    }


    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "Volley feilet!", Toast.LENGTH_LONG).show();
    }


    private void deleteVol( String volID) {

        String volunteerDelete_URL = ENDPOINT + "/volunteer/";
        if(isOnline()) {
            System.out.println("STATUS: OPERATIONAL");
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest =
                    new StringRequest(Request.Method.DELETE, volunteerDelete_URL + volID, this, this);
            queue.add(stringRequest);
            System.out.println(stringRequest);
            Toast.makeText(this, "User Deleted successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "ERROR: NO CONNECTION. STATUS: INOPERATIVE", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateVol(String volID, Volunteer curVol) {
        String volunteerUpdateURL = ENDPOINT + "/volunteer/" + volID;
        JSONObject jsonVolunteer = curVol.toJSONObject();
        if(isOnline()) {
            System.out.println("STATUS: OPERATIONAL");
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
