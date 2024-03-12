package org.wechat.component;


import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class Reader {
  public String readString(String path) throws IOException {
    StringBuilder string = new StringBuilder();
    BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      string.append(line);
    }
    System.out.println(string.toString());
    return string.toString();
  }
}
