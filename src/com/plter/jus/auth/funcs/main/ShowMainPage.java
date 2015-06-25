package com.plter.jus.auth.funcs.main;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.tools.RenderTool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/24/15.
 */
public class ShowMainPage extends Function{

    @Override
    @RequireLogin
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RenderTool.setRenderPage(request,"main");
    }
}
