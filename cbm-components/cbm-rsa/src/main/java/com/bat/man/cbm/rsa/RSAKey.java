package com.bat.man.cbm.rsa;

import java.io.Serializable;
import java.time.Duration;
import java.util.UUID;

public abstract class RSAKey implements Serializable {

    public static final long DEFAULT_TIME_TO_LIVE_MILLIS = Duration.ofMinutes(5).toMillis();

    private static final long serialVersionUID = 5686110814570011909L;

    private String id = UUID.randomUUID().toString().replaceAll("-", "");

    private long timestamp = System.currentTimeMillis();

    private long ttl = DEFAULT_TIME_TO_LIVE_MILLIS;

    public RSAKey() {

    }

    public RSAKey(String id, String encryptType, long timestamp, long ttl) {
        this.id = id;
        this.timestamp = timestamp;
        this.ttl = ttl;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

}