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
            // 注意：业务实际使用时请将环境变量替换成具体的值
            //   参数分别是 apiUrlPre / appId / appSecret
            YdSdk ydSdk = new YdSdk(System.getenv("YDSDK_API_PRE"), System.getenv("YDSDK_API_ID"), System.getenv("YDSDK_APP_SECERT"));
            //   参数分别是 apiUrlPre / appId / appSecret / userId， 其中 userId 在特殊场景下才会使用
            //YdSdk ydSdk = new YdSdk(System.getenv("YDSDK_API_PRE"), System.getenv("YDSDK_API_ID"), System.getenv("YDSDK_APP_SECERT"), "88350");
            // 支持自定义OkHttpClient
            //OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build();
            //YdSdk ydSdk = new YdSdk(System.getenv("YDSDK_API_PRE"), System.getenv("YDSDK_API_ID"), System.getenv("YDSDK_APP_SECERT"), "", okHttpClient);

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
test.sdk.get get<api, query>:
{"code":1,"data":[],"message":"操作成功","status":{"api_time_consuming":"165.04406929016ms","function_time_consuming":"1.1141300201416ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","dispatch_before_time_consuming":"108.64901542664ms"}}

test.sdk.get get<api, query, headers>:
{"code":1,"data":[],"message":"操作成功","status":{"api_time_consuming":"157.08112716675ms","function_time_consuming":"0.70786476135254ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","dispatch_before_time_consuming":"105.91816902161ms"}}

test.sdk.post post<api, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"161.44204139709ms","function_time_consuming":"0.77509880065918ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","dispatch_before_time_consuming":"100.68607330322ms"}}

test.sdk.post post<api, query, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"153.99789810181ms","function_time_consuming":"1.0318756103516ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","dispatch_before_time_consuming":"97.299814224243ms"}}

test.sdk.post post<api, query, postData, headers>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"149.50394630432ms","function_time_consuming":"0.8089542388916ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:35","dispatch_before_time_consuming":"98.716974258423ms"}}

test.sdk.post post<api, postData>:
{"code":1,"data":{"extdata":{"acb":100,"bcd":"string","abc":"000000","bac":1},"domain_ids":[10,22,3,44,5,16,7],"status":"2"},"message":"操作成功","status":{"api_time_consuming":"147.32599258423ms","function_time_consuming":"0.77486038208008ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","dispatch_before_time_consuming":"96.341133117676ms"}}

test.sdk.post post<api, query, postData>:
{"code":1,"data":{"extdata":{"acb":100,"bcd":"string","abc":"000000","bac":1},"domain_ids":[10,22,3,44,5,16,7],"status":"2"},"message":"操作成功","status":{"api_time_consuming":"155.82489967346ms","function_time_consuming":"0.77199935913086ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","dispatch_before_time_consuming":"105.22484779358ms"}}

test.sdk.post post<api, query, postData, headers>:
{"code":1,"data":{"extdata":{"acb":100,"bcd":"string","abc":"000000","bac":1},"domain_ids":[10,22,3,44,5,16,7],"status":"2"},"message":"操作成功","status":{"api_time_consuming":"153.20301055908ms","function_time_consuming":"0.77295303344727ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","dispatch_before_time_consuming":"101.80521011353ms"}}

test.sdk.patch patch<api, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"145.82991600037ms","function_time_consuming":"0.73599815368652ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","dispatch_before_time_consuming":"96.963882446289ms"}}

test.sdk.patch patch<api, query, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"163.81096839905ms","function_time_consuming":"0.78010559082031ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:36","dispatch_before_time_consuming":"100.40092468262ms"}}

test.sdk.patch patch<api, query, postData, headers>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"152.69708633423ms","function_time_consuming":"0.76508522033691ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"104.19201850891ms"}}

test.sdk.put put<api, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"148.84400367737ms","function_time_consuming":"0.68807601928711ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"99.58291053772ms"}}

test.sdk.put put<api, query, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"146.74687385559ms","function_time_consuming":"0.6721019744873ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"98.330974578857ms"}}

test.sdk.put put<api, query, postData, headers>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"144.09613609314ms","function_time_consuming":"0.76603889465332ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"96.315145492554ms"}}

test.sdk.delete delete<api, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"151.07297897339ms","function_time_consuming":"0.90479850769043ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"97.621917724609ms"}}

test.sdk.delete delete<api, query, postData>:
{"code":1,"data":{"domain_id":"1","status":"2"},"message":"操作成功","status":{"api_time_consuming":"144.84214782715ms","function_time_consuming":"0.73599815368652ms","code":1,"message":"操作成功","create_at":"2022-08-07 00:48:37","dispatch_before_time_consuming":"94.879150390625ms"}}

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

* 2023.01.11

1. 单元测试增加特殊字符
2. GET请求修正参数乱序导致的签名失败问题
3. JSON编码使用UTF8编码，已解决部分场景下签名异常问题
4. DEMO及示例代码使用环境变量
