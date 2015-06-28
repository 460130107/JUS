package com.plter.jus.controllers;

import com.plter.jus.auth.Functions;
import com.plter.jus.auth.funcs.fun.ShowFunctionList;
import com.plter.jus.auth.tools.RenderTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/26/15.
 */

@Controller
public class FunController {


    @RequestMapping(value = "/fs"/*Functions*/,method = RequestMethod.GET)
    public String functionList(HttpServletRequest request,HttpServletResponse response){
        Functions.call(ShowFunctionList.class,request,response);
        return RenderTool.getRenderPage(request);
    }
}
