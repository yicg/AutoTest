package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.Getuserlistcase;
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
import java.util.List;


public class GetUserListCase{
    @Test(dependsOnGroups = "loginTrue",description = "获取用户列表")
    public void getUserList() throws IOException {
        SqlSession session=DatabaseUtil.getSqlSession();
        Getuserlistcase getUserListCase=session.selectOne("getUserListCase",1);

        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getuserlistUrl);

        //发送请求获取结果
        JSONArray resultJson=getJsonResult(getUserListCase);
        //验证结果
        List<User> userList=session.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User u:userList){
            System.out.println("获取到的user："+u.toString());
        }
        //转化成json格式
        JSONArray userListJson=new JSONArray(userList);
        Assert.assertEquals(userListJson.length(),resultJson.length());
        for (int i=0;i<resultJson.length();i++){
            JSONObject except= (JSONObject) resultJson.get(i);
            JSONObject actual= (JSONObject) userListJson.get(i);
            Assert.assertEquals(except.toString(),actual.toString());

        }
    }

    private JSONArray getJsonResult(Getuserlistcase getUserListCase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.getuserlistUrl);
        JSONObject param=new JSONObject();
        param.put("username",getUserListCase.getUsername());
        param.put("sex",getUserListCase.getSex());
        param.put("age",getUserListCase.getAge());

        post.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String result=EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray jsonArray=new JSONArray(result);
        return jsonArray;
    }
}
