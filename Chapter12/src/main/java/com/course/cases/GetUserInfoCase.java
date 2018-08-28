package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.Getuserinfocase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoCase {
    @Test(dependsOnGroups = "loginTrue",description = "获取用户信息")
    public void getUserInfo() throws IOException {
        SqlSession session=DatabaseUtil.getSqlSession();
        Getuserinfocase getUserInfoCase=session.selectOne("GetUserInfoCase",2);

        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getuserinfoUrl);

        JSONArray resultJson=getJsonResult(getUserInfoCase);

        User user=session.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);

        List userList=new ArrayList();
        userList.add(user);
        JSONArray jsonArray=new JSONArray(userList);

        Assert.assertEquals(jsonArray,resultJson);
    }

    private JSONArray getJsonResult(Getuserinfocase getUserInfoCase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.getuserinfoUrl);
        JSONObject param=new JSONObject();
        param.put("id",getUserInfoCase.getId());

        post.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String result=EntityUtils.toString(response.getEntity(),"utf-8");

        List resultList=Arrays.asList(result);
        JSONArray array=new JSONArray(resultList);

        return array;
    }
}

