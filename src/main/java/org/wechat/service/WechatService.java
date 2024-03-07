package org.wechat.service;


import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wechat.component.WechatServerInfo;

import java.util.ArrayList;
import java.util.Collections;


@Service
public class WechatService {
    private WechatServerInfo wechatServerInfo;

    @Autowired
    public WechatService(WechatServerInfo wechatServerInfo) {
        this.wechatServerInfo = wechatServerInfo;
    }


    public String connectServer(HttpServletRequest httpServletRequest) {
        String echoStr = httpServletRequest.getParameter("echostr");
        String signature = httpServletRequest.getParameter("signature");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(httpServletRequest.getParameter("timestamp"));
        arrayList.add(httpServletRequest.getParameter("nonce"));
        arrayList.add(this.wechatServerInfo.getToken());
        Collections.sort(arrayList); // 字符串字典序重排
        String tempString = String.join("", arrayList); // 字符串转字符串

        String mine = DigestUtils.sha1Hex(tempString);

//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("SHA-1"); // 获取SHA-1算法实例
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        byte[] digest = md.digest(tempString.getBytes()); // 对字符串进行SHA-1加密
//
//        StringBuilder stringBuffer = new StringBuilder();
//        for (byte b : digest) {
//            stringBuffer.append(String.format("%02x", b)); // 将字节数组转换为十六进制字符串
//        }
//        String mine = stringBuffer.toString();

        if (!mine.equals(signature)) {
            System.out.println("fail to bind");
            return "fail";
        }

        return echoStr;
    }
}
