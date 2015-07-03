package com.plter.jus.auth.tools;

import com.plter.jus.Constants;
import com.plter.jus.errors.ErrorCodes;
import com.plter.jus.errors.ErrorMessages;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by plter on 6/30/15.
 */
public class StatusTool {

    static public void setErrorCode(HttpServletRequest request,int code){
        request.setAttribute(Constants.KEY_ERROR_CODE,code);
    }

    static public int getErrorCode(HttpServletRequest request){
        return AttrTool.getRequestAttr(request,Constants.KEY_ERROR_CODE, ErrorCodes.NO_ERROR);
    }

    static public void setErrorMsg(HttpServletRequest request,String msg){
        AttrTool.setRequestAttr(request,Constants.KEY_ERROR_MSG,msg);
    }

    static public String getErrorMsg(HttpServletRequest request){
        return AttrTool.getRequestAttr(request,Constants.KEY_ERROR_MSG, ErrorMessages.NOTHING);
    }

    static public void setErrorCodeAndMsg(HttpServletRequest request,int code,String msg){
        setErrorCode(request,code);
        setErrorMsg(request,msg);
    }
}


