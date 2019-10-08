/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.kroasiden20.ui.volunteer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kroasiden20.R;

import java.util.ArrayList;


class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<Volunteer> vVolunteerData;
    private Context vContext;
    private FragmentActivity parent;
    private LayoutInflater minInflater;



    VolunteerAdapter(FragmentActivity parent, ArrayList<Volunteer> volListen) {
        this.parent = parent;
        vContext = parent.getApplicationContext();
        minInflater = LayoutInflater.from(vContext);
        this.vVolunteerData = volListen;
    }



    @Override
    public VolunteerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vVolView = minInflater.inflate(R.layout.volunteer_item, parent, false);

        return new ViewHolder(vVolView, this);
    }

    @Override
    public int getItemCount() {
        return vVolunteerData.size();
    }


    @Override
    public void onBindViewHolder(VolunteerAdapter.ViewHolder holder, int position) {
        // Get current sport.
        Volunteer currentVol = vVolunteerData.get(position);
        ViewHolder vh = holder;

        // Alternates the color of the Volunteer cards
        if (position %2 == 1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#80CECECE"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#80FFFFFF"));
        }

        // Populate the textviews with data.
        String name = currentVol.getfName() + " " + currentVol.getlName();
        vh.vName.setText(name);
        // Because Joins does not work in the CRUD API, this is a quickfix
        if (currentVol.getRole().equals("1")){
            vh.vRole.setText("Root");

        } else if (currentVol.getRole().equals("2") ) {
            vh.vRole.setText("Daglig Leder");


        } else if (currentVol.getRole().equals("3")) {
            vh.vRole.setText("Volunteer Coordinatorr");


        } else if (currentVol.getRole().equals("4")) {
            vh.vRole.setText("Event Manager");


        } else if (currentVol.getRole().equals("5")) {
            vh.vRole.setText("Manager");


        } else if (currentVol.getRole().equals("6")) {
            vh.vRole.setText("Volunteer");


        } else{
            vh.vRole.setText("This no Work");

        }
        vh.vEmail.setText("Email: " + currentVol.getEmail());
        vh.vPhone.setText("Phone: " + currentVol.getPhone());
        vh.vLastVol.setText("Last Volunteered: " + currentVol.getLastVol());
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView vName;
        private TextView vRole;
        private TextView vEmail;
        private TextView vPhone;
        private TextView vLastVol;
        private ImageView vCrewImg;
        private ImageView vBarImg;
        private ImageView vSecImg;
        private ImageView vTechImg;
        final VolunteerAdapter mittAdapter;


        ViewHolder(View itemView, VolunteerAdapter adapter) {
            super(itemView);

            // Initialize the views.
            vName = itemView.findViewById(R.id.volunteerName);
            vRole = itemView.findViewById(R.id.userRole);
            vEmail = itemView.findViewById(R.id.volunteerEmail);
            vPhone = itemView.findViewById(R.id.volunteerPhone);
            vLastVol = itemView.findViewById(R.id.lastVol);
            vCrewImg = itemView.findViewById(R.id.crewImg);
            vBarImg = itemView.findViewById(R.id.barImg);
            vSecImg = itemView.findViewById(R.id.secImg);
            vTechImg = itemView.findViewById(R.id.techImg);
            this.mittAdapter = adapter;
            itemView.setOnClickListener(this);

        }



        @Override
        public void onClick(View view) {

            Volunteer curVol = vVolunteerData.get(getAdapterPosition());
            String name = curVol.getfName() + " " + curVol.getlName();
            Intent detailIntent = new Intent(parent, VolunteerActivity.class);
            detailIntent.putExtra("ID", curVol.getID());
            detailIntent.putExtra("Name", name);
            detailIntent.putExtra("Role", curVol.getRole());
            detailIntent.putExtra("Email", curVol.getEmail());
            detailIntent.putExtra("Phone", curVol.getPhone());
            detailIntent.putExtra("LastVol", curVol.getLastVol());

            detailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            vContext.startActivity(detailIntent);
        } // End of onClick()
    }
}
