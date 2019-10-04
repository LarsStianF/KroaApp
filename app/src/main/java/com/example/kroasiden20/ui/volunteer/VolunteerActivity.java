package com.example.kroasiden20.ui.volunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.kroasiden20.R;

public class VolunteerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);


        TextView volName = findViewById(R.id.volunteerName);
        TextView volRole = findViewById(R.id.userRole);
        TextView volEmail = findViewById(R.id.volunteerEmail);
        TextView volPhone = findViewById(R.id.volunteerPhone);
        TextView volLastVol = findViewById(R.id.lastVol);

        volName.setText(getIntent().getStringExtra("Name"));
        volRole.setText(getIntent().getStringExtra("Role"));
        volEmail.setText(getIntent().getStringExtra("Email"));
        volPhone.setText(getIntent().getStringExtra("Phone"));
        volLastVol.setText(getIntent().getStringExtra("LastVol"));
    }
}
