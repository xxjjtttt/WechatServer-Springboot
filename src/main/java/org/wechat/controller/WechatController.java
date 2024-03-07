package org.wechat.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.service.WechatService;
import org.wechat.service.handler.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Controller
public class WechatController {
    private final WechatService wechatService;
    private final Handler handler;


    @Autowired
    public WechatController(WechatService wechatService, Handler handler) {
        this.wechatService = wechatService;
        this.handler = handler;
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String connectServer(HttpServletRequest httpServletRequest) {
        return this.wechatService.connectServer(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String handleMessage(HttpServletRequest httpServletRequest) {
        InputStream xmlStream;
        try {
            xmlStream = httpServletRequest.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String xmlString = null;
        try {
            xmlString = new String(xmlStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(xmlString);
        return "succeed";
    }
}
