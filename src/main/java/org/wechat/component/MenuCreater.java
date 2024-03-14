package org.wechat.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MenuCreater {
  private HttpSender httpSender;
  private WechatServerInfo wechatServerInfo;
  private Reader reader;

  @Autowired
  public MenuCreater(HttpSender httpSender, WechatServerInfo wechatServerInfo, Reader reader) {
    this.httpSender = httpSender;
    this.wechatServerInfo = wechatServerInfo;
    this.reader = reader;
  }

  public void InitMenu() throws IOException {
    String accessToken = wechatServerInfo.getAccessToken();
    String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s".formatted(accessToken);
    String jsonString = reader.readString("/menu.json");
    System.out.println(jsonString);
    httpSender.doPost(url, jsonString);
  }
}
