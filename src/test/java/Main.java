import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    // 定义一个正则表达式
    String regex = "\\d+"; // 匹配一个或多个数字

    // 创建一个Pattern对象
    Pattern pattern = Pattern.compile(regex);

    // 定义一个需要匹配的字符串
    String input = "abc123def456";

    // 创建一个Matcher对象
    Matcher matcher = pattern.matcher(input);

    // 使用find()方法查找匹配的子串
    while (matcher.find()) {
      System.out.println("找到匹配的子串： " + matcher.group());
    }
  }
}
