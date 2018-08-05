package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.Getuserlistcase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;


public class GetUserListCase{
    @Test(dependsOnGroups = "loginTrue",description = "获取用户列表")
    public void getUserList() throws IOException {
        SqlSession session=DatabaseUtil.getSqlSession();
        Getuserlistcase getUserListCase=session.selectOne("getUserList",1);

        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getuserlistUrl);
    }
}
