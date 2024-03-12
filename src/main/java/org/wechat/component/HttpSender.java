package org.wechat.component;


import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpSender {

  public String doGet(String url) throws IOException {

    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder().url(url).build();
    Response response = okHttpClient.newCall(request).execute();
    return response.body().string();
  }

  public void doPost(String url, String json) throws IOException {
    OkHttpClient okHttpClient = new OkHttpClient();
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url(url)
        .post(body)
        .build();
    Response response = okHttpClient.newCall(request).execute();
  }
}
