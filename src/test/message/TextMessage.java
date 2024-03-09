package org.wechat.message;


import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("xml")
public class TextMessage extends AbstractMessage {
  @XStreamAlias("Content")
  private String content;

  private TextMessage(String toUserName, String fromUserName, String content) {
    super(toUserName, fromUserName);
    msgType = "text";
    this.content = content;
  }

  public static TextMessage createTextMessage(String toUserName, String fromUserName, String content) {
    return new TextMessage(toUserName, fromUserName, content);
  }

  public String getContent() {
    return content;
  }

}
