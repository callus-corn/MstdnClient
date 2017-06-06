package com.corn.collus.mstdnclient.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.corn.collus.mstdnclient.R;
import com.corn.collus.mstdnclient.fragments.MenuFragment;
import com.corn.collus.mstdnclient.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MainFragment main = new MainFragment();
        MenuFragment listView = new MenuFragment();

        transaction.add(R.id.main_liner,main);
        transaction.add(R.id.main_drawer,listView);
        transaction.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        Button button = (Button)findViewById(R.id.main_toot);
        button.setOnClickListener(l -> {
            Intent i = new Intent(this,TooTActivity.class);
            startActivity(i);
        });

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.main_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.app_name,R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
}
