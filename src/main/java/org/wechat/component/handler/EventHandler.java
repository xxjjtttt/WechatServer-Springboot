package org.wechat.component.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.wechat.component.FunctionCaller;

import java.lang.reflect.InvocationTargetException;

@Component
@PropertySource(value = {"event.yaml"})
@ConfigurationProperties(prefix = "event")
public class EventHandler {

  private FunctionCaller functionCaller;

  private String[][] order;

  @Autowired
  public EventHandler(FunctionCaller functionCaller) {
    this.functionCaller = functionCaller;
  }

  public void setOrder(String[][] order) {
    this.order = order;
  }

  public String[] getDataList(String event, String eventKey) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    String[] dataList = null;
    for (String[] task : order) {
      if (event.equals(task[0]) || eventKey.equals(task[1])) {
        dataList = functionCaller.getDataList(task[2]);
      }
    }
    return dataList;
  }
}
