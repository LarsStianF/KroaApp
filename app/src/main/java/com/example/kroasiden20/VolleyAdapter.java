package com.example.kroasiden20;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class VolleyAdapter {
    static final String endpoint = "https://itfag.usn.no/~216714/api.php";

    private Context ctx;
    private RequestQueue queue;

    public VolleyAdapter(Context ctx) {
        this.ctx = ctx;
        queue = Volley.newRequestQueue(ctx);
    }

    private boolean updatedOK = false;

    public boolean isRestCallOK() { return updatedOK; }

    private void addJSONRequest(int RequestMethod, String URL, JSONObject data) {
        if (isOnline()) {
            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(RequestMethod, URL, data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Foreløpig ingen sjekk av responsen for å se om lagring er vellykket  !!!
                            updatedOK = true;
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            updatedOK = false;
                        }
                    });
            queue.add(jsonObjRequest);
        }
    }

    // Sjekker om nettverkstilgang
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) ctx.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
