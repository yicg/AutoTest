package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.Getuserinfocase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserInfoCase {
    @Test(dependsOnGroups = "loginTrue",description = "获取用户信息")
    public void getUserInfo() throws IOException {
        SqlSession session=DatabaseUtil.getSqlSession();
        Getuserinfocase getUserInfoCase=session.selectOne("GetUserInfo",2);

        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getuserinfoUrl);
    }
}

