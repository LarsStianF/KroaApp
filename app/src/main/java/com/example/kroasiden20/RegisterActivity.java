package com.example.kroasiden20;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private  static  final Pattern PASSWORD_PATTERN = Pattern.compile("^" + "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=\\S+$)" + " .{6,}" + "$");
    private EditText textInputFname;
    private EditText textInputLname;
    private EditText textInputEmail;
    private EditText textInputPassword;
    private EditText textInputPhone;


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

    private boolean validateFirstname() {
        String firstnameInput = textInputFname.getText().toString().trim();

        if (firstnameInput.isEmpty()) {
            textInputFname.setError("Field can't  be empty");
            return false;
        } else {
            textInputFname.setError(null);
            return true;
        }
    }

    private boolean validateLastname() {
        String lastnameInput = textInputLname.getText().toString().trim();

        if (lastnameInput.isEmpty()) {
            textInputLname.setError("Field can't  be empty");
            return false;
        } else {
            textInputLname.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't  be empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Enter a valid email address");

        } else {
            textInputEmail.setError(null);
            return true;
        }
        return false;
    }


    private boolean validatePassword() {
        String passwordInput = textInputPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't  be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("6 char long, one numer. One UPPER and one lowercase.");
        }else {
            textInputPassword.setError(null);
            return true;
        }
        return false;
    }

    private boolean validatePhone() {
        String phoneInput = textInputPhone.getText().toString().trim();

        if (phoneInput.isEmpty()) {
            textInputPhone.setError("Field can't  be empty");
            return false;
        } else {
            textInputPhone.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {

        if (!validateEmail() | !validateFirstname() | !validateLastname() | !validatePhone() | !validatePassword()) {
            return;
        }

        String input = "Email: " + textInputFname.getText().toString();
        Toast.makeText(this, input, Toast.LENGTH_LONG).show();
    }
}
