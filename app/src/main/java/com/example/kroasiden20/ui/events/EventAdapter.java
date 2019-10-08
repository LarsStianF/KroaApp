package com.example.kroasiden20.ui.events;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kroasiden20.EventActivity;
import com.example.kroasiden20.R;
import java.util.ArrayList;

/**
 * @author Kim V Pedersen
 */
class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>  {

    private ArrayList<Event> eventListen;
    private LayoutInflater minInflater;
    private Context ctx;
    private FragmentActivity parent;

    EventAdapter(FragmentActivity parent, ArrayList<Event> eventListen) {
        this.parent = parent;
        ctx = parent.getApplicationContext();
        minInflater = LayoutInflater.from(ctx);
        this.eventListen = eventListen;
    }



    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mEventView = minInflater.inflate(R.layout.list_item, parent, false);

        return new EventViewHolder(mEventView, this);
    }


    @Override
    public int getItemCount() {
        return eventListen.size();
    }


    @Override
    public void onBindViewHolder(EventAdapter.EventViewHolder holder, int position) {
       Event event = eventListen.get(position);
       EventViewHolder vh = holder;
       String endTime = " - " + event.evtEnd;
       String secNeed = "/" + event.evtSec;
       String barNeed = "/" + event.evtBar;
       String crwNeed = "/" + event.evtCrw;
       String tchNeed = "/" + event.evtTch;
       vh.evtIdView.setText(event.evtId);
       vh.evtNameView.setText(event.evtName);
       vh.evtDateView.setText(event.evtDate);
       vh.evtStartView.setText(event.evtStart);
       vh.evtEndView.setText(endTime);
       vh.evtTextView.setText(event.evtTxt);
       vh.evtSecView.setText(secNeed);
       vh.evtBarView.setText(barNeed);
       vh.evtCrwView.setText(crwNeed);
       vh.evtTchView.setText(tchNeed);
       vh.evtSecGotView.setText(event.evtSecGot);
       vh.evtBarGotView.setText(event.evtBarGot);
       vh.evtcrwGotView.setText(event.evtCrwGot);
       vh.evtTchGotView.setText(event.evtTchGot);


    }



    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView evtIdView, evtNameView, evtDateView, evtStartView, evtEndView, evtTextView, evtSecView, evtBarView, evtCrwView, evtTchView, evtSecGotView, evtBarGotView, evtcrwGotView, evtTchGotView;
        final EventAdapter mittAdapter;



        public EventViewHolder(View itemView, EventAdapter adapter) {
            super(itemView);
            evtIdView        = (TextView) itemView.findViewById(R.id.evt_id);
            evtNameView      = (TextView) itemView.findViewById(R.id.evt_name);
            evtDateView      = (TextView) itemView.findViewById(R.id.evt_date);
            evtStartView     = (TextView) itemView.findViewById(R.id.evt_start);
            evtEndView       = (TextView) itemView.findViewById(R.id.evt_end);
            evtTextView      = (TextView) itemView.findViewById(R.id.evt_text);
            evtSecView       = (TextView) itemView.findViewById(R.id.evt_sec_need);
            evtBarView       = (TextView) itemView.findViewById(R.id.evt_bar_need);
            evtCrwView       = (TextView) itemView.findViewById(R.id.evt_crw_need);
            evtTchView       = (TextView) itemView.findViewById(R.id.evt_tch_need);
            evtSecGotView    = (TextView) itemView.findViewById(R.id.evt_sec_got);
            evtBarGotView    = (TextView) itemView.findViewById(R.id.evt_bar_got);
            evtcrwGotView    = (TextView) itemView.findViewById(R.id.evt_crw_got);
            evtTchGotView    = (TextView) itemView.findViewById(R.id.evt_tch_got);
            this.mittAdapter = adapter;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {

            Event currentEvent = eventListen.get(getAdapterPosition());
            Intent detailIntent = new Intent(parent, EventActivity.class);
            detailIntent.putExtra("ID", currentEvent.getEvtId());
            detailIntent.putExtra("Name", currentEvent.getEvtName());
            detailIntent.putExtra("Date", currentEvent.getEvtDate());
            detailIntent.putExtra("Start", currentEvent.getEvtStart());
            detailIntent.putExtra("End", currentEvent.getEvtEnd());
            detailIntent.putExtra("Txt", currentEvent.getEvtTxt());
            detailIntent.putExtra("secNeed", currentEvent.getEvtSec());
            detailIntent.putExtra("barNeed", currentEvent.getEvtBar());
            detailIntent.putExtra("crwNeed", currentEvent.getEvtCrw());
            detailIntent.putExtra("tchNeed", currentEvent.getEvtTch());
            detailIntent.putExtra("secGot", currentEvent.getEvtSecGot());
            detailIntent.putExtra("barGot", currentEvent.getEvtBarGot());
            detailIntent.putExtra("crwGot", currentEvent.getEvtCrwGot());
            detailIntent.putExtra("tchGot", currentEvent.getEvtTchGot());
            detailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(detailIntent);
        } // End of onClick()

    }
}
