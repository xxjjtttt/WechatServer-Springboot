package org.wechat.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.service.WechatService;

@Controller
public class WechatController {
  private final WechatService wechatService;

  @Autowired
  public WechatController(WechatService wechatService) {
    this.wechatService = wechatService;
  }

  @ResponseBody
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String connectServer(HttpServletRequest httpServletRequest) {
    return wechatService.connectServer(httpServletRequest);
  }

  @ResponseBody
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String handleMessage(HttpServletRequest httpServletRequest) {
    return wechatService.makeResponse(httpServletRequest);
  }
}
