package com.example.kroasiden20.ui.profile;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.kroasiden20.R;

import org.json.JSONObject;

import java.util.ArrayList;


public class ProfileFragment extends Fragment implements Response.Listener<String>, Response.ErrorListener {

    private ProfileViewModel profileViewModel;
    private TextView p_Welcome_Toast, p_units, p_Times_Worked, p_Monthly_Worked, p_Jippi;
    public final static String ENDPOINT = "https://itfag.usn.no/~163357/api.php";
    private RequestQueue queue;
    private ArrayList<Stats> statsArrayList = new ArrayList<>();
    private ProfileAdapter pAdapter;
    private ScrollView pCardView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        p_units = root.findViewById(R.id.unitStats);
        p_Welcome_Toast = root.findViewById(R.id.welcomeName);
        p_Times_Worked = root.findViewById(R.id.totalStats);
        p_Monthly_Worked = root.findViewById(R.id.monthStats);
        p_Jippi = root.findViewById(R.id.jippiStats);
        pGet_Volunteer();
        return root;
    }
    public void pGet_Volunteer() {
        String pVolunteer_URL = ENDPOINT + "/volunteer?filter=id,eq,2&columns=Unit&transform=1";
        if (isOnline()) {
            RequestQueue queue = Volley.newRequestQueue(this.getActivity());
            StringRequest stringRequest =
                    new StringRequest(Request.Method.GET, pVolunteer_URL, this, this);
            queue.add(stringRequest);
        }else Toast.makeText(getActivity(), "Ingen nettverkstilgang.", Toast.LENGTH_SHORT).show();
    }

    public void insertStats(Stats newUnit){
        String units_URL = ENDPOINT + "/volunteer?filter=id,eq,2&columns=Unit&transform=1";
        JSONObject jsonUnits = newUnit.toJSONObject();
        addJSONRequest(Request.Method.GET, units_URL, jsonUnits);
    }


    private void addJSONRequest(int RequestMethod, String url, JSONObject data) {
        if (isOnline()) {
            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(RequestMethod, url, data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Send resultat tilbake til kaller
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Varsle kaller om feil
                        }
                    });
            queue.add(jsonObjRequest);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this.getActivity(), "Volley feilet!", Toast.LENGTH_LONG).show();
    }

    // Sjekker om nettverkstilgang
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) this.getActivity().getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void onResponse(String response) {

    }
}