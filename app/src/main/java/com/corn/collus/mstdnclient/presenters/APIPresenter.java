package com.corn.collus.mstdnclient.presenters;

import com.corn.collus.mstdnclient.models.Host;
import com.corn.collus.mstdnclient.models.HostHolder;
import com.corn.collus.mstdnclient.models.MstdnAPI;
import com.corn.collus.mstdnclient.models.Toot;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mitsu on 2017/05/07.
 */

public class APIPresenter {
    private static APIPresenter apiPresenter = new APIPresenter();
    private Long last_id;

    private APIPresenter(){
        last_id = new Long(0);
    }

    public static APIPresenter getInstance(){
        return apiPresenter;
    }

    public Single<List<Toot>> readHome(){
        return Single.create(o->{
            Host host = HostHolder.getInstance().getActive();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://"+host.getHostName())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MstdnAPI api = retrofit.create(MstdnAPI.class);

            api.getHome("Bearer " + host.getAccessToken(),last_id.toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(tootList -> {
                        if(!tootList.isEmpty()) {
                            last_id = Long.parseLong(tootList.get(0).getId());
                        }
                        o.onSuccess(tootList);
                    });
        });
    }
}
