package com.course.controller;


import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping("v1")
public class userManager {
    //启动即加载
    @Autowired
    private SqlSessionTemplate template ;

    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method =RequestMethod.POST)
    public String login(HttpServletResponse response,
                        @RequestBody User user){

       int i=template.selectOne("login",user);
        //登录成功返回cookie值
        Cookie cookie=new Cookie("login","true");
        response.addCookie(cookie);
        if(i==1){
            return "登陆成功"+";  username="+user.getUsername();
        }
        return "登录失败";
    }
    @ApiOperation(value = "添加用户信息",httpMethod = "POST")
    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request,
                           @RequestBody User user){
        //验证cookies方法
        Boolean x=verifyCookies(request);
        int result=0;
        if(x !=null){
            result = template.insert("addUser",user);
        }
        if(result>0){
            return true;
        }
        return false;
    }
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    @PostMapping(value = "/getuserInfo")
    public List getUserInfo(HttpServletRequest request,
                            @RequestBody User user){
        Boolean x=verifyCookies(request);
        if(x==true){
            List users=template.selectList("getUserInfo",user);
            return users;
        }else {
            return null;
        }

    }
    @ApiOperation(value = "更新/删除用户接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request,
                          @RequestBody User user){

        Boolean x=verifyCookies(request);
        int i=0;
        if(x=true){
            i=template.update("updateUserInfo",user);
        }
        return i;
    }

    private Boolean verifyCookies(HttpServletRequest request) {
    Cookie[] cookies=request.getCookies();
        if(Objects.isNull(cookies)){
            return  false;
        }
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("login")&&cookie.getValue()
                    .equals("true")){
                return true;
            }
        }
        return false;
    }

}
