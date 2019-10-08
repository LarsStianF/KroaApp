package com.example.kroasiden20.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kroasiden20.R;

import java.util.ArrayList;

/**
 * @author Lars Stian
 */

class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder>  {
    private ArrayList<Stats> profile_Stats;
    private LayoutInflater statsInflate;
    private Context ctx;
    private ProfileFragment parent;

    public ProfileAdapter(ProfileFragment parent, ArrayList<Stats> profile_Stats) {
        this.parent = parent;
        ctx = parent.getActivity().getApplicationContext();
        statsInflate = LayoutInflater.from(ctx);
        this.profile_Stats = profile_Stats;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sItemView = statsInflate.inflate(R.layout.activity_profile, parent, false);
        return new ViewHolder(sItemView, this);
    }


    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        Stats stats = profile_Stats.get(position);
        ViewHolder vh = holder;
        vh.unitStatsView.setText(stats.unit);
        vh.welcomeNameView.setText(stats.name);
        vh.monthStatsView.setText(stats.worked);
        vh.totalStatsView.setText(stats.total);
        vh.jippiStatsView.setText(stats.jippi);
    }

    @Override
    public int getItemCount() { return profile_Stats.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView unitStatsView, welcomeNameView, monthStatsView, totalStatsView, jippiStatsView;
        final ProfileAdapter myAdapter;

        public ViewHolder(@NonNull View itemView, ProfileAdapter adapter) {
            super(itemView);
            unitStatsView = (TextView) itemView.findViewById(R.id.unitStats);
            welcomeNameView = (TextView) itemView.findViewById(R.id.welcomeName);
            monthStatsView = (TextView) itemView.findViewById(R.id.monthStats);
            totalStatsView = (TextView) itemView.findViewById(R.id.totalStats);
            jippiStatsView = (TextView) itemView.findViewById(R.id.jippiStats);
            this.myAdapter = adapter;
        }
    }
}
