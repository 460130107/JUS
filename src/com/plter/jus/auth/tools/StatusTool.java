package com.plter.jus.auth.tools;

import com.plter.jus.Constants;
import com.plter.jus.errors.ErrorCodes;

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
}
