package com.plter.jus.controllers;

import com.plter.jus.db.CommonProperties;
import com.plter.jus.db.Users;
import com.plter.jus.db.entities.DbConnection;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/18/15.
 */

@Controller
public class UserController {

    @RequestMapping(value = "useradmin",method = RequestMethod.GET)
    public String userAdmin(ModelMap map,@RequestParam(value = "pi",required = false,defaultValue = "0") int pageIndex,HttpServletRequest request,HttpServletResponse response){
        Session session = DbConnection.getSession();
        CommonProperties.retrieveCommonProperties(session,map,request,response);
        Users.retrieveUsers(session,map,pageIndex);
        session.close();
        return "useradmin";
    }
}
