package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMethod {
    @Test(groups = "server")
    public void test1(){
        System.out.println("这是服务端的测试方法111");
    }
    @Test(groups = "server")
    public void test2(){
        System.out.println("这是服务端的测试方法222");
    }
    @Test(groups = "client")
    public void test3(){
        System.out.println("这是客户端端的测试方法3333");
    }
    @Test(groups = "client")
    public void test4(){
        System.out.println("这是客户端的测试方法444");
    }
    @BeforeGroups("server")
    public void beforeGroupsOnserver(){
        System.out.println("这是服务端组运行之前的方法");
    }
    @AfterGroups("server")
    public void afterGroupsOnserver(){
        System.out.println("这是服务端组运行之后的方法");
    }
}
