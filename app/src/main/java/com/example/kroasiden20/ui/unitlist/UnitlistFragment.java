package com.example.kroasiden20.ui.unitlist;

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

public class UnitlistFragment extends Fragment {

    private UnitlistViewModel unitlistViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        unitlistViewModel =
                ViewModelProviders.of(this).get(UnitlistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_unitlist, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        unitlistViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}