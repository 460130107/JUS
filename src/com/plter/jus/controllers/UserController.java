package com.plter.jus.controllers;

import com.plter.jus.Constants;
import com.plter.jus.auth.Functions;
import com.plter.jus.auth.funcs.user.AddUser;
import com.plter.jus.auth.funcs.user.Login;
import com.plter.jus.auth.funcs.user.Reg;
import com.plter.jus.auth.funcs.user.ShowAdminPage;
import com.plter.jus.auth.tools.AttrTool;
import com.plter.jus.auth.tools.RenderTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/18/15.
 */

@Controller
public class UserController {

    @RequestMapping(value = "/u/ap"/*Admin Page*/,method = RequestMethod.GET)
    public String userAdmin(HttpServletRequest request, HttpServletResponse response){
        Functions.call(ShowAdminPage.class,request,response);
        return RenderTool.getRenderPage(request);
    }

    @RequestMapping(value = "/u/add"/*Add User Action*/,method = RequestMethod.POST)
    public String addUser(HttpServletRequest request,HttpServletResponse response){
        Functions.call(AddUser.class, request, response);
        return RenderTool.getRenderPage(request);
    }

    @RequestMapping(value = "/u/lp"/*Login page*/,method = RequestMethod.GET)
    public String loginPage(ModelMap map){
        return "user/LoginPage";
    }

    @RequestMapping(value = "/u/lp",method = RequestMethod.POST)
    public @ResponseBody String postLogin(HttpServletRequest request,HttpServletResponse response){
        Functions.call(Login.class,request,response);
        return AttrTool.getRequestAttr(request, Constants.KEY_ERROR_MSG,"Nothing");
    }

    @RequestMapping(value = "/u/reg",method = RequestMethod.POST)
    public @ResponseBody String reg(HttpServletRequest request,HttpServletResponse response){
        Functions.call(Reg.class,request,response);
        return AttrTool.getRequestAttr(request, Constants.KEY_ERROR_MSG,"Nothing");
    }
}
