package com.plter.jus.controllers;

import com.plter.jus.auth.Functions;
import com.plter.jus.auth.funcs.main.ShowMainPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/17/15.
 */

@Controller
public class MainController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String mainPage(ModelMap map, HttpServletRequest req, HttpServletResponse resp){
        Functions.call(ShowMainPage.class,req,resp);
        return "main";
    }
}
