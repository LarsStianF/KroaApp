package com.example.kroasiden20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Kim V Pedersen
 */
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
        TextView secNeed = findViewById(R.id.secDetailNeed);
        TextView barNeed = findViewById(R.id.barDetailNeed);
        TextView crwNeed = findViewById(R.id.crwDetailNeed);
        TextView tchNeed = findViewById(R.id.tchDetailNeed);
        TextView secGot = findViewById(R.id.secDetailGot);
        TextView barGot = findViewById(R.id.barDetailGot);
        TextView crwGot = findViewById(R.id.crwDetailGot);
        TextView tchGot = findViewById(R.id.tchDetailGot);

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


    }

}
