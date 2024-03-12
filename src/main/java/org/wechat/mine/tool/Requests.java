package org.wechat.mine.tool;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;


public class Requests {
  private String url;
  private Boolean useProxy;

  public Requests(String url, Boolean useProxy) {
    this.url = url;
    this.useProxy = useProxy;
  }

  public Response get() {
    OkHttpClient okHttpClient;
    if (useProxy) {
      String proxy = getProxy();
      Json json = new Json(proxy);
      String ip = json.getNode("data").get("proxy_list").get(0).get("ip").asText();
      int port = json.getNode("data").get("proxy_list").get(0).get("port").asInt();
      okHttpClient = new OkHttpClient.Builder()
          .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port)))
          .build();
    } else {
      okHttpClient = new OkHttpClient();
    }

    Request request = new Request.Builder()
        .url(url)
        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
        .build();
    try {
      Response response = okHttpClient.newCall(request).execute();
      return response;
    } catch (IOException e) {
      System.out.println("http error");
      return null;
    }
  }


  private String getProxy() {
    String apiUrl = "http://v2.api.juliangip.com/dynamic/getips?auto_white=1&filter=1&num=1&pt=1&result_type=json2&trade_no=1134776592803734&sign=c38c41d94de08c2fe8cfebd8faa6196e";
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder()
        .url(apiUrl)
        .build();
    Response response = null;
    try {
      response = okHttpClient.newCall(request).execute();
      String jsonString = response.body().string();
      return jsonString;
    } catch (IOException e) {
      System.out.println("get proxy error");
      throw new RuntimeException(e);
    }
  }
}

