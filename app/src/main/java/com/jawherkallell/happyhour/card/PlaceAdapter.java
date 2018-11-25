package com.jawherkallell.happyhour.card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import com.jawherkallell.happyhour.Common;
import com.jawherkallell.happyhour.Json.model.MyPlaces;
import com.jawherkallell.happyhour.Json.model.PlaceDetail;
import com.jawherkallell.happyhour.R;
import com.jawherkallell.happyhour.Remote.IGoogleAPIService;
import com.jawherkallell.happyhour.ViewPlace;
import com.jawherkallell.happyhour.card.model.Place;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PlaceAdapter extends   RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>
{
    //this context we will use to inflate the layout
    private Context mCtx;
    IGoogleAPIService mService;
    PlaceDetail mPlace;
    MyPlaces currentPlace;


    //we are storing all the places in a list
    private List<Place> placesList;


    public PlaceAdapter(Context mCtx, final List<Place> placesList,MyPlaces currentPlace) {
        this.mCtx = mCtx;
        this.placesList = placesList;
        mService = Common.getGoogleAPIService();
        this.currentPlace=currentPlace;


    }

    @Override

    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, final int position) {
        //getting the product of the specified position
        Place place = placesList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(place.getNom());
        holder.textViewRating.setText(String.valueOf(place.getRating()));

        if(placesList.get(position).getPhotos()!=null) {
            Picasso.with(mCtx)
                    .load(getPhotoOfPlace(placesList.get(position).getPhotos()[0].getPhoto_reference(), 1000))
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder.imageView);
        }
        else
        {
            holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.ic_image_black_24dp));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.currentResult = currentPlace.getResults()[position];
                mCtx.startActivity(new Intent(mCtx,ViewPlace.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }


    class PlaceViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewRating;
        ImageView imageView;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
    public String getPhotoOfPlace(String photos_reference, int maxWidth) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo");
        url.append("?maxwidth="+maxWidth);
        url.append("&photoreference="+photos_reference);
        url.append("&key="+mCtx.getResources().getString(R.string.browser_service));


        return url.toString();

    }

}