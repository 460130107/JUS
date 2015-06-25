package com.plter.jus.auth.tools;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by plter on 6/25/15.
 */
public class AttrTool {

    static public <T> T getRequestParam(HttpServletRequest request,String key,T defaultValue){
        Object o = request.getParameter(key);
        return o!=null?(T)o:defaultValue;
    }
}
