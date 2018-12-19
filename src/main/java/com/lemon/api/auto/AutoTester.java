package com.lemon.api.auto;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: suchunlei
 * @create: 2018-12-18 22:47
 * @description:
 */
public class AutoTester {
    @DataProvider
    public Object[][] login_data(){
        Object[][] datas = {
                {"13825161923","e10adc3949ba59abbe56e057f20f883e","1","登陆成功"},
                {"1382516192","e10adc3949ba59abbe56e057f20f883e","1","信息错误"},
                {"13825161923","e10adc3949ba59abbe56e057f20f88e","1","信息错误"},
        };
        return datas;
    }
    @Test(dataProvider = "login_data")
    public void post(String mobilephone,String password,String type,String partRrsult) throws IOException {
        /**
         * 1.指定请求方法post
         * 2.指定url：http://39.108.136.60:8085/lmcanon_web_auto/mvc/member/api/member/login
         * 3.参数：mobilephone=13825161923&password=e10adc3949ba59abbe56e057f20f883e&type=1
         * 4.发数据包
         * 5. 查看响应
         */
        //1.请求地址
        String url = "http://39.108.136.60:8085/lmcanon_web_auto/mvc/member/api/member/login";
        //2.生成post请求--请求体中间
        //3.创建一个容器，保存每个参数
        HttpPost post = new HttpPost(url);
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("mobilephone",mobilephone));
        parameters.add(new BasicNameValuePair("password",password));
        parameters.add(new BasicNameValuePair("type",type));
        //创建一个原生form表单的请求体
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters);
        //设置post请求请求体
        post.setEntity(entity);
        //发数据包http发包客户端，创建一个客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //发数据包
        CloseableHttpResponse response = httpClient.execute(post);
        //获得响应，关注具体信息
        //1.响应行
        StatusLine line = response.getStatusLine();
        System.out.println(line.getProtocolVersion());
        System.out.println(line.getReasonPhrase());
        System.out.println(line.getStatusCode());
        Assert.assertEquals(line.getStatusCode(),200);
        //2.响应头
        Header[] headers = response.getAllHeaders();
        for (Header header : headers) {
            System.out.println(header.getName()+":"+header.getValue());
        }
        //3.响应体
        HttpEntity resEntity = response.getEntity();
        String resResult = EntityUtils.toString(resEntity);
        System.out.println(resResult);
        Assert.assertTrue(!resResult.contains(partRrsult));
    }
}
