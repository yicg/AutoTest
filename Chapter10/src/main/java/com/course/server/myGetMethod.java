package com.course.server;
/**
 * 练习springboot进行get请求
 */

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class myGetMethod {
    /**
     *书写一个get请求，返回cookies值
     */
    @RequestMapping(value = "/getcookies",method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response){
        /**
         * HttpServletResponse
         * HttpServletRequest
         */

        Cookie cookies=new Cookie("login","true");
        response.addCookie(cookies);

        return "恭喜你获得cookies值";
    }

    /**
     * 这是一个需要携带cookies才能进行访问的get请求
     */
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(Objects.isNull(cookies)){
                return "你必须携带cookies信息来";
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "恭喜你携带了正确的cookies进行get请求了";
            }else{
                return "携带的cookies不一致，请重新";
            }
        }

        return "这是一个需要携带cookies信息的请求";
    }
    /**
     * 这是一个携带参数进行的get请求
     * 第一种参数携带方式
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    public Map getWithParamOne(@RequestParam Integer start, @RequestParam Integer end){
        Map list=new HashMap();
        list.put("张三","100");
        list.put("李四","200");
        list.put("王五","250");

        return list;
    }

    /**
     * 第二种参数携带方式
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    public Map getWithParamTwo(@PathVariable Integer start, @PathVariable Integer end){
        Map myList=new HashMap();
        myList.put("衬衫","120");
        myList.put("裤子","200");
        myList.put("外套","300");
        return myList;
    }
}
