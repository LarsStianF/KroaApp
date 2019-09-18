package com.example.kroasiden20.ui.volunteer;

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

import com.example.kroasiden20.R;

public class VolunteerFragment extends Fragment {

    private VolunteerViewModel volunteerViewModel;
    

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        volunteerViewModel =
                ViewModelProviders.of(this).get(VolunteerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_volunteers, container, false);

        return root;
    }
}