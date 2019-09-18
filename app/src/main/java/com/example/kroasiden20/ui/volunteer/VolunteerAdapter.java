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
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.kroasiden20.R;
import java.util.ArrayList;


class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<Volunteer> vVolunteerData;
    private Context vContext;




    VolunteerAdapter(Context context, ArrayList<Volunteer> eventData) {
        this.vVolunteerData = eventData;
        this.vContext = context;
    }



    @Override
    public VolunteerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(vContext).
                inflate(R.layout.volunteer_item, parent, false));
    }


    @Override
    public void onBindViewHolder(VolunteerAdapter.ViewHolder holder, int position) {
        // Get current sport.
        Volunteer currentVol = vVolunteerData.get(position);

        if (position %2 == 1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#80CECECE"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#80FFFFFF"));
        }

        // Populate the textviews with data.
        holder.bindTo(currentVol);
    }


    @Override
    public int getItemCount() {
        return vVolunteerData.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

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


        ViewHolder(View itemView) {
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

        }

        void bindTo(Volunteer currentVol){
            // Populate the textviews with data.
            vName.setText(currentVol.getName());
            vRole.setText(currentVol.getRole());
            vEmail.setText(currentVol.getEmail());
            vPhone.setText(currentVol.getPhone());
            vLastVol.setText(currentVol.getLastVol());
        }
    }
}
