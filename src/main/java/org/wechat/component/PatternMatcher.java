package org.wechat.component;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PatternMatcher {

  public Matcher match(String reString, String content) {
    Pattern pattern = Pattern.compile(reString);
    Matcher matcher = pattern.matcher(content);

    if (matcher.matches()) {
      return matcher;
    }

    return null;
  }

  public Matcher find(String reString, String content) {
    Pattern pattern = Pattern.compile(reString);
    Matcher matcher = pattern.matcher(content);

    if (matcher.find()) {
      return matcher;
    }

    return null;
  }

}
