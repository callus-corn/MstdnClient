package com.corn.collus.mstdnclient.models;

/**
 * Created by mitsu on 2017/05/03.
 */

public class Host {
    private String hostName;
    private String accessToken;
    private String clientId;
    private String clientSecret;
    private String scope;
    private String code;

    public Host(String name){
        hostName = name;
        scope = "read write follow";
    }

    public String getHostName() {
        return hostName;
    }
    public String getScope() {
        return scope;
    }
    public String getClientId() {
        return clientId;
    }
    public String getClientSecret() {
        return clientSecret;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String codeParameter){
        code = codeParameter;
    }
    public void setAccessToken(AccessToken token){
        accessToken = token.getAccessToken();
    }
    public void setClient(Client client){
        clientId = client.getClientId();
        clientSecret = client.getClientSecret();
    }

    public boolean isRegistered(){
        return clientId != null && clientSecret != null;
    }
    public boolean isAccessible(){return accessToken != null;}
}
