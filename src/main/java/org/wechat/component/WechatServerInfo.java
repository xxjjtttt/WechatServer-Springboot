package org.wechat.component;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;


@Component
public class WechatServerInfo {
  @Value("${wechat.appId}")
  private String appId;
  @Value("${wechat.appSecret}")
  private String appSecret;
  @Value("${wechat.token}")
  private String token;
  @Value("${wechat.encodingAESKey}")
  private String encodingAESKey;

  private String accessToken = "";
  private long expireTime;
  private HttpSender httpSender;

  @Autowired
  public WechatServerInfo(HttpSender httpSender) {
    this.httpSender = httpSender;
  }

  private Boolean isExpired() {
    Instant now = Instant.now();
    long timestamp = now.toEpochMilli();
    if (expireTime <= timestamp) {
      return true;
    }
    return false;
  }

  private void updateAccessToken() {
    String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s".formatted(appId, appSecret);
    Instant now = Instant.now();
    long timestamp = now.toEpochMilli();
    expireTime = timestamp + 7200000;
    try {
      String jsonString = httpSender.doGet(url);
      JsonMapper jsonMapper = new JsonMapper();
      JsonNode jsonNode = jsonMapper.readTree(jsonString);
      accessToken = jsonNode.get("access_token").asText();
    } catch (IOException e) {
      System.out.println("fail to get AccessToken");
      throw new RuntimeException(e);
    }
  }

  public String getAccessToken() {
    if (accessToken.isEmpty() || isExpired()) {
      updateAccessToken();
    }
    return accessToken;
  }

  public String getAppId() {
    return appId;
  }

  public String getAppSecret() {
    return appSecret;
  }

  public String getToken() {
    return token;
  }

  public String getEncodingAESKey() {
    return encodingAESKey;
  }
}