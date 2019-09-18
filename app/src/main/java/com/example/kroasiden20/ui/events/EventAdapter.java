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

package com.example.kroasiden20.ui.events;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.kroasiden20.R;
import java.util.ArrayList;


class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<Event> eEventsData;
    private Context eContext;




    EventAdapter(Context context, ArrayList<Event> eventData) {
        this.eEventsData = eventData;
        this.eContext = context;
    }



    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(eContext).
                inflate(R.layout.list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        // Get current sport.
        Event currentEvent = eEventsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentEvent);
    }


    @Override
    public int getItemCount() {
        return eEventsData.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView eTitleText;
        private TextView eInfoText;
        private ImageView eEventImage;


        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            eTitleText = itemView.findViewById(R.id.title);
            eInfoText = itemView.findViewById(R.id.subTitle);
            eEventImage = itemView.findViewById(R.id.eventImage);

        }

        void bindTo(Event currentEvent){
            // Populate the textviews with data.
            eTitleText.setText(currentEvent.getTitle());
            eInfoText.setText(currentEvent.getInfo());
            Glide.with(eContext).load(currentEvent.getImageResource()).into(eEventImage);
        }
    }
}
