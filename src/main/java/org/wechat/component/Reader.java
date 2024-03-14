package org.wechat.component;


import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class Reader {
  public String readString(String path) throws IOException {
    StringBuilder string = new StringBuilder();

    InputStream is = this.getClass().getResourceAsStream(path);

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      string.append(line);
    }
    return string.toString();
  }
}
