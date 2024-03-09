package org.wechat.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wechat.handler.TextHandler;

import java.util.HashMap;

@Component
public class Repeater {
  MessageProcessor messageProcessor;
  TextHandler textHandler;

  @Autowired
  public Repeater(MessageProcessor messageProcessor, TextHandler textHandler) {
    this.messageProcessor = messageProcessor;
    this.textHandler = textHandler;
  }

  public String makeResponse(String xmlString) {
    HashMap<String,String> hashMap = messageProcessor.createHashMap(xmlString);
    switch (hashMap.get("MsgType")) {
      case "event": break;
      case "text": textHandler.getMyContent(hashMap); break;
      case "image": break;
      case "voice": break;
      case "vedio": break;
      case "shortvideo": break;
      case "location": break;
      case "link": break;
    }


    return "";
  }
}
