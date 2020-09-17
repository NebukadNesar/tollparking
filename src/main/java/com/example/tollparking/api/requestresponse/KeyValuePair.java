package com.example.tollparking.api.requestresponse;

/**
 * <p>KeyValuePair class is a generic class which can be used in any response
 * <p>ResponseObjects can be extended by adding new key-val pairs messages. e.g: @StatisticsResponse
 */
public class KeyValuePair {

    String key;
    String val;

    public KeyValuePair() {
    }

    public KeyValuePair(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
