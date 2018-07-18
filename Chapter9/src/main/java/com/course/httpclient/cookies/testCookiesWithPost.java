package com.course.httpclient.cookies;
/**
 * 运用httpClient框架练习一个需要携带cookies才能进行的post请求
 * 2018.07.17
 */

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

public class testCookiesWithPost {

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
        String resultUrl=this.url+uri;
        //测试逻辑代码书写
        HttpGet get=new HttpGet(resultUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        //定义一个变量来存储响应结果
        String result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        /**
         * 设置生成cookies
         * 重点
         */
        store=client.getCookieStore();
        List<Cookie> cookieList=store.getCookies();
        for(Cookie cookie:cookieList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookiesName="+name+";  cookiesValue="+value);
        }

    }
    @Test(dependsOnMethods = {"getCookies"})
    //需要依赖获取cookies的get方法
    public void postWithCookies() throws IOException {

        String uri=bundle.getString("test.post.with.cookies");
        String testUrl=this.url+uri;
        //书写逻辑测试代码
        //设置请求方式
        HttpPost post=new HttpPost(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();

        //设置请求头信息
        post.setHeader("content-type","application/json");
        //设置参数
        JSONObject param=new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");
        //发送Cookies信息
        client.setCookieStore(this.store);
        //把参数参入到方法中
        StringEntity entity=new StringEntity(param.toString());
        post.setEntity(entity);
        //设置响应信息
        HttpResponse response=client.execute(post);

        //定义一个变量存储响应信息
        String result=EntityUtils.toString(response.getEntity(),"utf-8");
        //输出响应值
        System.out.println(result);

        //把响应值字符串转化成json格式
        JSONObject jsonResult=new JSONObject(result);
        //判断比较响应结果预期和实际
        //输出实际值
        String actualValue= (String) jsonResult.get("huhansan");
        String actualStatus= (String) jsonResult.get("status");
        //比较预期结果和实际结果
        Assert.assertEquals("success",actualValue);
        Assert.assertEquals("1",actualStatus);
    }
}
