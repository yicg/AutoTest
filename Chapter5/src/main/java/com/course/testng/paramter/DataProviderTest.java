package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

public class DataProviderTest {
    @Test(dataProvider = "data")
    public void testDataProviderTest(String name,int age){
        System.out.println("name="+name+";age="+age);
    }
    @DataProvider(name="data")
    public Object[][] providerData(){

        Object[][] o=new Object[][]{
                {"zhangsan",10},
                {"lisi",20},
                {"wangwu",30}

        };
        return o;
    }
    @Test(dataProvider = "method")
    public void test1(String name,int age){
        System.out.println("test1方法name="+name+";age="+age);
    }
    @Test(dataProvider = "method")
    public void test2(String name,int age){
        System.out.println("test2方法name="+name+";age="+age);
    }

    @DataProvider(name = "method")
    public Object[][] methodDataTest(Method method){
        Object[][] objects=null;
        if(method.getName().equals("test1")){
            objects=new Object[][]{
                    {"zhaoliu",25},
                    {"liudai",27}
            };
        }else if (method.getName().equals("test2")){
            objects=new Object[][]{
                    {"lisi",10},
                    {"dalao",20}
            };
        }

        return objects;
    }
}
