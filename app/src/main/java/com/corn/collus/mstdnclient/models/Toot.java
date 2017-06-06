package com.corn.collus.mstdnclient.models;

/**
 * Created by mitsu on 2017/05/08.
 */

public class Toot {
    private String id;
    private String content;
    private Account account;

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    private class Account{

    }
}
