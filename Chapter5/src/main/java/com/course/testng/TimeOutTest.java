package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {
    @Test(timeOut = 3000)
    public void TimeOutCase() throws InterruptedException {
        Thread.sleep(2000);
    }
    @Test(timeOut = 3000)
    public void TimeOutFailCase() throws InterruptedException {
        Thread.sleep(4000);

    }
}
