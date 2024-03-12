package org.wechat.mine.tool;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;

public class Json {

  private JsonNode root;

  public Json(String jsonString) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      root = mapper.readTree(jsonString);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public JsonNode getRoot() {
    return root;
  }

  public JsonNode getNode(String key) {
    return root.get(key);
  }

  public JsonNode getNode(int index) {
    return root.get(index);
  }
}