package com.lemon.api.auto;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author: suchunlei
 * @create: 2018-12-19 8:52
 * @description:
 */
public class HttpUtils {
    /**
     * 写一个方法，发get请求
     * 登陆接口，注册接口，充值接口，提现接口......
     *
     * @return
     */
    public static String get(String url, Map<String, String> paramsMap) {
        try {
            List<NameValuePair> parameters = null;
            if (paramsMap!=null) {
                paramsMap = new HashMap<>();
                //遍历Map取出所有键值对
                Set<String> keySet = paramsMap.keySet();
                for (String name : keySet) {
                    String value = paramsMap.get(name);
                    parameters.add(new BasicNameValuePair(name, value));
                }
                String encodeParams = URLEncodedUtils.format(parameters, "utf-8");
                url += ("?" + encodeParams);
            }
            HttpGet get = new HttpGet(url);
            //创建发包客户端
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //发数据包，获得响应
            CloseableHttpResponse response = httpClient.execute(get);
            //获得响应体
            HttpEntity respEntity = response.getEntity();
            return EntityUtils.toString(respEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String post(String url, Map<String, String> paramsMap) {
        //生成post请求
        try {
            HttpPost post = new HttpPost(url);
            List<NameValuePair> parameters = null;
            if(paramsMap!=null) {
                parameters = new ArrayList<>();
                //遍历Map取出所有键值对
                Set<String> keySet = paramsMap.keySet();
                for (String name : keySet) {
                    String value = paramsMap.get(name);
                    parameters.add(new BasicNameValuePair(name, value));
                }

                //创建一个原生form表单的请求体
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters);
                //设置post请求请求体
                post.setEntity(entity);
            }
            //发数据包http发包客户端，创建一个客户端
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //发数据包
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity respEntity = response.getEntity();
            return EntityUtils.toString(respEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

         return "";
    }
}
