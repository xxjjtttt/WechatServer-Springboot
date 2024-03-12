import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class ReadFileToString {
  public static void main(String[] args) {
    File file = new File("src/main/resources/menu.json");
    String content = readFileToString(file);
    System.out.println(content);
  }

  public static String readFileToString(File file) {
    StringBuilder contentBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        contentBuilder.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return contentBuilder.toString();
  }
}