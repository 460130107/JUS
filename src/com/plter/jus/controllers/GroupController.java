package com.plter.jus.controllers;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.Functions;
import com.plter.jus.auth.funcs.group.AddGroup;
import com.plter.jus.auth.funcs.group.EditPermissions;
import com.plter.jus.auth.funcs.group.ShowGroupAdminPage;
import com.plter.jus.auth.funcs.group.UpdatePermissions;
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
    public String addGroup(HttpServletRequest request,HttpServletResponse response){
        Functions.call(AddGroup.class,request,response);
        return RenderTool.getRenderPage(request);
    }

    @RequestMapping(value = "/g/ep"/*Edit Permissions*/,method = RequestMethod.GET)
    public String editPermissions(HttpServletRequest request,HttpServletResponse response){
        Functions.call(EditPermissions.class,request,response);
        return RenderTool.getRenderPage(request);
    }


    @RequestMapping(value = "/g/up"/*Update permissions*/,method = RequestMethod.POST)
    public String updatePermissions(HttpServletRequest request,HttpServletResponse response){
        Functions.call(UpdatePermissions.class,request,response);
        return RenderTool.getRenderPage(request);
    }
}
