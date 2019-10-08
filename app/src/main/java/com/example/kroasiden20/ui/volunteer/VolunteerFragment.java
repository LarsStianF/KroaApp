package com.example.kroasiden20.ui.volunteer;

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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kroasiden20.R;


import org.json.JSONException;

import java.util.ArrayList;

public class VolunteerFragment extends Fragment implements Response.Listener<String>, Response.ErrorListener {
    public final static String ENDPOINT = "https://itfag.usn.no/~163357/api.php";
    private RecyclerView vRecyclerView;
    private ArrayList<Volunteer> vVolunteerData;
    public final static String VOL_INTENT_ID =
            "com.example.kroasiden20.ui.volunteer.VolunteerFragment.SHOW_VOLUNTEER";
    private ArrayList<Volunteer> volArrayList = new ArrayList<Volunteer>();
    private VolunteerAdapter vAdapter;
    private VolunteerViewModel volunteerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        volunteerViewModel =
                ViewModelProviders.of(this).get(VolunteerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_volunteers, container, false);


        // Initialize the RecyclerView.
        vRecyclerView = root.findViewById(R.id.recyclerView);
        // Set the Layout Manager.
        vRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        // Initialize the ArrayList that will contain the data.
        vVolunteerData = new ArrayList<>();
        // Initialize the adapter and set it to the RecyclerView.
        vAdapter = new VolunteerAdapter(this.getActivity(), vVolunteerData);
        vRecyclerView.setAdapter(vAdapter);
        vRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        // Get the data.
        initializeData();

        return root;
    }

    // Method that initializes the data for the Volunteerlist
    public void initializeData() {
        String volunteerList_URL = ENDPOINT + "/volunteer?order=ID,asc&transform=1";

        if(isOnline()) {
            RequestQueue queue = Volley.newRequestQueue(this.getActivity());
            StringRequest stringRequest =
                    new StringRequest(Request.Method.GET, volunteerList_URL, this, this);
            queue.add(stringRequest);
        } else {
            Toast.makeText(this.getActivity(), "ERROR: NO CONNECTION. STATUS: INOPERATIVE", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponse(String response) {
        try {
            volArrayList = Volunteer.populateVolunteerList(response);
            populateVolunteerListView(volArrayList);
        }
        catch (JSONException e) {
            Toast.makeText(this.getActivity(), "Ugyldige JSON-data.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this.getActivity(), "Volley feilet!", Toast.LENGTH_LONG).show();
    }

    public void populateVolunteerListView(ArrayList<Volunteer> volArrayList) {
        vAdapter = new VolunteerAdapter(this.getActivity(), volArrayList);
        vRecyclerView.setAdapter(vAdapter);
        vRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) this.getActivity().getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    public final static int REQUEST_CODE = 0;



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data ) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Log.d("VolunteerFragment: ", "onActivityResult()");
            initializeData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // Method that refreshes the page upon new viewing. initializes the data anew.
    @Override
    public void onResume(){
        super.onResume();
        initializeData();

    }


}