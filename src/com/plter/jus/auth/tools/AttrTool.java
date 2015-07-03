package com.plter.jus.auth.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by plter on 6/25/15.
 */
public class AttrTool {

    static public <T> T getRequestParam(HttpServletRequest request,String key,T defaultValue){
        Object o = request.getParameter(key);
        return o!=null?(T)o:defaultValue;
    }

    static public long getRequestLongParam(HttpServletRequest request,String key,long defaultValue){
        String value = request.getParameter(key);
        if (value==null){
            return defaultValue;
        }

        try{
            return Long.parseLong(value);
        }catch (Exception e){
            return defaultValue;
        }
    }

    static public<T> T getSessionValue(HttpSession session,String key,T defaultValue){
        Object obj = session.getAttribute(key);
        return obj!=null?(T)obj:defaultValue;
    }

    static public<T> T getRequestAttr(HttpServletRequest request,String key,T defaultValue){
        Object obj = request.getAttribute(key);
        return obj!=null?(T)obj:defaultValue;
    }

    static public void setRequestAttr(HttpServletRequest request,String key,Object value){
        request.setAttribute(key,value);
    }
}
