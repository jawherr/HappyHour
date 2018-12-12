package com.jawherkallell.happyhour;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,Profile.OnLoginFormActivityListener,WelcomeFragment.OnLogoutListener{
    private static final String TAG = "MainActivity";
    BottomNavigationView nav;
    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nav=findViewById(R.id.btNav);
        nav.setOnNavigationItemSelectedListener(this);
        nav.setSelectedItemId(R.id.nav_home);
        checkLocationPermission();

        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        Intent intent2 = getIntent();
        if(intent2.getStringExtra("target2")!=null) {
            String target2 = intent2.getStringExtra("target2");
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.container, new ProfileFragment()).commit();

        }
        Intent intent = getIntent();
        if(intent.getStringExtra("target")!=null) {
            String target = intent.getStringExtra("target");
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.container, new Profile()).commit();

        }
        nav=findViewById(R.id.btNav);
        nav.setOnNavigationItemSelectedListener(this);
        nav.setSelectedItemId(R.id.nav_home);
        checkLocationPermission();

    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }


    Home home_fragment = new Home();
    EventFragment event_fragment = new EventFragment();
    Profile profile_fragment = new Profile();
    WelcomeFragment welcomeFragment = new WelcomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Profile profile=new Profile();
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, home_fragment).commit();
                return true;

            case R.id.nav_events:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, event_fragment).commit();
                return true;

            case R.id.nav_profile:
                if (session!=null && session.getusername().equals("houssem"))
                {
                      getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, welcomeFragment).commit();
                }
                else if(profile.googleApiClient!=null){
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.container, profileFragment).commit();
                }
                else{
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.container, profile_fragment).commit();
                }

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
    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                new RegistrationFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String name)
    {
        prefConfig.writeName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new WelcomeFragment()).commit();
    }

    @Override
    public void logoutPerformed() {
        prefConfig.writeLoginStatus(false);
        prefConfig.writeName("User");
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Profile()).commit();
    }

}
