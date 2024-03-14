package org.wechat.mine.task;

public class NothingTask extends AbstractTask {

  @Override
  public String[] getDataList(String[] args) {
    return new String[0];
  }

  @Override
  public String[] getDataList() {
    return new String[]{"text", "你发的是什么啦，看不懂啦啊🥰"};
  }
}
