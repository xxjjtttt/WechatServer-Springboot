package org.wechat.component;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PatternMatcher {

  public String[] match(String reString, String content) {
    Pattern pattern = Pattern.compile(reString);
    Matcher matcher = pattern.matcher(content);

    ArrayList<String> args = new ArrayList<>();

    if (matcher.matches()) {
      for (int i = 1; i <= matcher.groupCount(); i++) {
        args.add(matcher.group(i));
      }

    }
    return args.toArray(new String[0]);
  }


  public String[] find(String reString, String content) {
    Pattern pattern = Pattern.compile(reString);
    Matcher matcher = pattern.matcher(content);
    ArrayList<String> args = new ArrayList<>();

    if (matcher.find()) {
      for (int i = 1; i <= matcher.groupCount(); i++) {
        args.add(matcher.group(i));
      }
    }

    return args.toArray(new String[0]);
  }

}
