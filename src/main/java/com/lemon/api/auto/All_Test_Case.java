package com.lemon.api.auto;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: suchunlei
 * @create: 2018-12-22 16:37
 * @description:
 */
public class All_Test_Case {
        @DataProvider
        public Object[][] datas() {
            Object[][] datas = ExcelUtils.readExcel("/api_test_case_01.xlsx",2);
//                {"13145326785", "123456", "tom1"},
//                {"1314532678", "123456", "tom2"},
//                {"13145326785", "123456u", "tom3"},
            return datas;
        }

        @Test(dataProvider = "datas")
        //每个接口对应一个测试用例文档
        //所有测试用例放在一个eexcel中
        //url各不相同
        //各个接口参数不确定 给一个json字符串{"mobilephone","13146230254","pwd","123456"}
        public void test1(String caseId, String apiId, String requestData,String expectedReponseData) {
            String url = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
            Map<String,String> paramsMap = (Map<String, String>) JSONObject.parse(requestData);
//            Map<String, String> paramsMap = new HashMap<>();
//            paramsMap.put("mobilephone", mobilephone);
//            paramsMap.put("pwd", pwd);
//            paramsMap.put("regname", regname);
//        List<NameValuePair> parameters = new ArrayList<>();
//        parameters.add(new BasicNameValuePair("mobilephone","13888889999"));
//        parameters.add(new BasicNameValuePair("pwd","123456"));
//        parameters.add(new BasicNameValuePair("regname","tom6&name=1"));

            String result = HttpUtils.post(url, paramsMap);
            System.out.println(result);

        }

        @Test
        public void f1() throws IOException {
            //将字符串转换成对象
            //JSONObject.parse(...)
            //将对象转换成字符串
            //JSONObject.toJSONString（...)
            String url = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("mobilephone", "13888889999");
            paramsMap.put("pwd", "123456");
            paramsMap.put("regname", "tom6666");
//        List<NameValuePair> parameters = new ArrayList<>();
//        parameters.add(new BasicNameValuePair("mobilephone","13888889999"));
//        parameters.add(new BasicNameValuePair("pwd","123456"));
//        parameters.add(new BasicNameValuePair("regname","tom6&name=1"));

            String result = HttpUtils.post(url, null);
            System.out.println(result);

        }
    }

