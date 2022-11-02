package com.ydsdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import okhttp3.OkHttpClient;

public class Demo {

    public static void main(String[] args) {
        try {
            //   参数分别是 apiUrlPre / appId / appSecret
            YdSdk ydSdk = new YdSdk("http://api.local.com/V4/", "xxxxxxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            //   参数分别是 apiUrlPre / appId / appSecret / userId， 其中 userId 在特殊场景下才会使用
            //YdSdk ydSdk = new YdSdk("http://api.local.com/V4/", "xxxxxxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "88350");
            // 支持自定义OkHttpClient
            //OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build();
            //YdSdk ydSdk = new YdSdk("http://api.local.com/V4/", "xxxxxxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "", okHttpClient);

            String api;
            JSONObject result;
            Map<String, Object> query = new HashMap<>();
            Map<String, String> headers = new HashMap<>();
            Map<String, Object> postData = new HashMap<>();

            api = "test.sdk.get";
            query.put("domain_id", "1");
            query.put("status", "2");
            query.put("name", "name名称");
            result = ydSdk.get(api, query);
            System.out.println(api + " get<api, query>: ");
            System.out.println(JSON.toJSONString(result) + "\n");


            Map<String, Object> extQuery = new HashMap<>();
            extQuery.put("bcd", "string");
            extQuery.put("abc", "000000");
            query.put("extquery", extQuery);
            query.put("domain_idsString", new String[]{"10", "22", "3", "44", "5", "16", "7"});
            query.put("domain_idsInt", new int[]{10, 22, 3, 44, 5, 16, 7});
            query.put("domain_idsDouble", new double[]{10.1, 22.2, 3.3, 44.0, 5.5, 16.1, 7.7});
            result = ydSdk.get(api, query);
            System.out.println(api + " get<api, query>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.get(api, query, headers);
            System.out.println(api + " get<api, query, headers>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            postData.clear();

            api = "test.sdk.post";
            postData.put("domain_id", "1");
            postData.put("status", "2");
            postData.put("name", "name名称");

            result = ydSdk.post(api, postData);
            System.out.println(api + " post<api, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.post(api, query, postData);
            System.out.println(api + " post<api, query, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.post(api, query, postData, headers);
            System.out.println(api + " post<api, query, postData, headers>: ");
            System.out.println(JSON.toJSONString(result) + "\n");


            postData.clear();

            api = "test.sdk.post";
            postData.put("domain_ids", new int[]{10, 22, 3, 44, 5, 16, 7});
            postData.put("status", "2");
            Map<String, Object> extData = new HashMap<>();
            extData.put("bcd", "string");
            extData.put("abc", "000000");
            extData.put("bac", 1);
            extData.put("acb", 100.0);
            postData.put("extdata", extData);

            result = ydSdk.post(api, postData);
            System.out.println(api + " post<api, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.post(api, query, postData);
            System.out.println(api + " post<api, query, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.post(api, query, postData, headers);
            System.out.println(api + " post<api, query, postData, headers>: ");
            System.out.println(JSON.toJSONString(result) + "\n");


            postData.clear();

            api = "test.sdk.patch";
            postData.put("domain_id", "1");
            postData.put("status", "2");
            postData.put("name", "name名称");

            result = ydSdk.patch(api, postData);
            System.out.println(api + " patch<api, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.patch(api, query, postData);
            System.out.println(api + " patch<api, query, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.patch(api, query, postData, headers);
            System.out.println(api + " patch<api, query, postData, headers>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            postData.clear();

            api = "test.sdk.put";
            postData.put("domain_id", "1");
            postData.put("status", "2");
            postData.put("name", "name名称");

            result = ydSdk.put(api, postData);
            System.out.println(api + " put<api, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.put(api, query, postData);
            System.out.println(api + " put<api, query, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.put(api, query, postData, headers);
            System.out.println(api + " put<api, query, postData, headers>: ");
            System.out.println(JSON.toJSONString(result) + "\n");


            postData.clear();

            api = "test.sdk.delete";
            postData.put("domain_id", "1");
            postData.put("status", "2");
            postData.put("name", "name名称");

            result = ydSdk.delete(api, postData);
            System.out.println(api + " delete<api, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.delete(api, query, postData);
            System.out.println(api + " delete<api, query, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.delete(api, query, postData, headers);
            System.out.println(api + " delete<api, query, postData, headers>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
