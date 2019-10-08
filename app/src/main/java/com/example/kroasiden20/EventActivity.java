package com.example.kroasiden20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kroasiden20.ui.events.Event;

import org.json.JSONObject;

/**
 * @author Kim V Pedersen
 */
public class EventActivity extends AppCompatActivity  implements Response.Listener<String>, Response.ErrorListener {

    public EditText evtName;
    public EditText evtDate;
    public EditText evtStart;
    public EditText evtEnd;
    public EditText evtText;
    public final static String ENDPOINT = "https://itfag.usn.no/~163357/api.php";
    Boolean updateOK = false;

    private Event curEvent = new Event();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);




        evtName = findViewById(R.id.nameDetail);
        evtDate = findViewById(R.id.dateDetail);
        evtStart = findViewById(R.id.startDetail);
        evtEnd = findViewById(R.id.endDetail);
        evtText = findViewById(R.id.textDetail);
        TextView secNeed = findViewById(R.id.secDetailNeed);
        TextView barNeed = findViewById(R.id.barDetailNeed);
        TextView crwNeed = findViewById(R.id.crwDetailNeed);
        TextView tchNeed = findViewById(R.id.tchDetailNeed);
        TextView secGot = findViewById(R.id.secDetailGot);
        TextView barGot = findViewById(R.id.barDetailGot);
        TextView crwGot = findViewById(R.id.crwDetailGot);
        TextView tchGot = findViewById(R.id.tchDetailGot);

        Button btnDelEvent = findViewById(R.id.btnDelEvent);
        final Button btnUpdEvent = findViewById(R.id.btnUpdEvent);

        evtName.setText(getIntent().getStringExtra("Name"));
        evtDate.setText(getIntent().getStringExtra("Date"));
        evtStart.setText(getIntent().getStringExtra("Start"));
        evtEnd.setText(getIntent().getStringExtra("End"));
        evtText.setText(getIntent().getStringExtra("Txt"));
        secNeed.setText(getIntent().getStringExtra("secNeed"));
        barNeed.setText(getIntent().getStringExtra("barNeed"));
        crwNeed.setText(getIntent().getStringExtra("crwNeed"));
        tchNeed.setText(getIntent().getStringExtra("tchNeed"));
        secGot.setText(getIntent().getStringExtra("secGot"));
        barGot.setText(getIntent().getStringExtra("barGot"));
        crwGot.setText(getIntent().getStringExtra("crwGot"));
        tchGot.setText(getIntent().getStringExtra("tchGot"));




        evtDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (evtDate.isFocusable()) {
                    DatePickerDialog evtDatePicker = new DatePickerDialog(EventActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            evtDate.setText(year + "-" + month + "-" + dayOfMonth);
                        }
                    },2019,1,1);
                    evtDatePicker.show();
                }
            }
        });

        evtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (evtStart.isFocusable()){
                    TimePickerDialog evtStartTimePicker = new TimePickerDialog(EventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                            evtStart.setText(hourOfDay + ":" + minutes);
                        }
                    }, 0, 0, true);
                    evtStartTimePicker.show();

                }


            }
        });

        evtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (evtEnd.isFocusable()){
                    TimePickerDialog evtStartTimePicker = new TimePickerDialog(EventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                            evtEnd.setText(hourOfDay + ":" + minutes);
                        }
                    }, 0, 0, true);
                    evtStartTimePicker.show();

                }


            }
        });

        btnDelEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEvent(getIntent().getStringExtra("ID"));
                finish();

            }

        });

        btnUpdEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventID = getIntent().getStringExtra("ID");
                updateEventJSON();
                evtName.setFocusableInTouchMode(true);
                evtName.setClickable(true);

                evtDate.setFocusableInTouchMode(true);
                evtDate.setClickable(true);

                evtStart.setFocusableInTouchMode(true);
                evtStart.setClickable(true);

                evtEnd.setFocusableInTouchMode(true);
                evtEnd.setClickable(true);

                evtText.setFocusableInTouchMode(true);
                evtText.setClickable(true);

                if (btnUpdEvent.getText().equals("Confirm update")){
                    updateEventJSON();
                    updateEvent(eventID, curEvent);
                   // finish();
                }
                btnUpdEvent.setText("Confirm update");

            }

        });
    }

    private void deleteEvent(String eventID) {
        String eventDelete_URL = ENDPOINT + "/event/";
        if(isOnline()) {
            System.out.println("STATUS: OPERATIONAL");
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest =
                    new StringRequest(Request.Method.DELETE, eventDelete_URL + eventID, this, this);
            queue.add(stringRequest);
            System.out.println(stringRequest);
            Toast.makeText(this, "Event Deleted successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "ERROR: NO CONNECTION. STATUS: INOPERATIVE", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateEventJSON() {
        curEvent.setEvtName(evtName.getText().toString());
        curEvent.setEvtDate(evtDate.getText().toString());
        curEvent.setEvtStart(evtStart.getText().toString());
        curEvent.setEvtEnd(evtEnd.getText().toString());
        curEvent.setEvtTxt(evtText.getText().toString());

    }
    private void updateEvent(String eventID, Event curEvent){
        String eventUpdateURL = ENDPOINT + "/event/" + eventID;
        JSONObject jsonEvent = curEvent.toJSONObject();
        if(isOnline()) {
            System.out.println("STATUS: OPERATIONAL");
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.PUT, eventUpdateURL, jsonEvent, new Response.Listener<JSONObject>() {
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
            Toast.makeText(this, "Event updated successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "ERROR: NO CONNECTION. STATUS: INOPERATIVE", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {

    }

    // Sjekker om nettverkstilgang
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) this.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
