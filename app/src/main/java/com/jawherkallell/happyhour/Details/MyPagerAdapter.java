package com.jawherkallell.happyhour.Details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jawherkallell.happyhour.Details.DetailsFragment;
import com.jawherkallell.happyhour.Details.GalleryFragment;
import com.jawherkallell.happyhour.Details.Reviews;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    public MyPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override    public Fragment getItem(int position) {
        switch (position){
            case 0: return new DetailsFragment();
            case 1: return new GalleryFragment();
            case 2: return  new Reviews();

        }
        return null;
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Override    public CharSequence getPageTitle(int position) {        switch (position){
        case 0: return "Info";
        case 1: return "Gallery";
        case 2: return  "Avis" ;
        default: return null;
    }
    }
}
