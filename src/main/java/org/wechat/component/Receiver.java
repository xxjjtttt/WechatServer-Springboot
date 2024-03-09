package org.wechat.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Receiver {
  private Repeater repeater;

  @Autowired
  public Receiver(Repeater repeater) {
    this.repeater = repeater;
  }

  public String makeResponse(String xmlString) {
    return repeater.makeResponse(xmlString);
  }

}
