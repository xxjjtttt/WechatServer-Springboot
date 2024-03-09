package org.wechat.message;


import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class AbstractMessage {
  @XStreamAlias("ToUserName")
  protected String toUserName;
  @XStreamAlias("FromUserName")
  protected String fromUserName;
  @XStreamAlias("CreateTime")
  protected long createTime;
  @XStreamAlias("MsgType")
  protected String msgType;

  protected AbstractMessage(String toUserName, String fromUserName) {
    this.toUserName = toUserName;
    this.fromUserName = fromUserName;
    this.createTime = (int) (System.currentTimeMillis() / 1000);
    this.msgType = "abstract";
  }

  public String getToUserName() {
    return toUserName;
  }

  public String getFromUserName() {
    return fromUserName;
  }

  public long getCreateTime() {
    return createTime;
  }

  public String getMsgType() {
    return msgType;
  }
}
