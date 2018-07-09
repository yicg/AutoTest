package com.course.httpclient.cookies;
/**
 * 功能：实现一个携带cookies的post请求
 * 时间：2018.07.09
 */

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void boforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetWithCookies() throws IOException {
        //拼接字符串
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;

        //测试逻辑代码编写
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        //定义一个变量储存响应结果
        String result = EntityUtils.toString(response.getEntity());

        //设置Cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        //遍历泛型
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookiesName=" + name + ";  cookiesValue=" + value);
        }
    }
    @Test(dependsOnMethods = {"testGetWithCookies"})
    public void testPostWithCookies() throws IOException {
        String uri=bundle.getString("test.post.with.cookies");
        //拼接最终的测试地址
        String testurl=this.url+uri;
        //声明一个client对象，用来进行方法执行
        DefaultHttpClient client=new DefaultHttpClient();
        //声明一个方法，这个方法就是post请求
        HttpPost post=new HttpPost(testurl);

        //添加参数
        JSONObject param=new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");

        //设置请求头信息,设置header
        post.setHeader("content-type","application/json");

        //将参数添加到方法中
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //声明一个对象来进行响应结果储存
        String result;

        //设置Cookies信息
        client.setCookieStore(this.store);

        //执行post方法
        HttpResponse response=client.execute(post);
        //获取响应结果
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //处理结果，就是判断返回结果是否符合预期
        //将返回的响应字符串结果转化成json对象
        JSONObject resultJson=new JSONObject(result);

        //获取到结果值
        String success= (String) resultJson.get("huhansan");
        String status= (String) resultJson.get("status");
        //具体的判断返回结果的值
        //第一个值是期望结果，第二个是实际结果
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);

    }
}

