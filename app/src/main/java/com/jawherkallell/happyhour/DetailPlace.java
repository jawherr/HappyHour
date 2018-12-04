package com.jawherkallell.happyhour;


import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jawherkallell.happyhour.Details.DetailsFragment;
import com.jawherkallell.happyhour.Details.GalleryFragment;
import com.jawherkallell.happyhour.Details.PagerAdapter;
import com.jawherkallell.happyhour.Details.Reviews;
import com.jawherkallell.happyhour.Details.card.ReviewAdapter;
import com.jawherkallell.happyhour.Json.model.Photos;
import com.jawherkallell.happyhour.Json.model.PlaceDetail;
import com.jawherkallell.happyhour.Remote.IGoogleAPIService;
import com.jawherkallell.happyhour.card.PlaceAdapter;
import com.squareup.picasso.Picasso;

import java.net.URI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class DetailPlace extends AppCompatActivity  {

    IGoogleAPIService mService;
    Photos[] photos;
    PlaceDetail mPlace;
    private ImageView img;
    private    CollapsingToolbarLayout collapsingToolbar;
    TextView t;
    TabLayout tablayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        mService = Common.getGoogleAPIService();
        img=(ImageView) findViewById(R.id.backdrop);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        FloatingActionButton btn=(FloatingActionButton) findViewById(R.id.floating);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.google.com/local/writereview?placeid="+Common.currentResult.getPlace_id())));
            }
        });
       /* Toast.makeText(DetailPlace.this,"houssem:",Toast.LENGTH_LONG).show();
        tablayout=(TabLayout) findViewById(R.id.tablayout) ;
        tablayout.addTab(tablayout.newTab().setText("Information Principales"));
        tablayout.addTab(tablayout.newTab().setText("Info"));*/
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //creating recyclerview adapter



                mService.getDetailPlace(getDetailUrl(Common.currentResult.getPlace_id()))
                        .enqueue(new Callback<PlaceDetail>() {
                            @Override
                            public void onResponse(Call<PlaceDetail> call, Response<PlaceDetail> response) {
                                mPlace = response.body();

                                photos = mPlace.getResult().getPhotos();
                                collapsingToolbar.setTitle(mPlace.getResult().getName());
                               ReviewAdapter adapter = new ReviewAdapter(DetailPlace.this, mPlace.getResult().getReviews());
                               recyclerView.setAdapter(adapter);
                                if (photos != null) {
                                    Picasso.with(DetailPlace.this)
                                            .load(getPhotoOfPlace(Common.currentResult.getPhotos()[0].getPhoto_reference(), 1000))
                                            .placeholder(R.drawable.ic_image_black_24dp)
                                            .error(R.drawable.ic_error_black_24dp)
                                            .into(img);
                                } else {

                                    img.setImageDrawable(getResources().getDrawable(R.drawable.ic_image_black_24dp));

                                }
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
