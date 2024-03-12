package org.wechat.component;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;


@Component
public class MessageProcessor {
  public HashMap<String, String> createHashMap(String xmlString) throws DocumentException {
    HashMap<String, String> resultMap = new HashMap<>();
    // 创建SAXReader对象
    SAXReader reader = new SAXReader();
    // 读取XML字符串
    Document document = reader.read(new StringReader(xmlString));
    // 获取根元素
    Element rootElement = document.getRootElement();
    // 遍历根元素的子元素
    for (Element element : rootElement.elements()) {
      // 将元素的标签名和文本内容添加到Map中
      resultMap.put(element.getName(), element.getText());
    }
    return resultMap;
  }


  // args[0] MsgType args[1]... 响应的数据组成的数组 类型不一样 长度同样不会一样
  public String createMessage(HashMap<String, String> map, String[] args) throws IOException {
    // 创建Document对象
    Document document = DocumentHelper.createDocument();
    // 创建根元素
    Element root = document.addElement("xml");
    addNecessaryElemant(root, map.get("FromUserName"), map.get("ToUserName"));
    switch (args[0]) {
      case "text":
        addTextMessageElement(root, args[1]);
        break;
      case "image":
        addImageMessageElement(root, args[1], args[2]);
        break;
      case "voice":
        break;
      case "video":
        break;
      case "music":
        break;
      case "news":
        break;
    }
    // 使用OutputFormat设置输出格式
    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setSuppressDeclaration(true);

    // 使用XMLWriter将Document对象写入到字符串中
    StringWriter writer = new StringWriter();
    XMLWriter xmlWriter = new XMLWriter(writer, format);

    xmlWriter.write(document);
    xmlWriter.close();

    return writer.toString();
  }


  private void addNecessaryElemant(Element root, String fromUserName, String toUserName) {
    // 需要颠倒 From To
    root.addElement("FromUserName").addCDATA(toUserName);
    root.addElement("ToUserName").addCDATA(fromUserName);
    root.addElement("CreateTime").setText(String.valueOf((System.currentTimeMillis() / 1000)));
  }

  private void addTextMessageElement(Element root, String Content) {
    root.addElement("MsgType").addCDATA("text");
    root.addElement("Content").addCDATA(Content);
  }

  private void addImageMessageElement(Element root, String mediaId, String picUrl) {
    root.addElement("MediaId").addCDATA(mediaId);
    root.addElement("PicUrl").addCDATA(picUrl);
  }
}