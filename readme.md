# 云盾 api sdk for Java

### 说明

* 接口基地址： 'https://api.local.com/V4/';
* 接口遵循RESTful,默认请求体json,接口默认返回json
* app_id, app_secret 联系技术客服，先注册一个云盾的账号，用于申请绑定api身份

### 签名算法

* 每次请求都签名，保证传输过程数据不被篡改
* 客户端：sha256签名算法，将参数base64编码+app_secret用sha256签名，每次请求带上签名
* 服务端：拿到参数用相同的算法签名，对比签名是否正确

### sdk 使用说明

* 环境：java >=1.8
* 支持get/post/patch/put/delete方法
* 参数说明
    * appId 分配的app_id
    * appSecert 分配的appSecert, 用于签名数据
    * apiUrlPre api地址前缀
    * userId 当前使用者的用户ID
* 每次调用会返回JSONObject, 如果执行过程中有异常，会直接抛出异常；
* 如果需要调试，可以调用debug方法
* 注意事项
    针对所有请求，uri与get参数是分离的，如 https://apiv4.local.com/V4/version?v=1, 调用时v=1参数，须通过query传递
    JSONObject result = ydSdk.get(api, query, headers);


### 使用

#### 示例类
```
package com.ydsdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.slf4j.LoggerFactory;

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

            result = ydSdk.delete(api, postData);
            System.out.println(api + " delete<api, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.delete(api, query, postData);
            System.out.println(api + " delete<api, query, postData>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

            result = ydSdk.delete(api, query, postData, headers);
            System.out.println(api + " delete<api, query, postData, headers>: ");
            System.out.println(JSON.toJSONString(result) + "\n");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### 示例类执行输出
···
2022-08-06 16:48:34.981 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804514","user_id":""},{},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"qGgCPPQDnpWYh-yYW50qvI05P3OEq0hDveSZPMfA3zI="}]
2022-08-06 16:48:34.990 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804514","user_id":""}
2022-08-06 16:48:34.990 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {}
2022-08-06 16:48:34.991 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"qGgCPPQDnpWYh-yYW50qvI05P3OEq0hDveSZPMfA3zI=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:35.011 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.get?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804514&user_id=
2022-08-06 16:48:35.012 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: GET
2022-08-06 16:48:35.294 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","api_time_consuming":"165.04406929016ms","function_time_consuming":"1.1141300201416ms","dispatch_before_time_consuming":"108.64901542664ms"},"data":[]}
test.sdk.get get<api, query>:
{"code":1,"data":[],"message":"操作成功","status":{"api_time_consuming":"165.04406929016ms","function_time_consuming":"1.1141300201416ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","dispatch_before_time_consuming":"108.64901542664ms"}}

2022-08-06 16:48:35.305 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""},{},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"P_fIvOynHrBug69J1pNFYlhtDu4t3vJLUnnVvR-eyso="}]
2022-08-06 16:48:35.306 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""}
2022-08-06 16:48:35.306 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {}
2022-08-06 16:48:35.306 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"P_fIvOynHrBug69J1pNFYlhtDu4t3vJLUnnVvR-eyso=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:35.307 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.get?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804515&user_id=
2022-08-06 16:48:35.307 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: GET
2022-08-06 16:48:35.474 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","api_time_consuming":"157.08112716675ms","function_time_consuming":"0.70786476135254ms","dispatch_before_time_consuming":"105.91816902161ms"},"data":[]}
test.sdk.get get<api, query, headers>:
{"code":1,"data":[],"message":"操作成功","status":{"api_time_consuming":"157.08112716675ms","function_time_consuming":"0.70786476135254ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","dispatch_before_time_consuming":"105.91816902161ms"}}

2022-08-06 16:48:35.476 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804515","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"rcfSO3iIEgG8T5Y0Pz2YRHQ5cl3pkPWrV5b-Cum7QxA="}]
2022-08-06 16:48:35.476 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {}
2022-08-06 16:48:35.477 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804515","status":"2","user_id":""}
2022-08-06 16:48:35.477 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"rcfSO3iIEgG8T5Y0Pz2YRHQ5cl3pkPWrV5b-Cum7QxA=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:35.477 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.post?
2022-08-06 16:48:35.478 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: POST
2022-08-06 16:48:35.650 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","api_time_consuming":"161.44204139709ms","function_time_consuming":"0.77509880065918ms","dispatch_before_time_consuming":"100.68607330322ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.post post<api, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"161.44204139709ms","function_time_consuming":"0.77509880065918ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","dispatch_before_time_consuming":"100.68607330322ms"}}

2022-08-06 16:48:35.653 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804515","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"rcfSO3iIEgG8T5Y0Pz2YRHQ5cl3pkPWrV5b-Cum7QxA="}]
2022-08-06 16:48:35.654 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""}
2022-08-06 16:48:35.654 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804515","status":"2","user_id":""}
2022-08-06 16:48:35.654 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"rcfSO3iIEgG8T5Y0Pz2YRHQ5cl3pkPWrV5b-Cum7QxA=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:35.655 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.post?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804515&user_id=
2022-08-06 16:48:35.655 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: POST
2022-08-06 16:48:35.819 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","api_time_consuming":"153.99789810181ms","function_time_consuming":"1.0318756103516ms","dispatch_before_time_consuming":"97.299814224243ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.post post<api, query, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"153.99789810181ms","function_time_consuming":"1.0318756103516ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","dispatch_before_time_consuming":"97.299814224243ms"}}

2022-08-06 16:48:35.821 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804515","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"rcfSO3iIEgG8T5Y0Pz2YRHQ5cl3pkPWrV5b-Cum7QxA="}]
2022-08-06 16:48:35.821 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""}
2022-08-06 16:48:35.822 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804515","status":"2","user_id":""}
2022-08-06 16:48:35.822 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"rcfSO3iIEgG8T5Y0Pz2YRHQ5cl3pkPWrV5b-Cum7QxA=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:35.823 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.post?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804515&user_id=
2022-08-06 16:48:35.823 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: POST
2022-08-06 16:48:35.984 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","api_time_consuming":"149.50394630432ms","function_time_consuming":"0.8089542388916ms","dispatch_before_time_consuming":"98.716974258423ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.post post<api, query, postData, headers>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"149.50394630432ms","function_time_consuming":"0.8089542388916ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","dispatch_before_time_consuming":"98.716974258423ms"}}

2022-08-06 16:48:36.028 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_ids":[10,22,3,44,5,16,7],"extdata":{"acb":100.0,"bcd":"string","abc":"000000","bac":1},"issued_at":"1659804515","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"tVEs-zDngqzMNI5m5Jct8d2YnZuiWU54AEnkKwTORh4="}]
2022-08-06 16:48:36.029 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {}
2022-08-06 16:48:36.029 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_ids":[10,22,3,44,5,16,7],"extdata":{"acb":100.0,"bcd":"string","abc":"000000","bac":1},"issued_at":"1659804515","status":"2","user_id":""}
2022-08-06 16:48:36.030 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"tVEs-zDngqzMNI5m5Jct8d2YnZuiWU54AEnkKwTORh4=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:36.030 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.post?
2022-08-06 16:48:36.030 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: POST
2022-08-06 16:48:36.187 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","api_time_consuming":"147.32599258423ms","function_time_consuming":"0.77486038208008ms","dispatch_before_time_consuming":"96.341133117676ms"},"data":{"domain_ids":[10,22,3,44,5,16,7],"extdata":{"acb":100,"bcd":"string","abc":"000000","bac":1},"status":"2"}}
test.sdk.post post<api, postData>:
{"code":1,"data":{"extdata":{"acb":100,"bcd":"string","abc":"000000","bac":1},"domain_ids":[10,22,3,44,5,16,7],"status":"2"},"message":"操作成功","status":{"api_time_consuming":"147.32599258423ms","function_time_consuming":"0.77486038208008ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","dispatch_before_time_consuming":"96.341133117676ms"}}

2022-08-06 16:48:36.190 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_ids":[10,22,3,44,5,16,7],"extdata":{"acb":100.0,"bcd":"string","abc":"000000","bac":1},"issued_at":"1659804516","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"ztw80YnB9Htxcht8R7rqpdZsJGGbGdNCU7gldvm2Q14="}]
2022-08-06 16:48:36.191 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""}
2022-08-06 16:48:36.192 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_ids":[10,22,3,44,5,16,7],"extdata":{"acb":100.0,"bcd":"string","abc":"000000","bac":1},"issued_at":"1659804516","status":"2","user_id":""}
2022-08-06 16:48:36.192 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"ztw80YnB9Htxcht8R7rqpdZsJGGbGdNCU7gldvm2Q14=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:36.193 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.post?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804515&user_id=
2022-08-06 16:48:36.193 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: POST
2022-08-06 16:48:36.360 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","api_time_consuming":"155.82489967346ms","function_time_consuming":"0.77199935913086ms","dispatch_before_time_consuming":"105.22484779358ms"},"data":{"domain_ids":[10,22,3,44,5,16,7],"extdata":{"acb":100,"bcd":"string","abc":"000000","bac":1},"status":"2"}}
test.sdk.post post<api, query, postData>:
{"code":1,"data":{"extdata":{"acb":100,"bcd":"string","abc":"000000","bac":1},"domain_ids":[10,22,3,44,5,16,7],"status":"2"},"message":"操作成功","status":{"api_time_consuming":"155.82489967346ms","function_time_consuming":"0.77199935913086ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","dispatch_before_time_consuming":"105.22484779358ms"}}

2022-08-06 16:48:36.363 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_ids":[10,22,3,44,5,16,7],"extdata":{"acb":100.0,"bcd":"string","abc":"000000","bac":1},"issued_at":"1659804516","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"ztw80YnB9Htxcht8R7rqpdZsJGGbGdNCU7gldvm2Q14="}]
2022-08-06 16:48:36.366 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""}
2022-08-06 16:48:36.366 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_ids":[10,22,3,44,5,16,7],"extdata":{"acb":100.0,"bcd":"string","abc":"000000","bac":1},"issued_at":"1659804516","status":"2","user_id":""}
2022-08-06 16:48:36.366 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"ztw80YnB9Htxcht8R7rqpdZsJGGbGdNCU7gldvm2Q14=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:36.367 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.post?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804515&user_id=
2022-08-06 16:48:36.367 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: POST
2022-08-06 16:48:36.529 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","api_time_consuming":"153.20301055908ms","function_time_consuming":"0.77295303344727ms","dispatch_before_time_consuming":"101.80521011353ms"},"data":{"domain_ids":[10,22,3,44,5,16,7],"extdata":{"acb":100,"bcd":"string","abc":"000000","bac":1},"status":"2"}}
test.sdk.post post<api, query, postData, headers>:
{"code":1,"data":{"extdata":{"acb":100,"bcd":"string","abc":"000000","bac":1},"domain_ids":[10,22,3,44,5,16,7],"status":"2"},"message":"操作成功","status":{"api_time_consuming":"153.20301055908ms","function_time_consuming":"0.77295303344727ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","dispatch_before_time_consuming":"101.80521011353ms"}}

2022-08-06 16:48:36.531 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804516","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"oF35Mmnno-ak227QsF4Q-vx8UA0YQoI37wX6-MQjBDs="}]
2022-08-06 16:48:36.532 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {}
2022-08-06 16:48:36.532 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804516","status":"2","user_id":""}
2022-08-06 16:48:36.532 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"oF35Mmnno-ak227QsF4Q-vx8UA0YQoI37wX6-MQjBDs=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:36.533 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.patch?
2022-08-06 16:48:36.533 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: PATCH
2022-08-06 16:48:36.688 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","api_time_consuming":"145.82991600037ms","function_time_consuming":"0.73599815368652ms","dispatch_before_time_consuming":"96.963882446289ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.patch patch<api, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"145.82991600037ms","function_time_consuming":"0.73599815368652ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","dispatch_before_time_consuming":"96.963882446289ms"}}

2022-08-06 16:48:36.690 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804516","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"oF35Mmnno-ak227QsF4Q-vx8UA0YQoI37wX6-MQjBDs="}]
2022-08-06 16:48:36.691 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""}
2022-08-06 16:48:36.691 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804516","status":"2","user_id":""}
2022-08-06 16:48:36.691 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"oF35Mmnno-ak227QsF4Q-vx8UA0YQoI37wX6-MQjBDs=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:36.692 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.patch?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804515&user_id=
2022-08-06 16:48:36.692 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: PATCH
2022-08-06 16:48:36.865 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","api_time_consuming":"163.81096839905ms","function_time_consuming":"0.78010559082031ms","dispatch_before_time_consuming":"100.40092468262ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.patch patch<api, query, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"163.81096839905ms","function_time_consuming":"0.78010559082031ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","dispatch_before_time_consuming":"100.40092468262ms"}}

2022-08-06 16:48:36.871 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804516","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"oF35Mmnno-ak227QsF4Q-vx8UA0YQoI37wX6-MQjBDs="}]
2022-08-06 16:48:36.871 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""}
2022-08-06 16:48:36.871 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804516","status":"2","user_id":""}
2022-08-06 16:48:36.872 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"oF35Mmnno-ak227QsF4Q-vx8UA0YQoI37wX6-MQjBDs=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:36.872 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.patch?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804515&user_id=
2022-08-06 16:48:36.872 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: PATCH
2022-08-06 16:48:37.034 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","api_time_consuming":"152.69708633423ms","function_time_consuming":"0.76508522033691ms","dispatch_before_time_consuming":"104.19201850891ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.patch patch<api, query, postData, headers>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"152.69708633423ms","function_time_consuming":"0.76508522033691ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"104.19201850891ms"}}

2022-08-06 16:48:37.036 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM="}]
2022-08-06 16:48:37.037 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {}
2022-08-06 16:48:37.037 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""}
2022-08-06 16:48:37.037 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:37.038 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.put?
2022-08-06 16:48:37.038 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: PUT
2022-08-06 16:48:37.195 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","api_time_consuming":"148.84400367737ms","function_time_consuming":"0.68807601928711ms","dispatch_before_time_consuming":"99.58291053772ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.put put<api, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"148.84400367737ms","function_time_consuming":"0.68807601928711ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"99.58291053772ms"}}

2022-08-06 16:48:37.197 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM="}]
2022-08-06 16:48:37.198 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""}
2022-08-06 16:48:37.198 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""}
2022-08-06 16:48:37.198 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:37.199 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.put?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804515&user_id=
2022-08-06 16:48:37.199 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: PUT
2022-08-06 16:48:37.355 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","api_time_consuming":"146.74687385559ms","function_time_consuming":"0.6721019744873ms","dispatch_before_time_consuming":"98.330974578857ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.put put<api, query, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"146.74687385559ms","function_time_consuming":"0.6721019744873ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"98.330974578857ms"}}

2022-08-06 16:48:37.357 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM="}]
2022-08-06 16:48:37.357 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""}
2022-08-06 16:48:37.358 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""}
2022-08-06 16:48:37.358 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:37.359 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.put?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804515&user_id=
2022-08-06 16:48:37.359 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: PUT
2022-08-06 16:48:37.512 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","api_time_consuming":"144.09613609314ms","function_time_consuming":"0.76603889465332ms","dispatch_before_time_consuming":"96.315145492554ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.put put<api, query, postData, headers>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"144.09613609314ms","function_time_consuming":"0.76603889465332ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"96.315145492554ms"}}

2022-08-06 16:48:37.514 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM="}]
2022-08-06 16:48:37.515 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {}
2022-08-06 16:48:37.515 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""}
2022-08-06 16:48:37.515 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:37.515 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.delete?
2022-08-06 16:48:37.516 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: DELETE
2022-08-06 16:48:37.675 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","api_time_consuming":"151.07297897339ms","function_time_consuming":"0.90479850769043ms","dispatch_before_time_consuming":"97.621917724609ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.delete delete<api, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"151.07297897339ms","function_time_consuming":"0.90479850769043ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"97.621917724609ms"}}

2022-08-06 16:48:37.679 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM="}]
2022-08-06 16:48:37.679 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""}
2022-08-06 16:48:37.687 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""}
2022-08-06 16:48:37.687 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:37.688 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.delete?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804515&user_id=
2022-08-06 16:48:37.688 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: DELETE
2022-08-06 16:48:37.844 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","api_time_consuming":"144.84214782715ms","function_time_consuming":"0.73599815368652ms","dispatch_before_time_consuming":"94.879150390625ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.delete delete<api, query, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"144.84214782715ms","function_time_consuming":"0.73599815368652ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"94.879150390625ms"}}

2022-08-06 16:48:37.846 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:149) - payload: [{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""},{"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""},{"Content-Type":"application/json; charset=utf-8","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","X-Auth-Sdk-Version":"1.0.3","X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM="}]
2022-08-06 16:48:37.846 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:150) - query: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","issued_at":"1659804515","user_id":""}
2022-08-06 16:48:37.846 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:151) - post: {"algorithm":"HMAC-SHA256","client_ip":"","client_userAgent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","domain_id":"1","issued_at":"1659804517","status":"2","user_id":""}
2022-08-06 16:48:37.846 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:152) - headers: {"X-Auth-Sign":"pfJyK1W18Ws1OVo_FwFX0VtU-gyttsG0Uaw0WgCoPcM=","X-Auth-App-Id":"xxxxxxxxxxxxxxxxxxxx","User-Agent":"'YdSdk 0.1; java/1.8.0_242; Linux 3.10.0-1127.el7.x86_64","X-Auth-Sdk-Version":"1.0.3","Content-Type":"application/json; charset=utf-8"}
2022-08-06 16:48:37.847 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:159) - url: http://api.local.com/V4/test.sdk.delete?algorithm=HMAC-SHA256&client_ip=&client_userAgent=%27YdSdk+0.1%3B+java%2F1.8.0_242%3B+Linux+3.10.0-1127.el7.x86_64&issued_at=1659804515&user_id=
2022-08-06 16:48:37.847 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:160) - method: DELETE
2022-08-06 16:48:38.008 [main] DEBUG com.ydsdk.YdSdk (YdSdk.java:195) - response raw body: {"status":{"code":1,"message":"操作成功","create_at":"2022-08-07 00:48:38","api_time_consuming":"152.095079422ms","function_time_consuming":"0.82111358642578ms","dispatch_before_time_consuming":"99.290132522583ms"},"data":{"domain_id":"1","status":"2"}}
test.sdk.delete delete<api, query, postData, headers>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"152.095079422ms","function_time_consuming":"0.82111358642578ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:38","dispatch_before_time_consuming":"99.290132522583ms"}}
···

### 更新日志

* 2022.08.05 

重构java版SDK

* 2022.08.07

1. 接入标准化的日志
2. 增加方法重载
3. 不再自动填充客户机IP
4. 修正get请求二维参数签名的bug
