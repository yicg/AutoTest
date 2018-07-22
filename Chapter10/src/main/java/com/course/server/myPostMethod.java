package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(description = "这是我全部的post请求",value ="/" )
public class myPostMethod {
    /**
     * 返回cookie信息的登录请求
     */
    Cookie cookie;
    @RequestMapping(value = "/get/cookies",method = RequestMethod.POST)
    @ApiOperation(value = "这是一个获取cookies信息的post请求",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String username,
                        @RequestParam(value = "passWord",required = true) String passWord){
        if(username.equals("zhangsan")&&passWord.equals("123456")){
            cookie=new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登录成功，并生成cookies信息";
        }

        return "用户名或密码错误";
    }
    /**
     * 通过cookies获取用户信息
     */
    User user;
    @RequestMapping(value = "/get/userInfo",method = RequestMethod.POST)
    @ApiOperation(value = "这是一个获取用户信息的post请求",httpMethod = "POST")
    public User getInfo(HttpServletRequest request,
                        @RequestBody User u){
        if(u.getUserName().equals("zhangsan")&&u.getPassWord().equals("123456")){
            user=new User();
            user.setAge(20);
            user.setName("李四");
            user.setUserName(u.getUserName());
            user.setPassWord(u.getPassWord());

            return user;
        }

        return null;
    }

}
