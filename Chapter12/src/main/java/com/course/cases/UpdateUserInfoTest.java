package com.course.cases;

        import com.course.config.TestConfig;
        import com.course.model.updateuserinfocase;
        import com.course.utils.DatabaseUtil;
        import org.apache.ibatis.session.SqlSession;
        import org.testng.annotations.Test;

        import java.io.IOException;

public class UpdateUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description = "更新用户信息")
    public void updateUserInfo() throws IOException {
        SqlSession session=DatabaseUtil.getSqlSession();
        updateuserinfocase updateUserInfoTest=session.selectOne("updateUserInfo",1);

        System.out.println(updateUserInfoTest.toString());
        System.out.println(TestConfig.updateuserinfoUrl);
    }

    @Test(dependsOnGroups = "loginTrue",description = "删除用户")
    public void deleteUser() throws IOException {

        SqlSession session=DatabaseUtil.getSqlSession();
        updateuserinfocase updateUserInfoTest=session.selectOne("updateUserInfo",2);

        System.out.println(updateUserInfoTest.toString());
        System.out.println(TestConfig.updateuserinfoUrl);
    }
}

