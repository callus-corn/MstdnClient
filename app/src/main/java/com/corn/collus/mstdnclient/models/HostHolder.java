package com.corn.collus.mstdnclient.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by mitsu on 2017/05/03.
 */

public class HostHolder {
    private static HostHolder hostHolder = new HostHolder();
    Map<String,Host> hosts;
    Set<String> keys;
    Host active = null;

    private HostHolder(){
        hosts = new HashMap<String,Host>();
        keys = new HashSet<String>();
    }

    public static HostHolder getInstance(){
        return hostHolder;
    }

    public boolean isEmpty(){
        return hosts.isEmpty();
    }

    public Host getHost(String hostName){
        return hosts.containsKey(hostName) ? hosts.get(hostName): new Host(hostName);
    }

    public void setHost(Host host){
        hosts.put(host.getHostName(),host);
        keys.add(host.getHostName());
    }

    public void setActive(Host host){
        active = host;
    }

    public Host getActive() {
        return active;
    }
}
