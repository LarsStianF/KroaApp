package com.example.kroasiden20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);




        TextView evtName = findViewById(R.id.nameDetail);
        TextView evtDate = findViewById(R.id.dateDetail);
        TextView evtStart = findViewById(R.id.startDetail);
        TextView evtEnd = findViewById(R.id.endDetail);
        TextView evtText = findViewById(R.id.textDetail);

        evtName.setText(getIntent().getStringExtra("Name"));
        evtDate.setText(getIntent().getStringExtra("Date"));
        evtStart.setText(getIntent().getStringExtra("Start"));
        evtEnd.setText(getIntent().getStringExtra("End"));
        evtText.setText(getIntent().getStringExtra("Txt"));
    }

}
