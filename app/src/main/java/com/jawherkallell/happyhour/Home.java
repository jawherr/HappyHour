package com.jawherkallell.happyhour;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.jawherkallell.happyhour.Details.card.EventAdapter;
import com.jawherkallell.happyhour.Details.card.EventHomeAdapter;
import com.jawherkallell.happyhour.Json.model.Event;
import com.jawherkallell.happyhour.Json.model.MyPlaces;
import com.jawherkallell.happyhour.Json.model.Photos;
import com.jawherkallell.happyhour.Json.model.Results;
import com.jawherkallell.happyhour.Remote.IGoogleAPIService;
import com.jawherkallell.happyhour.Remote.IMyAPI;
import com.jawherkallell.happyhour.card.PlaceAdapter;
import com.jawherkallell.happyhour.card.model.Place;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private FusedLocationProviderClient client;
    private SearchView search;
    ImageButton btRes, btCafe, btBar,btn;
    double latitude,longitude;
    MyPlaces currentPlace;
    IMyAPI mService;
    IGoogleAPIService mServices;
    private List<Place> listPlace =new ArrayList<Place>();
    Event mEvent;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mService=IMyAPI.retrofit.create(IMyAPI.class);
        mServices = Common.getGoogleAPIService();
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView3);

        //LinearLayout manager=(LinearLayout) view.findViewById(R.id.manager) ;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView2=(RecyclerView) view.findViewById(R.id.recyclerView5);
        //LinearLayout manager=(LinearLayout) view.findViewById(R.id.manager) ;
        recyclerView2.setHasFixedSize(true);

        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        btRes = (ImageButton) view.findViewById(R.id.btnRestaurent);
        btRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){

                    return;
                }
                client.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {


                        if (location != null) {

                            String txt = "" + location.getLatitude() + "," + location.getAltitude();

                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            //nearByPlace("restaurant");


                        }
                    }
                });
                Intent intent=new Intent(view.getContext(), PlaceActivity.class);
                String lat=""+latitude;
                String longt=""+longitude;
                intent.putExtra("placetype","restaurant");
                intent.putExtra("latitude", lat);
                intent.putExtra("placeName","");
                intent.putExtra("longitude",longt);
                view.getContext().startActivity(intent);
            }
        });
        btCafe = (ImageButton) view.findViewById(R.id.btnCafe);
        btCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){

                    return;
                }
                client.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {


                        if (location != null) {

                            String txt = "" + location.getLatitude() + "," + location.getAltitude();

                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            //nearByPlace("restaurant");


                        }
                    }
                });
                Intent intent=new Intent(view.getContext(), PlaceActivity.class);
                String lat=""+latitude;
                String longt=""+longitude;
                intent.putExtra("placetype","cafe");
                intent.putExtra("latitude", lat);
                intent.putExtra("placeName","");
                intent.putExtra("longitude",longt);
                view.getContext().startActivity(intent);
            }
        });
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        btBar = (ImageButton) view.findViewById(R.id.btnBar);
        btBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){

                    return;
                }
                client.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {


                        if (location != null) {

                            String txt = "" + location.getLatitude() + "," + location.getAltitude();

                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            //nearByPlace("restaurant");


                        }
                    }
                });
                Intent intent=new Intent(view.getContext(), PlaceActivity.class);
                String lat=""+latitude;
                String longt=""+longitude;
                intent.putExtra("placetype","bar");
                intent.putExtra("placeName","");
                intent.putExtra("latitude", lat);
                intent.putExtra("longitude",longt);
                view.getContext().startActivity(intent);
            }
        });
        btn=(ImageButton) view.findViewById(R.id.ancre);
        search=(SearchView)view.findViewById(R.id.searchView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){

                    return;
                }
                client.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {


                        if (location != null) {

                            String txt = "" + location.getLatitude() + "," + location.getAltitude();

                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            //nearByPlace("restaurant");


                        }
                    }
                });
                Intent intent=new Intent(view.getContext(), PlaceActivity.class);
                String lat=""+latitude;
                String longt=""+longitude;
                intent.putExtra("placetype","");
                intent.putExtra("placeName",search.getQuery().toString());
                intent.putExtra("latitude", lat);
                intent.putExtra("longitude",longt);
                view.getContext().startActivity(intent);
            }
        });
        Call<List<Event>> call =  mService.getEvents("/mysuperapi/list.php");

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

                // Toast.makeText(getContext(),""+response.body().size(),Toast.LENGTH_LONG).show();                    //creating recyclerview adapter
                EventHomeAdapter adapter = new EventHomeAdapter(getActivity(), response.body());
                //setting adapter to recyclerview
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(getActivity(),"fail",Toast.LENGTH_LONG).show();
            }
        });
        nearByPlace();
        return view;
    }

    private void nearByPlace() {

        String url = getUrlPlace();

        mServices.getNearByPlaces(url)
                .enqueue(new Callback<MyPlaces>() {
                    @Override
                    public void onResponse(Call<MyPlaces> call, Response<MyPlaces> response) {


                        if(response.isSuccessful())
                        {
                            currentPlace = response.body();
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
                            PlaceAdapter adapter = new PlaceAdapter(getActivity(), listPlace,currentPlace);
                            //setting adapter to recyclerview
                            recyclerView2.setAdapter(adapter);


                        }
                    }

                    @Override
                    public void onFailure(Call<MyPlaces> call, Throwable t) {

                    }
                });
    }



    private String getUrlPlace() {
        StringBuffer googlePlacesUrl = new StringBuffer("https://maps.googleapis.com/maps/api/place/textsearch/json?");
        googlePlacesUrl.append("query=Best food Tunisia");
        googlePlacesUrl.append("&key="+getResources().getString(R.string.browser_service));
        Log.d("getUrl",googlePlacesUrl.toString());
        return googlePlacesUrl.toString();
    }

}
