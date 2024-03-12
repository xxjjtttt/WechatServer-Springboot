package org.wechat.component;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;


@Component
public class Receiver {
  MessageProcessor messageProcessor;
  private Repeater repeater;

  @Autowired
  public Receiver(MessageProcessor messageProcessor, Repeater repeater) {
    this.messageProcessor = messageProcessor;
    this.repeater = repeater;
  }

  public String makeResponse(String xmlString) throws DocumentException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    HashMap<String, String> hashMap = messageProcessor.createHashMap(xmlString);
    return repeater.makeResponse(hashMap);
  }

}
