package com.corn.collus.mstdnclient.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mitsu on 2017/05/05.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new HomeTimeLineFragment();
            case 1:
                return new PublicTimeLineFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return HomeTimeLineFragment.getTitle();
            case 1:
                return PublicTimeLineFragment.getTitle();
            default:
                return null;
        }
    }
}
