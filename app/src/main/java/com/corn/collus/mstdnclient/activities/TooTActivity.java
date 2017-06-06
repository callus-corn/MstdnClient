package com.corn.collus.mstdnclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.corn.collus.mstdnclient.R;
import com.corn.collus.mstdnclient.presenters.APIPresenter;

/**
 * Created by mitsu on 2017/06/06.
 */

public class TooTActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toot);

        Button button = (Button)findViewById(R.id.toot_button);
        EditText editText = (EditText)findViewById(R.id.toot_edit);

        button.setOnClickListener(l -> {
            String text = editText.getText().toString();
            APIPresenter.getInstance().toot(text).subscribe(toot -> {
                this.finish();
            });
        });

    }
}
