package com.example.kroasiden20.ui.events;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kroasiden20.EventActivity;
import com.example.kroasiden20.R;
import com.example.kroasiden20.VolleyAdapter;

import org.json.JSONException;

import java.util.ArrayList;

public class EventsFragment extends Fragment implements Response.Listener<String>, Response.ErrorListener {

    private static final String DEBUG_TAG = "EventFragmentEvent";
    public final static String EVENT_INTENT_ID =
            "com.example.kroasiden20.ui.events.EventsFragment.VIS_EVENT";
    public final static int REQUEST_CODE_VIS_VARE = 0;
    public final static String ENDPOINT = "https://itfag.usn.no/~216714/api.php";

    private VolleyAdapter restDbAdapter;
    private RecyclerView evtRecyclerView;
    private ArrayList<Event> evtArrayList = new ArrayList<Event>();
    private EventAdapter evtAdapter;

    private EventsViewModel eventsViewModel;

    Event valgtEvent;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventsViewModel =
                ViewModelProviders.of(this).get(EventsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_events, container, false);


        // Initialize the RecyclerView.
        evtRecyclerView = root.findViewById(R.id.recyclerView);
        // Set the Layout Manager.
        evtRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        // Initialize the ArrayList that will contain the data.
        evtArrayList = new ArrayList<>();
        // Initialize the adapter and set it to the RecyclerView.
        evtAdapter = new EventAdapter(this.getActivity(), evtArrayList);
        evtRecyclerView.setAdapter(evtAdapter);
        restDbAdapter =new VolleyAdapter(root.getContext());
        // Get the data.
        lesAlleEventer();

        return root;
    }

    public void lesAlleEventer() {
        String eventliste_URL = ENDPOINT + "/event?order=Date,asc&transform=1";
        Toast.makeText(this.getActivity(), eventliste_URL, Toast.LENGTH_LONG).show();
        System.out.println(eventliste_URL);
        if(isOnline()) {
            System.out.println("JEG ER ONLINE FOR FOAEN !!!");
            RequestQueue queue = Volley.newRequestQueue(this.getActivity());
            StringRequest stringRequest =
                    new StringRequest(Request.Method.GET, eventliste_URL, this, this);
            queue.add(stringRequest);
            System.out.println(stringRequest);
        } else {
            Toast.makeText(this.getActivity(), "Ingen nettverkstilgang. Kan ikke laste varer.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponse(String response) {
        try {
            evtArrayList = Event.lagEventListe(response);
            oppdaterEventListView(evtArrayList);
        }
        catch (JSONException e) {
            Toast.makeText(this.getActivity(), "Ugyldige JSON-data.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this.getActivity(), "Volley feilet!", Toast.LENGTH_LONG).show();
    }

    public void oppdaterEventListView(ArrayList<Event> nyEventListe) {
        evtAdapter = new EventAdapter(this.getActivity(), evtArrayList);
        evtRecyclerView.setAdapter(evtAdapter);
        evtRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) this.getActivity().getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void visEvent(String eventID) {
        Intent startIntent = new Intent(this.getActivity(), EventActivity.class);
        startIntent.putExtra(EventsFragment.EVENT_INTENT_ID, eventID);
        startActivityForResult(startIntent, EventsFragment.REQUEST_CODE_VIS_VARE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data ) {
        if (requestCode == REQUEST_CODE_VIS_VARE && resultCode == Activity.RESULT_OK){
            Log.d("EventsFragment: ", "onActivityResult()");
            lesAlleEventer();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}