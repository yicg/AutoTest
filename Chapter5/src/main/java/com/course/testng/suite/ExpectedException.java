package com.course.testng.suite;

import org.testng.annotations.Test;

public class ExpectedException {
/**
 * 什么时候回用到异常测试？
 * 在我们期望结果为摸一个异常的时候
 * 比如：我们传入了某些不合法的参数
 */

//这是一个试试会失败的异常测试
    @Test(expectedExceptions=RuntimeException.class)
    public void RunTimeExceptionsFile(){
        System.out.println("这是一个失败的异常测试");
    }

    //这是一个成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void RunTimeExceptionsSuccess(){
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();

    }
}
