package com.demo.interceptor;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import sun.net.www.http.HttpClient;

import java.io.IOException;

/**
 * @author yanglei
 * @date 2021/11/1
 */
public class InterceptorExample {
    public static void main(String[] args) {

        HttpRequestInterceptor requestInterceptor = new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
                // 如果没有token
                if (!httpRequest.containsHeader("token")){


                }

            }
        };
        CloseableHttpClient httpClient = HttpClients.custom().addInterceptorFirst(requestInterceptor).build();


    }
}
