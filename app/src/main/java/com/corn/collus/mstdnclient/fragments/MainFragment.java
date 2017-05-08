package com.corn.collus.mstdnclient.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.corn.collus.mstdnclient.R;

/**
 * Created by mitsu on 2017/05/04.
 */

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        ViewPager viewPager = (ViewPager)view.findViewById(R.id.main_view_pager);
        MainPagerAdapter adapter = new MainPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.main_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
