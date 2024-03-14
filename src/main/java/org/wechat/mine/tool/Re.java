package org.wechat.mine.tool;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Re {
  private final Matcher matcher;

  public Re(String reString, String content) {
    Pattern pattern = Pattern.compile(reString);
    matcher = pattern.matcher(content);
  }

  public Boolean isValid() {
    return matcher.find();
  }

  public Matcher match() {
    if (matcher.matches()) {
      return matcher;
    }
    return null;
  }

  public Matcher find() {
    if (matcher.find()) {
      return matcher;
    }

    return null;
  }

}

