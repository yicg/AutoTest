package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.Logincase;
import com.course.model.User;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class loginTest {
    @BeforeTest(groups = "loginTrue",description = "测试准备工作")
    public void beforeLogin(){
        TestConfig.getuserinfoUrl=ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getuserlistUrl=ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.adduserUrl=ConfigFile.getUrl(InterfaceName.ADDUSER);
        TestConfig.loginUrl=ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateuserinfoUrl=ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.defaultHttpClient=new DefaultHttpClient();
    }
    @Test(groups = "loginTrue",description = "用户登录成功接口")
    public void loginTrue() throws IOException {

        SqlSession session=DatabaseUtil.getSqlSession();
        Logincase logincase=session.selectOne("loginCase",1);
        System.out.println(logincase.toString());
        System.out.println(TestConfig.loginUrl);
        //第一步就是发送请求
        String result=getResult(logincase);
        //第二部就是验证结果
        Assert.assertEquals(logincase.getExpected(),result);
    }



    @Test(groups = "loginFalse",description = "登录失败的接口")
    public void loginFalse() throws IOException {
        SqlSession session=DatabaseUtil.getSqlSession();
        Logincase logincase=session.selectOne("loginCase",2);

        System.out.println(logincase.toString());
       System.out.println(TestConfig.loginUrl);
        //第一步就是发送请求
        String result=getResult(logincase);
        //第二部就是验证结果
        Assert.assertEquals(logincase.getExpected(),result);


    }

    private String getResult(Logincase logincase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.loginUrl);
        JSONObject param=new JSONObject();
        param.put("username",logincase.getUsername());
        param.put("password",logincase.getPassword());

        post.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        String result;
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        TestConfig.store=TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }
}
