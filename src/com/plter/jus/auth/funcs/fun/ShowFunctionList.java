package com.plter.jus.auth.funcs.fun;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.Functions;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.tools.RenderTool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/26/15.
 */
public class ShowFunctionList extends Function {

    static public final String KEY_FUNCTIONS = "functions";

    @Override
    @RequireLogin
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(KEY_FUNCTIONS, Functions.getFunctions().keySet());
        RenderTool.setRenderPage(request,"fun/FunctionsList");
    }
}
