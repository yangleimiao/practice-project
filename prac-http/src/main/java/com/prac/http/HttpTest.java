package com.prac.http;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

/**
 * @author yanglei
 * @date 2021/10/11
 */
public class HttpTest {
    static OkHttpClient okHttpClient = new OkHttpClient();

    public static void main(String[] args) {
        //test();
        test2();
    }

    public static void test() {
        String urlGet = "http://localhost:8081/list?claId=1";
        Request request = new Request.Builder().url(urlGet)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        // 同步
        // Response response = call.execute();
        // 异步
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure:"+e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse:"+response.body().string());
            }
        });
    }
    public static void test2(){
        String urlPost = "http://localhost:8081/update";
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject json = new JSONObject();
        json.put("stuId",1);
        json.put("stuName","ab123");
        json.put("claId",2);
        RequestBody requestBody = RequestBody.create(mediaType, json.toJSONString());
        Request request = new Request.Builder()
                .url(urlPost)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure:"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.code()+","+response.message());
                System.out.println("onResponse:"+response.body().string());
            }
        });

    }

}
