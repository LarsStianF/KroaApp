package com.example.kroasiden20;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kroasiden20.ui.volunteer.Volunteer;
import org.json.JSONObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * @author Kim V Pedersen
 */
public class RegisterActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {
    boolean exist;
    public final static String ENDPOINT = "https://itfag.usn.no/~163357/api.php";
    public Volunteer newVol = new Volunteer();


    private  static  final Pattern PASSWORD_PATTERN =
            Pattern.compile("^"           +
                            "(?=.*[0-9])" +
                            "(?=.*[a-z])" +
                            "(?=.*[A-Z])" +
                            "(?=\\S+$)"   +
                            ".{6,}"       +
                            "$");
    private  static  final Pattern PHONE_PATTERN =
            Pattern.compile("^"   +
                    "(?=.*[0-9])" +
                    "(?=\\S+$)"   +
                    ".{8}"        +
                    "$");
    private EditText textInputFname;
    private EditText textInputLname;
    private EditText textInputEmail;
    private EditText textInputPassword;
    private EditText textInputPhone;
    Boolean updateOK = false;
    String emailInput = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        textInputFname = findViewById(R.id.fname_field);
        textInputLname = findViewById(R.id.lname_field);
        textInputEmail = findViewById(R.id.email_field);
        textInputPassword = findViewById(R.id.password_field);
        textInputPhone = findViewById(R.id.phone_field);


    }




    /**
     * User registration.
     * methods for validation.
     */
    private boolean validateFirstname() {
        String firstnameInput = textInputFname.getText().toString();

        if (firstnameInput.isEmpty()) {
            textInputFname.setError("Field can't  be empty");
            return false;
        } else {
            textInputFname.setError(null);
            return true;
        }
    }

    private boolean validateLastname() {
        String lastnameInput = textInputLname.getText().toString();

        if (lastnameInput.isEmpty()) {
            textInputLname.setError("Field can't  be empty");
            return false;
        } else {
            textInputLname.setError(null);
            return true;
        }
    }



    boolean validateEmail() {

        emailInput = textInputEmail.getText().toString();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Enter a valid email address");
            return false;
        } else
        {
            textInputEmail.setError(null);
            return true;
        }
    }


    private boolean validatePassword() {
        String passwordInput = textInputPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't  be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("6 char long, one numer. One UPPER and one lowercase.");
            return false;
        }else {
            textInputPassword.setError(null);
            return true;
        }

    }

    private boolean validatePhone() {
        String phoneInput = textInputPhone.getText().toString().trim();

        if (phoneInput.isEmpty()) {
            textInputPhone.setError("Field can't be empty");
            return false;
        } else if (!PHONE_PATTERN.matcher(phoneInput).matches()) {
            textInputPhone.setError("a mobile number should be 8 digits");
            return false;
        } else {
            textInputPhone.setError(null);
            return true;
        }

    }

    public void confirmInput(View v) {

        if (!validateEmail() | !validateFirstname() | !validateLastname() | !validatePhone() | !validatePassword()) {
            return;
        } else {
            // create user.

            create_Volunteer();
        }
    }

    private void create_Volunteer() {

        String password = textInputPassword.getText().toString().trim();
        String hashPassword = passwordHash(password);
        newVol.setfName(textInputFname.getText().toString());
        newVol.setlName(textInputLname.getText().toString());
        newVol.setEmail(textInputEmail.getText().toString());
        newVol.setPassword(hashPassword);
        newVol.setPhone(textInputPhone.getText().toString());
        newVol.setRole("6");




        String volunteerCreateURL = ENDPOINT + "/volunteer/";
        JSONObject jsonVolunteer = newVol.toJSONObject();
        if(isOnline()) {
            System.out.println("STATUS: OPERATIONAL");
            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, volunteerCreateURL, jsonVolunteer, new Response.Listener<JSONObject>() {
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
            Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show();
            Intent result = new Intent();
            setResult(Activity.RESULT_OK, result);
            finish();

        } else {
            Toast.makeText(this, "ERROR: NO CONNECTION. STATUS: INOPERATIVE", Toast.LENGTH_SHORT).show();
        }

    }

    public static String passwordHash(String password) {
        /**
         * Bruker simpel MD5 kryptering (ikke sikker)
         * Matches poor database.
         */

        String hashedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            hashedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println(hashedPassword);

        return hashedPassword;
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) this.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        if(response.contains(emailInput))  {
            exist = true;
                    textInputEmail.setError("Email already used");
            System.out.println(exist);
        } else {
            exist = false;
        }




    }
}
