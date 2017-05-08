package com.corn.collus.mstdnclient.fragments;

import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.corn.collus.mstdnclient.R;

/**
 * Created by mitsu on 2017/05/05.
 */

public class HomeTimeLineFragment extends Fragment{

    private SwipeRefreshLayout swipe;
    private ListView listView;
    private TootAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.pull_refresh,container,false);
        adapter = new TootAdapter(getActivity());
        listView = (ListView)view.findViewById(R.id.pull_list);
        listView.setAdapter(adapter);
        adapter.refresh().subscribe();

        swipe = (SwipeRefreshLayout)view.findViewById(R.id.pull_swipe);
        swipe.setOnRefreshListener(()->{
            adapter.refresh()
                    .subscribe(()->swipe.setRefreshing(false));
        });

        return view;
    }

    public static String getTitle(){
        return "Home";
    }
}
