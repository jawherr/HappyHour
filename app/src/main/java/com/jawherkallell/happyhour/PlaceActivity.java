package com.jawherkallell.happyhour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import  com.jawherkallell.happyhour.card.model.Place;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.jawherkallell.happyhour.Json.model.MyPlaces;
import com.jawherkallell.happyhour.Json.model.Photos;
import com.jawherkallell.happyhour.Json.model.PlaceDetail;
import com.jawherkallell.happyhour.Json.model.Results;
import com.jawherkallell.happyhour.Remote.IGoogleAPIService;
import com.jawherkallell.happyhour.card.PlaceAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceActivity extends AppCompatActivity {
    private static final int MY_PERMISSION_CODE = 1000;
    private GoogleMap mMap;
    private double latitude, longitude;
    private android.location.Location mLastLocation;
    private Marker mMarker;
    private Button btn;
    private ListView lst;
    private FusedLocationProviderClient client;
    private List<Place> listPlace =new ArrayList<Place>();
    private String placetype;
    PlaceDetail mPlace;
    //the recyclerview
    RecyclerView recyclerView;
    IGoogleAPIService mService;
    ArrayAdapter<String> myAdapter;

    MyPlaces currentPlace;
    //new Location
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationCallback locationCallback;
    private LocationRequest mLocationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        Intent myintent = getIntent();
        placetype=myintent.getStringExtra("placetype");
        latitude=Double.parseDouble(myintent.getStringExtra("latitude"));
        longitude=Double.parseDouble(myintent.getStringExtra("longitude"));

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mService = Common.getGoogleAPIService();

        nearByPlace("cafe");

    }

    private void nearByPlace(final String placeType) {

        String url = getUrl(latitude,longitude,placeType);

        mService.getNearByPlaces(url)
                .enqueue(new Callback<MyPlaces>() {
                    @Override
                    public void onResponse(Call<MyPlaces> call, Response<MyPlaces> response) {

                        currentPlace = response.body();
                        if(response.isSuccessful())
                        {
                            for(int i=0;i<response.body().getResults().length;i++)
                            {

                                Results googlePlace = response.body().getResults()[i];

                                String placeName = googlePlace.getName();
                                String PlaceRating=googlePlace.getRating();
                                Photos[] photos =googlePlace.getPhotos();
                                LatLng latLng = new LatLng(latitude,longitude);
                                Place p = new Place(placeName,PlaceRating);
                                p.setPhotos(photos);
                                listPlace.add(p);
                            }
                            //creating recyclerview adapter
                            PlaceAdapter adapter = new PlaceAdapter(PlaceActivity.this, listPlace,currentPlace);
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);


                        }
                    }

                    @Override
                    public void onFailure(Call<MyPlaces> call, Throwable t) {

                    }
                });
    }

    private String getUrl(double latitude, double longitude, String placeType) {
        StringBuffer googlePlacesUrl = new StringBuffer("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location="+latitude+","+longitude);
        googlePlacesUrl.append("&radius="+10000);
        googlePlacesUrl.append("&type="+placeType);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key="+getResources().getString(R.string.browser_service));
        Log.d("getUrl",googlePlacesUrl.toString());
        return googlePlacesUrl.toString();
    }
}
