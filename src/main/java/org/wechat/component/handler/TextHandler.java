package org.wechat.component.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.wechat.component.FunctionCaller;
import org.wechat.component.PatternMatcher;

import java.lang.reflect.InvocationTargetException;

@Component
@PropertySource(value = {"text.yaml"})
@ConfigurationProperties(prefix = "text")
public class TextHandler {
  private FunctionCaller functionCaller;
  private PatternMatcher patternMatcher;

  private String[][] order;


  @Autowired
  public TextHandler(FunctionCaller functionCaller, PatternMatcher patternMatcher) {
    this.functionCaller = functionCaller;
    this.patternMatcher = patternMatcher;
  }

  public void setOrder(String[][] order) {
    this.order = order;
  }

  public String[] getDataList(String content) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    String[] args = null;
    String[] dataList = null;
    for (String[] task : order) {
      args = patternMatcher.match(task[0], content);
      if (args.length != 0) {
        dataList = functionCaller.getDataList(task[1], args);
      }
    }
    return dataList;
  }

}
