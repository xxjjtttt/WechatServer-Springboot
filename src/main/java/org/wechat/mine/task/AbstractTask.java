package org.wechat.mine.task;

import java.util.regex.Matcher;


public abstract class AbstractTask {
  /*
    自己的业务逻辑的类必须继承AbstractTask类 并实现getDataList函数
    getDataList函数返回一个字符串数组
    DataList[0]: 是希望响应消息的类型 "text" "image"
    DataList[1]...: 是这个响应类型所必须的参数 text类型 -> content; image类型 -> mediaId picUrl
   */

  public abstract String[] getDataList(Matcher matcher);

  public abstract String[] getDataList();
}
