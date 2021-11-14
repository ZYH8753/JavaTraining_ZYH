package com.zyh.javatraining.weektwo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Objects;

public class HttpClient {
    public static void main(String[] args) {
        testHttpClient();
//        testOKClient();
    }

    public static void testHttpClient() {
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        try (CloseableHttpResponse response = HttpClients.createDefault().execute(httpGet)) {
            System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testOKClient() {
        Request request = new Request.Builder().url("http://localhost:8801").build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            System.out.println(Objects.requireNonNull(response.body()).string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
