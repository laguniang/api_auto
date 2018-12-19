package com.lemon.api.auto;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: suchunlei
 * @create: 2018-12-19 10:50
 * @description:
 */
public class PostTester {
    @DataProvider
    public Object[][] datas() {
        Object[][] datas = ExcelUtils.readExcel("/api.xlsx");
//                {"13145326785", "123456", "tom1"},
//                {"1314532678", "123456", "tom2"},
//                {"13145326785", "123456u", "tom3"},
        return datas;
    }

    @Test(dataProvider = "datas")
    public void test1(String mobilephone,String pwd,String regname){
        String url = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("mobilephone",mobilephone);
        paramsMap.put("pwd",pwd);
        paramsMap.put("regname",regname);
//        List<NameValuePair> parameters = new ArrayList<>();
//        parameters.add(new BasicNameValuePair("mobilephone","13888889999"));
//        parameters.add(new BasicNameValuePair("pwd","123456"));
//        parameters.add(new BasicNameValuePair("regname","tom6&name=1"));

        String result = HttpUtils.post(url,paramsMap);
        System.out.println(result);

    }
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

        String result = HttpUtils.post(url, null);
        System.out.println(result);

    }
}
