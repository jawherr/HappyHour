package com.jawherkallell.happyhour.Details.card;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jawherkallell.happyhour.Json.model.Reviews;
import com.jawherkallell.happyhour.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewAdapter extends   RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context mCtx;
    private Reviews[] reviews;

    public ReviewAdapter(Context mCtx, Reviews[] reviews) {
        this.mCtx = mCtx;
        this.reviews = reviews;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.review_list_layout, null);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        Reviews review = reviews[position];
        holder.nom.setText(review.getAuthor_name());
        holder.description.setText(review.getText());
        holder.rating.setNumStars(Integer.parseInt(review.getRating()));
        Date d = new Date(Long.parseLong(review.getTime())*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String formattedDate = sdf.format(d);
        holder.date.setText(formattedDate);
        Picasso.with(mCtx).load(review.getProfile_photo_url()).into(holder.avatar);

    }

    @Override
    public int getItemCount() {
        return reviews.length;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView nom,date,description;
        RatingBar rating;
        public ReviewViewHolder(View itemView) {
            super(itemView);
            avatar=(ImageView)itemView.findViewById(R.id.imageView);
            nom=(TextView) itemView.findViewById(R.id.textViewName);
            date=(TextView) itemView.findViewById(R.id.date);
            description=(TextView) itemView.findViewById(R.id.descrip);
            rating=(RatingBar) itemView.findViewById(R.id.ratingBar2);


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
