package com.course.httpclient.cookies;
/**
 * 功能：实现一个产生Cookies的get请求
 * 时间：2018.07.09
 */

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

    private String url;
    private ResourceBundle bundle;
    //用来储存Cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        /**
         * 从配置文件中拼接字符串
         */
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");

        }
        @Test
        public void testGetCookies() throws IOException {

        String uri=bundle.getString("getCookies.uri");
        //最终字符串
        String testUrl=this.url+uri;

        //测试逻辑代码
        HttpGet get=new HttpGet(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        /**
         * 定义一个变量，用来储存响应数据
         */
        String result;
        result=EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println(result);

            /**
             * 获取cookies信息
             */

            this.store=client.getCookieStore();
            List<Cookie>cookieList=store.getCookies();
            //遍历泛型
            for (Cookie cookie:cookieList){
               String name=cookie.getName();
               String value=cookie.getValue();
               System.out.println("cookieName="+name+";  cookieValue="+cookie.getValue());
            }
    }
        //依赖testGetCookies运行
        @Test(dependsOnMethods = {"testGetCookies"})
        public void testGetWithCookie() throws IOException {
            /**
             * 拼接字符串
             */
            String uri=bundle.getString("getWithCookies.uri");
            //最终请求地址
            String testUrl=this.url+uri;
            //测试逻辑代码书写
            HttpGet get=new HttpGet(testUrl);
            DefaultHttpClient client=new DefaultHttpClient();

            /**
             * 设置cookies信息
             */
            client.setCookieStore(this.store);

            HttpResponse response=client.execute(get);
            //获取响应状态码
            int statusCode=response.getStatusLine().getStatusCode();
            System.out.println("statusCode="+statusCode);

            if(statusCode==200){
                String  result=EntityUtils.toString(response.getEntity());
                System.out.println(result);

            }
        }
}
