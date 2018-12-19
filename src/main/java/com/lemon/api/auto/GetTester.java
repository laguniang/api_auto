package com.lemon.api.auto;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: suchunlei
 * @create: 2018-12-19 7:32
 * @description:
 */
public class GetTester {
    @Test
    public void f1() throws IOException {
        String url = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("mobilephone","13888889999");
        paramsMap.put("pwd","123456");
        paramsMap.put("regname","tom6666");
//        List<NameValuePair> parameters = new ArrayList<>();
//        parameters.add(new BasicNameValuePair("mobilephone","13888889999"));
//        parameters.add(new BasicNameValuePair("pwd","123456"));
//        parameters.add(new BasicNameValuePair("regname","tom6&name=1"));

        String result = HttpUtils.get(url, null);
        System.out.println(result);

    }
}
