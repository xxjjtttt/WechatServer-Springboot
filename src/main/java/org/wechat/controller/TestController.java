package org.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.component.WechatServerInfo;

@Controller
public class TestController {
  private WechatServerInfo wechatServerInfo;

  @Autowired
  public TestController(WechatServerInfo wechatServerInfo) {
    this.wechatServerInfo = wechatServerInfo;
  }

  @ResponseBody
  @RequestMapping("/hehe")
  public String getInfo() {
    return this.wechatServerInfo.getEncodingAESKey();
  }
}
