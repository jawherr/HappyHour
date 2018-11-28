package com.jawherkallell.happyhour;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "MainActivity";
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav=findViewById(R.id.btNav);
        nav.setOnNavigationItemSelectedListener(this);
        nav.setSelectedItemId(R.id.nav_home);
        checkLocationPermission();

    }

    Home home_fragment = new Home();
    Event event_fragment = new Event();
    Profile profile_fragment = new Profile();
    Search search_fragment = new Search();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, home_fragment).commit();
                return true;

            case R.id.nav_events:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, event_fragment).commit();
                return true;

            /*case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, search_fragment).commit();
                return true;
            */
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, profile_fragment).commit();
                return true;

        }

        return false;
    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }


        }


}
