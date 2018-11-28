package com.jawherkallell.happyhour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;

import android.text.TextUtils;

import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.RatingBar;

import android.widget.TextView;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.jawherkallell.happyhour.Json.model.Photos;
import com.jawherkallell.happyhour.Json.model.PlaceDetail;
import com.jawherkallell.happyhour.Remote.IGoogleAPIService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPlace extends AppCompatActivity {
    LinearLayout gallery;
    RatingBar ratingBar;
    TextView opening_hours,place_address,place_name;
    Button btnViewOnMap;
    IGoogleAPIService mService;
    Photos[] photos;
    ViewPager mViewPager;
    PlaceDetail mPlace;
  CustomSwipeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);

        mService = Common.getGoogleAPIService();


        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        place_address = (TextView)findViewById(R.id.place_address);
        place_name = (TextView)findViewById(R.id.place_name);
        opening_hours = (TextView)findViewById(R.id.place_open_hour);
        btnViewOnMap = (Button)findViewById(R.id.btn_show_map);

        //Empty all view
        place_name.setText("");
        place_address.setText("");
        opening_hours.setText("");
        //



        mViewPager = (ViewPager) findViewById(R.id.pager);

        btnViewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPlace.getResult().getUrl()));
                startActivity(mapIntent);
            }
        });
        //Photo
        if (Common.currentResult.getPhotos() != null && Common.currentResult.getPhotos().length >0 )
        {

        }
        //Rating
        if(Common.currentResult.getRating() != null && !TextUtils.isEmpty(Common.currentResult.getRating()))
        {
            ratingBar.setRating(Float.parseFloat(Common.currentResult.getRating()));
        }
        else
        {
            ratingBar.setVisibility(View.GONE);
        }

        //Opening hours
        if(Common.currentResult.getOpening_hours() != null)
        {
            opening_hours.setText(Common.currentResult.getOpening_hours().getOpen_now());
        }
        else
        {
            opening_hours.setVisibility(View.GONE);
        }

        //User Service to fetch Address and Name
        mService.getDetailPlace(getDetailUrl(Common.currentResult.getPlace_id()))
                .enqueue(new Callback<PlaceDetail>() {
                    @Override
                    public void onResponse(Call<PlaceDetail> call, Response<PlaceDetail> response) {
                        mPlace = response.body();
                        place_address.setText(mPlace.getResult().getFormatted_address());
                        place_name.setText(mPlace.getResult().getName());
                        photos = mPlace.getResult().getPhotos();
                        adapter= new CustomSwipeAdapter(photos,ViewPlace.this);
                        mViewPager.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<PlaceDetail> call, Throwable t) {

                    }
                });

    }

    public  String getDetailUrl(String place_id) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json");
        url.append("?placeid="+place_id);
        url.append("&key="+getResources().getString(R.string.browser_service));
        return url.toString();
    }

    public String getPhotoOfPlace(String photos_reference, int maxWidth) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo");
        url.append("?maxwidth="+maxWidth);
        url.append("&photoreference="+photos_reference);
        url.append("&key="+getResources().getString(R.string.browser_service));


        return url.toString();

    }



}
