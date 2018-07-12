package com.course.httpclient.cookies;

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
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class testCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    @BeforeTest
    public void beforeTest(){
        bundle=ResourceBundle.getBundle("application",Locale.CHINA);
        url=bundle.getString("test.url");
    }
    @Test
    public void getCookies() throws IOException {
        /**
         * 拼接最终字符串
         */
        String uri=bundle.getString("getCookies.uri");
        String testUrl=this.url+uri;
        //测试逻辑代码书写
        HttpGet get=new HttpGet(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        //定义一个变量，用来储存响应值
        String result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //设置Cookies信息
        this.store=client.getCookieStore();
        //遍历cookie值
        List<Cookie> cookieList=store.getCookies();
        for(Cookie cookie:cookieList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookiesName="+name+";  cookiesValue="+value);
        }
    }
    @Test(dependsOnMethods = {"getCookies"})
    public void myCookiesForPost() throws IOException {
        String uri=bundle.getString("test.post.with.cookies");
        String testUrl=this.url+uri;
        //定义一个请求方式
        HttpPost post=new HttpPost(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        //定义参数
        JSONObject param=new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");
        //设置请求header
        post.setHeader("conent-type","application/json");
        //把参数放到方法中
        StringEntity entity=new StringEntity(param.toString());
        post.setEntity(entity);
        //设置cookies
        client.setCookieStore(this.store);
        //设置变量存储响应值
        String result;
        //响应结果
        HttpResponse response=client.execute(post);
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //把结果转换成json格式
        JSONObject jsonResult =new JSONObject(result);
        String ActualResult= (String) jsonResult.get("huhansan");
        String ActualStatus= (String) jsonResult.get("status");
        /**
         * 比较预期和实际结果
         * 第一个值是期望结果，第二个值是实际结果
         */
        Assert.assertEquals("success",ActualResult);
        Assert.assertEquals("1",ActualStatus);
    }
}
