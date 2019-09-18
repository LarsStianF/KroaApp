package com.example.kroasiden20.ui.events;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kroasiden20.R;

import java.util.ArrayList;

public class EventsFragment extends Fragment {

    private RecyclerView eRecyclerView;
    private ArrayList<Event> eSportsData;
    private EventAdapter eAdapter;

    private EventsViewModel eventsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventsViewModel =
                ViewModelProviders.of(this).get(EventsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_events, container, false);


        // Initialize the RecyclerView.
        eRecyclerView = root.findViewById(R.id.recyclerView);
        // Set the Layout Manager.
        eRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        // Initialize the ArrayList that will contain the data.
        eSportsData = new ArrayList<>();
        // Initialize the adapter and set it to the RecyclerView.
        eAdapter = new EventAdapter(this.getActivity(), eSportsData);
        eRecyclerView.setAdapter(eAdapter);

        // Get the data.
        initializeData();

        return root;
    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] eventList = getResources()
                .getStringArray(R.array.event_titles);
        String[] eventInfo = getResources()
                .getStringArray(R.array.event_info);
        TypedArray eventImageResources =
                getResources().obtainTypedArray(R.array.events_images);

        // Clear the existing data (to avoid duplication).
        eSportsData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<eventList.length;i++){
            eSportsData.add(new Event(eventList[i],eventInfo[i],
                    eventImageResources.getResourceId(0,0)));
        }


        // Notify the adapter of the change.
        eAdapter.notifyDataSetChanged();

    }
}