package com.plter.jus.controllers;

import com.plter.jus.db.Group;
import com.plter.jus.db.DbConnection;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by plter on 6/23/15.
 */

@Controller
public class GroupController {


    @RequestMapping(value = "/g/ap"/*Admin page*/,method = RequestMethod.GET)
    public String userGroupAdmin(ModelMap map){
        Session session = DbConnection.getSession();
        Group.retrieveGroups(session,map);
        session.close();
        return "group/AdminPage";
    }

    @RequestMapping(value = "/g/add"/*Add Group*/,method = RequestMethod.POST)
    public String addGroup(ModelMap map){



        return "group/AddGroup";
    }
}
