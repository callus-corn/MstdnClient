package com.corn.collus.mstdnclient.models;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by mitsu on 2017/05/02.
 */

public interface MstdnAPI {
    @FormUrlEncoded
    @POST("api/v1/apps")
    Observable<Client> register(@Field("client_name")String client_name,@Field("redirect_uris")String uri,@Field("scopes")String scope);

    @FormUrlEncoded
    @POST("oauth/token")
    Observable<AccessToken> getAccessToken(@Field("redirect_uri")String redirect_uri,@Field("grant_type")String grant_type,@Field("client_id")String client_id,@Field("client_secret")String client_secret,@Field("code")String code,@Field("scope")String scope);

    @GET("api/v1/timelines/home?limit=40")
    Observable<List<Toot>> getHome(@Header("Authorization")String header, @Query("since_id")String since_id);

    @GET("api/v1/timelines/public?limit=40")
    Observable<List<Toot>> getPublic(@Header("Authorization")String header, @Query("since_id")String since_id);

    @FormUrlEncoded
    @POST("api/v1/statuses")
    Observable<Toot> toot(@Header("Authorization")String header,@Field("status")String text);
}
