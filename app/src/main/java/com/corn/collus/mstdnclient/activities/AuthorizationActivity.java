package com.corn.collus.mstdnclient.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.corn.collus.mstdnclient.R;
import com.corn.collus.mstdnclient.presenters.AuthAPIPresenter;
import com.corn.collus.mstdnclient.presenters.AuthorizationView;

public class AuthorizationActivity extends AppCompatActivity implements AuthorizationView{
    private AuthAPIPresenter api = new AuthAPIPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        api.start();
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Intent intent = getIntent();
        Uri uri = intent.getData();
        api.updateUri(uri);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        api.stop();
    }

    @Override
    public void startAuthorization(View.OnClickListener listner){
        Button button = (Button)findViewById(R.id.auth_button);
        button.setOnClickListener(listner);
    }

    @Override
    public String getHostName(){
        EditText editText = (EditText)findViewById(R.id.auth_edit_text);
        return editText.getText().toString();
    }

    @Override
    public void startBrowserAuthorization(String url){
        Uri uri = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(i);
    }

    @Override
    public void pause(){
        Button button = (Button)findViewById(R.id.auth_button);
        button.setEnabled(false);
    }

    @Override
    public void liftPause(){
        Button button = (Button)findViewById(R.id.auth_button);
        button.setEnabled(true);
    }

    @Override
    public void complete(){
        Intent i = new Intent(this,StartActivity.class);
        startActivity(i);
        this.finish();
    }
}
