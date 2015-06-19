package com.plter.jus.controllers;

import com.plter.jus.db.CommonProperties;
import com.plter.jus.db.Users;
import com.plter.jus.db.entities.DbConnection;
import com.plter.jus.db.entities.UsersEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/17/15.
 */

@Controller
public class MainController {


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String mainPage(ModelMap map,HttpServletRequest request,HttpServletResponse response){
        Session session = DbConnection.getSession();
        CommonProperties.retrieveCommonProperties(session,map,request,response);
        session.close();
        return "main";
    }
}
