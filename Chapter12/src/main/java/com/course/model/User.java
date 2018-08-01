package com.course.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isDelete;

    //复写一个方法，使结果为json格式
    @Override
    public String toString(){
        return (
                        "{id:"+id+","+
                        "username:"+username+","+
                        "password:"+password+","+
                        "age:"+age+","+
                        "sex:"+sex+","+
                        "permission:"+permission+","+
                        "isDelete:"+isDelete+"}"
                );
    }
}
