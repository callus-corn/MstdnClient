package com.corn.collus.mstdnclient.models;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
