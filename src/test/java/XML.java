import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


public class XML {
    public static void main(String[] args) {
        String xml = "<root><name>John</name><age>25</age></root>";
        Map<String, String> resultMap = parseXmlToMap(xml);
        System.out.println(resultMap); // 输出：{name=John, age=25}
    }
    public static Map<String, String> parseXmlToMap(String xml) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            // 创建SAXReader对象
            SAXReader reader = new SAXReader();
            // 读取XML字符串
            Document document = reader.read(new StringReader(xml));
            // 获取根元素
            Element rootElement = document.getRootElement();
            // 遍历根元素的子元素
            for (Element element : rootElement.elements()) {
                // 将子元素的标签名作为键，文本内容作为值存储到HashMap中
                resultMap.put(element.getName(), element.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}

