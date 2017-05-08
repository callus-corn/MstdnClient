package com.corn.collus.mstdnclient.presenters;

import android.net.Uri;

import com.corn.collus.mstdnclient.models.Host;
import com.corn.collus.mstdnclient.models.HostHolder;
import com.corn.collus.mstdnclient.models.MstdnAPI;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mitsu on 2017/05/03.
 */

public class AuthAPIPresenter {
    private final CompositeDisposable dis = new CompositeDisposable();
    private Host host;

    public Observable<String> authorize(String hostName){
        return Observable.create(o->{
            host = HostHolder.getInstance().getHost(hostName);
            if(!host.isRegistered()) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://" + host.getHostName())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                MstdnAPI api = retrofit.create(MstdnAPI.class);
                Disposable d = api.register("MstdnClient","mstdn://com.corn.collus.mstdnclient",host.getScope())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(client -> {
                            host.setClient(client);
                            o.onNext("https://" + host.getHostName() + "/oauth/authorize?response_type=code&redirect_uri=mstdn://com.corn.collus.mstdnclient" + "&client_id=" + host.getClientId() + "&scope=" + host.getScope());
                        },e->{});
                dis.add(d);
            }else {
                o.onNext("https://" + host.getHostName() + "/oauth/authorize?response_type=code&redirect_uri=mstdn://com.corn.collus.mstdnclient" + "&client_id=" + host.getClientId() + "&scope=" + host.getScope());
            }
        });
    }

    public Observable<Boolean> updateUri(Uri uri){
        return Observable.create(o-> {
            String code = uri.getQueryParameter("code");
            host.setCode(code);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://" + host.getHostName())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            MstdnAPI api = retrofit.create(MstdnAPI.class);
            Disposable d = api.getAccessToken("mstdn://com.corn.collus.mstdnclient","authorization_code",host.getClientId(),host.getClientSecret(),host.getCode(),host.getScope())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(accessToken ->{
                        host.setAccessToken(accessToken);
                        HostHolder.getInstance().setHost(host);
                        HostHolder.getInstance().setActive(host);
                        o.onNext(true);
                    },e->{});
            dis.add(d);
        });
    }

    public void stop(){
        dis.dispose();
    }
}
