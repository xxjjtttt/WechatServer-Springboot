package org.wechat.mine.tool;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Re {
  private Matcher matcher;

  public Re(String reString, String content) {
    Pattern pattern = Pattern.compile(reString);
    matcher = pattern.matcher(content);
  }

  public Boolean isValid() {
    if (matcher.find()) {
      return true;
    }
    return false;
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

