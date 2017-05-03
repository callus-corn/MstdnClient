package com.corn.collus.mstdnclient.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.corn.collus.mstdnclient.R;
import com.corn.collus.mstdnclient.models.HostHolder;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        HostHolder hostHolder = HostHolder.getInstance();

        if(hostHolder.isEmpty())
        {
            Intent i = new Intent(this,AuthorizationActivity.class);
            startActivity(i);
        }else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        this.finish();
    }
}
