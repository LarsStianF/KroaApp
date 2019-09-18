package com.example.kroasiden20.ui.volunteer;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kroasiden20.R;


import java.util.ArrayList;

public class VolunteerFragment extends Fragment {

    private RecyclerView vRecyclerView;
    private ArrayList<Volunteer> vVolunteerData;
    private VolunteerAdapter vAdapter;

    private VolunteerViewModel volunteerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        volunteerViewModel =
                ViewModelProviders.of(this).get(VolunteerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_volunteers, container, false);


        // Initialize the RecyclerView.
        vRecyclerView = root.findViewById(R.id.recyclerView);
        // Set the Layout Manager.
        vRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        // Initialize the ArrayList that will contain the data.
        vVolunteerData = new ArrayList<>();
        // Initialize the adapter and set it to the RecyclerView.
        vAdapter = new VolunteerAdapter(this.getActivity(), vVolunteerData);
        vRecyclerView.setAdapter(vAdapter);
        vRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        // Get the data.
        initializeData();

        return root;
    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] volNameList = getResources()
                .getStringArray(R.array.volunteer_names);
        String[] volRoleList = getResources()
                .getStringArray(R.array.volunteer_roles);
        String[] volEmailList = getResources()
                .getStringArray(R.array.volunteer_emails);
        String[] volPhoneList = getResources()
                .getStringArray(R.array.volunteer_phones);
        String[] volLastVolList = getResources()
                .getStringArray(R.array.volunteer_lastvolunteered);



        // Clear the existing data (to avoid duplication).
        vVolunteerData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<volNameList.length;i++){
            vVolunteerData.add(new Volunteer(volNameList[i],volRoleList[i], "Email: " + volEmailList[i],"Tlf: " + volPhoneList[i], "Last Volunteered: " + volLastVolList[i]
                    ));
        }


        // Notify the adapter of the change.
        vAdapter.notifyDataSetChanged();

    }
}