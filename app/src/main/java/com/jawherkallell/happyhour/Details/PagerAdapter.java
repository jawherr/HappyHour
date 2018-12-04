package com.jawherkallell.happyhour.Details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    int nbtabs;
    int p=0;
    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        nbtabs=tabCount;
    }

    @Override
    public Fragment getItem(int p) {
        switch (0) {
            case 0: return DetailsFragment.newInstance("1","1");
            case 1: GalleryFragment galleryFragment= new GalleryFragment();
            return  galleryFragment;
            case 2: Reviews reviews= new Reviews();
            return reviews;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nbtabs;
    }
}
