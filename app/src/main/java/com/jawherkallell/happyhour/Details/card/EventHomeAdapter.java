package com.jawherkallell.happyhour.Details.card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jawherkallell.happyhour.EventDetails;
import com.jawherkallell.happyhour.Json.model.Event;
import com.jawherkallell.happyhour.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class EventHomeAdapter extends RecyclerView.Adapter<EventHomeAdapter.EventHomeHolder>
{
    private Context mCtx;
    private List<Event> events;

    public EventHomeAdapter(Context mCtx, List<Event> events) {
        this.mCtx = mCtx;
        this.events = events;
    }

    @Override
    public EventHomeAdapter.EventHomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.event_home, null);
        return new EventHomeAdapter.EventHomeHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHomeHolder holder, int position) {
        final Event event = events.get(position);
        final int resID = mCtx.getResources().getIdentifier(event.getImg(),
                "drawable", mCtx.getPackageName());
        holder.img.setImageDrawable(mCtx.getResources().getDrawable(resID));
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx,EventDetails.class);
                intent.putExtra("Img",""+resID);
                intent.putExtra("add",event.getAdresse());
                intent.putExtra("date",event.getDate());
                intent.putExtra("name",event.getDesignation());
                intent.putExtra("descrip",event.getDescription());
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class EventHomeHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public EventHomeHolder(View itemView) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.imageView2);

        }
    }
}
