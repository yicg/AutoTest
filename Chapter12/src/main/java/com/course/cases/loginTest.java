package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.Logincase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
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
    }
    @Test(groups = "loginFalse",description = "登录失败的接口")
    public void loginFalse() throws IOException {
        SqlSession session=DatabaseUtil.getSqlSession();
        Logincase logincase=session.selectOne("loginCase",2);

        System.out.println(logincase.toString());
       System.out.println(TestConfig.loginUrl);

    }
}
