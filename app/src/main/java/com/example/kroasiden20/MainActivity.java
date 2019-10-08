package com.example.kroasiden20;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Kim V Pedersen
 */
public class MainActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    public final static String ENDPOINT = "https://itfag.usn.no/~163357/api.php";
    EditText inputEmail;
    EditText inputPassword;
    ArrayList userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEmail = findViewById(R.id.email_login_field);
        inputPassword = findViewById(R.id.password_login_field);
    }

    public void userLogin(View view) {
            String email = inputEmail.getText().toString();
            String pass = inputPassword.getText().toString();
       if(!emailCheck(email) | !passCheck(pass)) {
           return;
        } else {
            RequestQueue queue = Volley.newRequestQueue(this);
            String getUser = ENDPOINT + "/volunteer?transform=1&filter=Email,eq," + inputEmail.getText().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, getUser, this, this);
            queue.add(stringRequest);
        }

    }

    public void userRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    boolean emailCheck(String email) {
        if(email.isEmpty()) {
            inputEmail.setError("Field can't be empty");
            return false;
        } else {
            inputPassword.setError(null);
            return true;
        }
    }

    boolean passCheck(String pass) {
        if(pass.isEmpty()) {
            inputPassword.setError("Field can't be empty");
            return false;
        } else {
            inputPassword.setError(null);
            return true;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {

        try {
            userData = getUserdat(response);

            String userEmail = inputEmail.getText().toString();
            String userPass = RegisterActivity.passwordHash(inputPassword.getText().toString());
            String emailDB = userData.get(0).toString();
            String passwordDB = userData.get(1).toString();
            if(userEmail.equals(emailDB) && userPass.equals(passwordDB)) {


                String name = userData.get(5).toString() + " " + userData.get(6).toString();
                String id = userData.get(2).toString();
                String units = userData.get(3).toString();
                String usertype = userData.get(4).toString();

                login(id, name, units, usertype);


            } else {
                inputPassword.setError("Wrong username or password.");
            }


        } catch (JSONException e) {
            Toast.makeText(this, "Error in Json", Toast.LENGTH_SHORT).show();
        }

    }

    private ArrayList getUserdat(String response) throws JSONException, NullPointerException {
        JSONObject jsonData = new JSONObject(response);
        JSONArray dataTabell = jsonData.optJSONArray("volunteer");
        JSONObject userDat = (JSONObject) dataTabell.get(0);
        String email = userDat.optString("Email");
        String password = userDat.optString("Password");
        String units = userDat.optString("Unit");
        String id = userDat.getString("ID");
        String userType = userDat.getString("user_type");
        String fName = userDat.getString("Firstname");
        String lName = userDat.getString("Lastname");


        ArrayList<String> userData = new ArrayList<>(5);
        userData.add(email);
        userData.add(password);
        userData.add(id);
        userData.add(units);
        userData.add(userType);
        userData.add(fName);
        userData.add(lName);



        return userData;
    }

    public void login(String id, String name, String units, String usertype) {

        Intent intent = new Intent(this, NavActivity.class);
        intent.putExtra("Id", id);
        intent.putExtra("name", name);
        intent.putExtra("units", units);
        intent.putExtra("usertype", usertype);

        startActivity(intent);
    }
}
