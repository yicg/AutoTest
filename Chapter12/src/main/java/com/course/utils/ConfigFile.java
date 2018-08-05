package com.course.utils;

import com.course.model.InterfaceName;


import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {
    private static ResourceBundle bundle=ResourceBundle.getBundle("application",Locale.CHINA);
    public static String getUrl(InterfaceName name){
        String address=bundle.getString("test.url");
        String uri=null;
        if(name==InterfaceName.ADDUSER){
            uri=bundle.getString("adduser.uri");
        }if(name==InterfaceName.LOGIN){
            uri=bundle.getString("login.uri");
        }if(name==InterfaceName.GETUSERLIST){
            uri=bundle.getString("getuserlist.uri");
        }if(name==InterfaceName.UPDATEUSERINFO){
            uri=bundle.getString("updateuserinfo.uri");
        }if(name==InterfaceName.GETUSERINFO){
            uri=bundle.getString("getuserinfo.uri");
        }

        String testUrl=address+uri;
        return testUrl;
    }
}
