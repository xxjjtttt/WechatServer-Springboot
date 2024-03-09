package org.wechat.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class ImageMessage extends AbstractMessage {

  @XStreamAlias("PicUrl")
  private String picUrl;
  @XStreamAlias("MediaId")
  private String mediaId;


  private ImageMessage(String toUserName, String fromUserName, String picUrl, String mediaId) {
    super(toUserName, fromUserName);
    this.msgType = "image";
    this.picUrl = picUrl;
    this.mediaId = mediaId;
  }

  public static ImageMessage createImageMessage(String toUserName, String fromUserName, String picUrl, String mediaId) {
    return new ImageMessage(toUserName, fromUserName, picUrl, mediaId);
  }

  public String getPicUrl() {
    return picUrl;
  }

  public String getMediaId() {
    return mediaId;
  }
}
