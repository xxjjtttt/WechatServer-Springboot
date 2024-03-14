package org.wechat.mine.task;

import com.fasterxml.jackson.databind.JsonNode;
import org.wechat.mine.tool.Json;
import org.wechat.mine.tool.Requests;

import java.io.IOException;
import java.util.HashMap;

public class SearchWords extends AbstractTask {
  private final HashMap<String, String> languageHashMap;

  public SearchWords() {
    languageHashMap = new HashMap<>();
    languageHashMap.put("英", "en");
    languageHashMap.put("法", "fr");
    languageHashMap.put("日", "ja");
    languageHashMap.put("韩", "ko");
  }

  private String getWordsInfo(String language, String word) throws IOException {
    String url = "https://dict.youdao.com/suggest?&num=1&doctype=json&le=%s&q=%s".formatted(language, word);
    Requests requests = new Requests(url, false);
    String jsonString = requests.get().body().string();
    Json json = new Json(jsonString);
    JsonNode entry = json.get("data").get("entries").get(0);
    String myContent = "原词：%s\n翻译：%s".formatted(entry.get("entry"), entry.get("explain"));
    return myContent;
  }

  @Override
  public String[] getDataList(String[] args) {
    String myContent = null;
    try {
      myContent = getWordsInfo(languageHashMap.get(args[1]), args[0]);
      return new String[]{"text", myContent};
    } catch (IOException e) {
      return new String[]{"text", "你查询的诗歌几把"};
    }
  }

  @Override
  public String[] getDataList() {
    return new String[0];
  }
}
