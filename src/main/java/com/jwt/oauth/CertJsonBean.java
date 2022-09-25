package com.jwt.oauth;
import java.util.*;
public class CertJsonBean {
    public String privatekey;
    public Map<String, Object> payload;
    public Map<String, Object> header;
    public String getPrivatekey() {
        return privatekey;
    }
    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
    }
    public Map<String, Object> getPayload() {
        return payload;
    }
    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }
    public Map<String, Object> getHeader() {
        return header;
    }
    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }
    @Override
    public String toString() {
        return "CertJsonBean{" +
                "privatekey='" + privatekey + '\'' +
                ", payload=" + payload +
                ", header=" + header +
                '}';
    }
}