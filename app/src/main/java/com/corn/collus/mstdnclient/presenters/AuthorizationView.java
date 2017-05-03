package com.corn.collus.mstdnclient.presenters;

/**
 * Created by mitsu on 2017/05/03.
 */

import android.view.View.OnClickListener;

public interface AuthorizationView {

    void startAuthorization(OnClickListener listner);
    String getHostName();
    void startBrowserAuthorization(String url);
    void pause();
    void liftPause();
    void complete();
}
