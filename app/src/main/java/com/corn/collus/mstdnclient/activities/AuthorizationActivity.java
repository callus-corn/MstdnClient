package com.corn.collus.mstdnclient.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.corn.collus.mstdnclient.R;
import com.corn.collus.mstdnclient.presenters.AuthAPIPresenter;

public class AuthorizationActivity extends AppCompatActivity{
    private AuthAPIPresenter api = new AuthAPIPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        if(Intent.ACTION_VIEW.equals(getIntent().getAction()))
        {
            //ブラウザからこのアクティビティが直接呼び出された場合
            complete();
        }

        Button button = (Button)findViewById(R.id.auth_button);
        button.setOnClickListener(v->{
            button.setEnabled(false);
            EditText editText = (EditText)findViewById(R.id.auth_edit_text);
            String hostName = editText.getText().toString();
            if(!hostName.equals("")) {
                api.authorize(hostName)
                        .subscribe(url -> startBrowserAuthorization(url));
            }
            button.setEnabled(true);
        });
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
        if(uri != null) {
            api.updateUri(uri).subscribe(b -> complete());
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        api.stop();
    }

    private void startBrowserAuthorization(String url){
        Uri uri = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(i);
    }

    private void complete(){
        Intent i = new Intent(this,StartActivity.class);
        startActivity(i);
        this.finish();
    }
}
