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
