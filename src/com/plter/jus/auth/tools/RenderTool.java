package com.plter.jus.auth.tools;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by plter on 6/25/15.
 */
public class RenderTool {

    static public final String KEY_RENDER_PAGE = "renderPage";

    static public void setRenderPage(HttpServletRequest request,String page){
        request.setAttribute(KEY_RENDER_PAGE,page);
    }

    static public String getRenderPage(HttpServletRequest request){
        return AttrTool.getRequestAttr(request,KEY_RENDER_PAGE,"main");
    }
}
