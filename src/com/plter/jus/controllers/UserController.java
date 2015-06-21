package com.plter.jus.controllers;

import com.plter.jus.db.Users;
import com.plter.jus.db.entities.DbConnection;
import com.plter.jus.db.entities.UsersEntity;
import com.plter.jus.tools.MD5;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    @RequestMapping(value = "ua"/*User Admin Page*/,method = RequestMethod.GET)
    public String userAdmin(ModelMap map,@RequestParam(value = "pi",required = false,defaultValue = "0") int pageIndex,HttpServletRequest request,HttpServletResponse response){
        Session session = DbConnection.getSession();
        Users.retrieveUsers(session,map,pageIndex);
        session.close();
        return "UserAdmin";
    }

    @RequestMapping(value = "au"/*Add User Action*/,method = RequestMethod.POST)
    public String addUser(ModelMap map,
                          @RequestParam(value = "name",required = false)String name,
                          @RequestParam(value = "pass",required = false)String pass,
                          @RequestParam(value = "email",required = false)String email){
        try {
            pass = MD5.md5(pass);
            Session session = DbConnection.getSession();
            Transaction transaction = session.beginTransaction();

            UsersEntity entity = new UsersEntity();
            entity.setName(name);
            entity.setEmail(email);
            entity.setPass(pass);
            session.save(entity);

            transaction.commit();
            session.close();

            map.addAttribute("success",true);
        }catch (Exception e){
            e.printStackTrace();
            map.addAttribute("success",false);
        }

        return "AddUser";
    }
}
