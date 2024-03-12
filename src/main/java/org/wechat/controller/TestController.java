package org.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.component.WechatServerInfo;

import java.io.File;


@Controller
public class TestController {
  private WechatServerInfo wechatServerInfo;


  @Autowired
  public TestController(WechatServerInfo wechatServerInfo) {
    this.wechatServerInfo = wechatServerInfo;

  }

  @ResponseBody
  @RequestMapping("/download")
  public ResponseEntity<Resource> getInfo() {
    String path = "D:\\IDE\\Code\\Idea\\WechatServer\\out\\artifacts\\WechatServer_jar\\WechatServer.jar";
    String contentDisposition = ContentDisposition
        .builder("attachment")
        .filename(path)
        .build().toString();

    File file = new File(path);
    if (file.exists()) {
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
          .contentType(MediaType.APPLICATION_OCTET_STREAM)
          .body(new FileSystemResource(path));
    } else {
      return ResponseEntity.badRequest()
          .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition + " Not Found")
          .contentType(MediaType.APPLICATION_OCTET_STREAM)
          .body(null);
    }
  }

  @ResponseBody
  @RequestMapping("/test")
  public String getAccessToken() {
    return wechatServerInfo.getAccessToken();
  }
}
