package com.jawherkallell.happyhour;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private FusedLocationProviderClient client;
    ImageButton btRes, btCafe, btBar;
    double latitude,longitude;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
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
               intent.putExtra("latitude", lat);
               intent.putExtra("longitude",longt);
               view.getContext().startActivity(intent);
            }
        });
        return view;
    }


}
