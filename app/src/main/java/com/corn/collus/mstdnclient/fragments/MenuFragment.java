package com.corn.collus.mstdnclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.corn.collus.mstdnclient.R;

/**
 * Created by mitsu on 2017/05/04.
 */

public class MenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_menu,container,false);

        String[] names = {"foo","bar"};
        ListView listView = (ListView)view.findViewById(R.id.menu_list_view);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,names));

        return view;
    }
}
