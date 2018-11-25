package com.jawherkallell.happyhour;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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


}
