package org.wechat.handler;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@PropertySource(value = {"message.yaml"})
@ConfigurationProperties(prefix = "text")
public class TextHandler {
  private String[] keys;
  private String[] keywords;
  private HashMap<String, String> map;

  public void setKeys(String[] keys) {
    this.keys = keys;
  }

  public void setKeywords(String[] keywords) {
    this.keywords = keywords;
  }

  public String getMyContent(HashMap<String,String> map) {
    return keys[0];
  }

  public String matchKeywords(String content) {

    return "";
  }
}
