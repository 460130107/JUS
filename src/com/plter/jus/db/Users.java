package com.plter.jus.db;

import com.plter.jus.db.entities.UsersEntity;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * Created by plter on 6/18/15.
 */
public class Users {



    public static final int PER_PAGE_COUNT = 100;

    public static void retrieveUsers(Session session,ModelMap map,int pageIndex){


    }
}
