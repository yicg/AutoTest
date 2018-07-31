package com.course.controller;

import com.course.model.Test;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "v1",description = "这是mybatis基本增删改查用法")
public class Demo {
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户列表",httpMethod = "GET")
    public List<Object> getList(){

        return template.selectList("getUserList");
    }
    @PostMapping(value = "/addUserInfo")
    @ApiOperation(value = "添加用户",httpMethod = "POST")
    public List<Object> addUserInfo(@RequestBody Test test){

        template.insert("addUser",test);
        return template.selectList("getUserList");
    }
    @PostMapping(value = "/updataUserInfo")
    @ApiOperation(value = "更新用户信息",httpMethod = "POST")
    public List<Object> updataUserInfo(
                                       @RequestBody Test test){

        template.update("updataUser",test);
        return template.selectList("getUserList");
    }
    @DeleteMapping(value = "/delUserInfo/{ID}")
    @ApiOperation(value = "删除用户信息",httpMethod = "DELETE")
    public List<Object> delUserInfo(@PathVariable(value = "ID") Integer id){

        template.delete("deleteUser",id);
        return template.selectList("getUserList");
    }

}
