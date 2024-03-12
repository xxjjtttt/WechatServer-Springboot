package org.wechat.service;


import jakarta.servlet.http.HttpServletRequest;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wechat.component.MenuCreater;
import org.wechat.component.Reader;
import org.wechat.component.Receiver;
import org.wechat.component.WechatServerInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;


@Service
public class WechatService {
  private WechatServerInfo wechatServerInfo;
  private Receiver receiver;
  private Reader reader;
  private MenuCreater menuCreater;


  @Autowired
  public WechatService(WechatServerInfo wechatServerInfo, Receiver receiver, Reader reader, MenuCreater menuCreater) {
    this.wechatServerInfo = wechatServerInfo;
    this.receiver = receiver;
    this.reader = reader;
    this.menuCreater = menuCreater;
  }

  public String connectServer(HttpServletRequest httpServletRequest) {
    String echoStr = httpServletRequest.getParameter("echostr");
    String signature = httpServletRequest.getParameter("signature");
    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add(httpServletRequest.getParameter("timestamp"));
    arrayList.add(httpServletRequest.getParameter("nonce"));
    arrayList.add(wechatServerInfo.getToken());
    Collections.sort(arrayList); // 字符串字典序重排
    String tempString = String.join("", arrayList); // 字符串转字符串
    MessageDigest md = null;
    System.out.println(echoStr + "\n" + signature + "\n" + arrayList.get(0) + "\n" + arrayList.get(1) + "\n" + arrayList.get(2));
    try {
      md = MessageDigest.getInstance("SHA-1");
    } catch (NoSuchAlgorithmException e) {
      System.out.println("fail to get my signature");
    }
    byte[] bytes = md.digest(tempString.getBytes());
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02x", b));
    }
    String mine = sb.toString();

    if (!mine.equals(signature)) { // 对比签名
      System.out.println("fail to bind");
      return "fail";
    }
//    System.out.println("bing successfully");
    try {
      menuCreater.InitMenu();
    } catch (IOException e) {
      System.out.println("fail to init menu");
    }
    return echoStr;

  }

  public String makeResponse(HttpServletRequest httpServletRequest) {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      BufferedReader reader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(), StandardCharsets.UTF_8));
      String line;
      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line).append(System.lineSeparator());
      }
      String xmlString = stringBuilder.toString();
//      System.out.println("receive a string" + xmlString);
      return receiver.makeResponse(xmlString);
    } catch (DocumentException | IOException | ClassNotFoundException | InvocationTargetException |
             NoSuchMethodException | InstantiationException | IllegalAccessException e) {
      System.out.println("your business function error");
      return "succeed";
    }

  }

}
