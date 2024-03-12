package org.wechat.mine.task;

import java.util.regex.Matcher;

public class HelpTask extends AbstractTask {

  @Override
  public String[] getDataList(Matcher matcher) {
    return new String[0];
  }

  @Override
  public String[] getDataList() {
    return new String[]{"text",
        "欢迎你的关注，下面是使用方法：\n"+
        "1.执行任务\n"+
        "a.天气查询任务：\n"+
        "输入“{省份} {城市} 天气”\n"+
//        "b.查词任务\n"+
//        "输入{单词} {语言} 查词\n"+
//        "语言支持：英、法、日、韩\n"+
//        "\n2.一些无聊的关键词，自个来探索\n"+
//        "tips:输入{帮助} 重新获取本消息\n"+
        "还有更多项目正在开发中🤔"};
  }
}
