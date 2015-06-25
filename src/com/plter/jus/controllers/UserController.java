package com.plter.jus.controllers;

import com.plter.jus.auth.Functions;
import com.plter.jus.auth.funcs.user.ShowAdminPage;
import com.plter.jus.auth.funcs.user.AddUser;
import com.plter.jus.auth.funcs.user.Login;
import com.plter.jus.auth.funcs.user.Reg;
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
        return "user/AdminPage";
    }

    @RequestMapping(value = "/u/add"/*Add User Action*/,method = RequestMethod.POST)
    public String addUser(HttpServletRequest request,HttpServletResponse response){
        Functions.call(AddUser.class, request, response);
        return "user/AddUser";
    }

    @RequestMapping(value = "/u/lp"/*Login page*/,method = RequestMethod.GET)
    public String loginPage(ModelMap map){
        return "user/LoginPage";
    }

    @RequestMapping(value = "/u/lp",method = RequestMethod.POST)
    public @ResponseBody String postLogin(HttpServletRequest request,HttpServletResponse response){
        request.setAttribute("errorMsg","Nothing");

        Functions.call(Login.class,request,response);

        String errorMsg = (String) request.getAttribute("errorMsg");
        return errorMsg;
    }

    @RequestMapping(value = "/u/reg",method = RequestMethod.POST)
    public @ResponseBody String reg(HttpServletRequest request,HttpServletResponse response){
        request.setAttribute("errorMsg","Nothing");

        Functions.call(Reg.class,request,response);

        String errorMsg = (String) request.getAttribute("errorMsg");
        return errorMsg;
    }
}
