package org.wechat.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wechat.component.handler.TextHandler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

@Component
public class Repeater {
  private MessageProcessor messageProcessor;
  private TextHandler textHandler;

  @Autowired
  public Repeater(MessageProcessor messageProcessor, TextHandler textHandler) {
    this.messageProcessor = messageProcessor;
    this.textHandler = textHandler;
  }

  public String makeResponse(HashMap<String, String> hashMap) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    String[] dataList = null;
    switch (hashMap.get("MsgType")) {
      case "event":
        break;
      case "text":
        dataList = textHandler.getDataList(hashMap.get("Content"));
        break;
      case "image":
        break;
      case "voice":
        break;
      case "vedio":
        break;
      case "shortvideo":
        break;
      case "location":
        break;
      case "link":
        break;
    }

    if (dataList != null) {
      return messageProcessor.createMessage(hashMap, dataList);
    }

    return messageProcessor.createMessage(hashMap, new String[]{"text, 看不懂奥"});
  }
}
