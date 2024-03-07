package org.wechat.component;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class WechatServerInfo {
    @Value("${wechat.appID}")
    private String appID;
    @Value("${wechat.appSecret}")
    private String appSecret;
    @Value("${wechat.token}")
    private String token;
    @Value("${wechat.encodingAESKey}")
    private String encodingAESKey;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }
}
