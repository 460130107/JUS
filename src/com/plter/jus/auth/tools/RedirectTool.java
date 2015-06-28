package com.plter.jus.auth.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by plter on 6/26/15.
 */
public class RedirectTool {

    static public void redirectTo(HttpServletRequest request,HttpServletResponse response,String path){
        try {
            response.sendRedirect(String.format("%s%s",request.getContextPath(), path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
