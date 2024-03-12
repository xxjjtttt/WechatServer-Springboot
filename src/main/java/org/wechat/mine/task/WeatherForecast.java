package org.wechat.mine.task;

import com.fasterxml.jackson.databind.JsonNode;
import org.wechat.mine.tool.Json;
import org.wechat.mine.tool.Re;
import org.wechat.mine.tool.Requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class WeatherForecast extends AbstractTask {

  private HashMap<String, String> provinceHashMap;

  public WeatherForecast() {
    provinceHashMap = new HashMap<>();
    provinceHashMap.put("北京市", "BJ");
    provinceHashMap.put("上海市", "SH");
    provinceHashMap.put("天津市", "TJ");
    provinceHashMap.put("重庆市", "CQ");
    provinceHashMap.put("河北省", "HE");
    provinceHashMap.put("山西省", "SX");
    provinceHashMap.put("内蒙古自治区", "NM");
    provinceHashMap.put("辽宁省", "LN");
    provinceHashMap.put("吉林省", "JL");
    provinceHashMap.put("黑龙江省", "HL");
    provinceHashMap.put("江苏省", "JS");
    provinceHashMap.put("浙江省", "ZJ");
    provinceHashMap.put("安徽省", "AH");
    provinceHashMap.put("福建省", "FJ");
    provinceHashMap.put("江西省", "JX");
    provinceHashMap.put("山东省", "SD");
    provinceHashMap.put("河南省", "HA");
    provinceHashMap.put("湖北省", "HB");
    provinceHashMap.put("湖南省", "HN");
    provinceHashMap.put("广东省", "GD");
    provinceHashMap.put("广西壮族自治区", "GX");
    provinceHashMap.put("海南省", "HI");
    provinceHashMap.put("四川省", "SC");
    provinceHashMap.put("贵州省", "GZ");
    provinceHashMap.put("云南省", "YN");
    provinceHashMap.put("西藏自治区", "XZ");
    provinceHashMap.put("陕西省", "SN");
    provinceHashMap.put("甘肃省", "GS");
    provinceHashMap.put("青海省", "QH");
    provinceHashMap.put("宁夏回族自治区", "NX");
    provinceHashMap.put("新疆维吾尔族自治区", "XJ");
    provinceHashMap.put("台湾省", "TW");
    provinceHashMap.put("香港特别行政区", "HK");
    provinceHashMap.put("澳门特别行政区", "MO");
  }


  private String getProvinceCode(String province) {
    Re re = null;
    for (Map.Entry<String, String> entry : provinceHashMap.entrySet()) {
      re = new Re(province, entry.getKey());
      if (re.isValid()) {
        return entry.getValue();
      }
    }
    return "error";
  }

  private String getStatonId(String provinceCode, String city) {
    String statonidUrl = "http://www.nmc.cn/rest/province/A%s".formatted(provinceCode);
    Requests requests = new Requests(statonidUrl, false);
    try {
      Json json = new Json(requests.get().body().string());
      for (JsonNode jsonNode : json.getRoot()) {
        Re re = new Re(city, jsonNode.get("city").asText());
        if (re.isValid()) {
          return jsonNode.get("code").asText();
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return "error";
  }

  private String getWeatherInfo(String stationId, String province, String city) throws IOException {
    String url = "http://www.nmc.cn/rest/weather?stationid=%s".formatted(stationId);
    Requests requests = new Requests(url, false);
    Json json = new Json(requests.get().body().string());
    String currentTemperature = json.get("data").get("real").get("weather").get("temperature").asText();
    String currentFeelTemperature = json.get("data").get("real").get("weather").get("feelst").asText();
    String currentWeather = json.get("data").get("real").get("weather").get("info").asText();
    JsonNode temperaturePredict = json.get("data").get("tempchart");
    ArrayList<String[]> arrayLists = new ArrayList<>();
    for (JsonNode jsonNode : temperaturePredict) {
      if (!jsonNode.get("day_img").asText().equals("9999")) {
        String time = jsonNode.get("time").asText();
        String maxTemp = jsonNode.get("max_temp").asText();
        String minTemp = jsonNode.get("min_temp").asText();
        String dayText = jsonNode.get("day_text").asText();
        String nightText = jsonNode.get("night_text").asText();
        String weatherText;
        if (nightText.equals(dayText)) {
          weatherText = nightText;
        } else {
          weatherText = "%s转%s".formatted(dayText, nightText);
        }
        arrayLists.add(new String[]{time, maxTemp, minTemp, weatherText});
      }
    }
    String message = ("%s %s: \n实时天气: %s\n室外温度: %s℃").formatted(province, city, currentWeather, currentTemperature);

    if (currentFeelTemperature.equals("9999")) {
      System.out.println(currentFeelTemperature);
      message += "体感温度%s℃".formatted(currentFeelTemperature);
    }

    message += "\n下面是天气预报噢\n";

    String template = "%s:\n天气%s 温度%s-%s℃\n";

    for (String[] arrayList : arrayLists) {
      message += template.formatted(arrayList[0], arrayList[3], arrayList[1], arrayList[2]);
    }

    return message;
  }

  @Override
  public String[] getDataList(Matcher matcher) {
    String province = matcher.group(1);
    String city = matcher.group(2);
    String provinceCode = getProvinceCode(province);

    String stationId = getStatonId(provinceCode, city);
    if (stationId == "error") {
      return new String[]{"text", "我找不到你希望查询的地区，重新试试吧"};
    }

    try {
      String myMessage = getWeatherInfo(stationId, province, city);
      return new String[]{"text", myMessage};
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String[] getDataList() {
    return new String[0];
  }
}
