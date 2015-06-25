package com.plter.jus.controllers;

import com.plter.jus.auth.Functions;
import com.plter.jus.auth.funcs.group.ShowGroupAdminPage;
import com.plter.jus.auth.tools.RenderTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/23/15.
 */

@Controller
public class GroupController {


    @RequestMapping(value = "/g/ap"/*Admin page*/,method = RequestMethod.GET)
    public String userGroupAdmin(HttpServletRequest request,HttpServletResponse response){
        Functions.call(ShowGroupAdminPage.class,request,response);
        return RenderTool.getRenderPage(request);
    }

    @RequestMapping(value = "/g/add"/*Add Group*/,method = RequestMethod.POST)
    public String addGroup(ModelMap map){

        return "group/AddGroup";
    }
}
