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

    public static final String KEY_CURRENT_PAGE_INDEX = "currentPageIndex";
    public static final String KEY_MAX_PAGE_COUNT = "maxPageCount";
    public static final String KEY_USERS_COUNT = "usersCount";
    public static final String KEY_USER_LIST = "userList";
    public static final String KEY_PER_PAGE_COUNT = "perPageCount";

    public static final int PER_PAGE_COUNT = 100;

    public static void retrieveUsers(Session session,ModelMap map,int pageIndex){

        List<UsersEntity> list = session.createCriteria(UsersEntity.class).setFirstResult(pageIndex * PER_PAGE_COUNT)
                .setMaxResults(PER_PAGE_COUNT)
                .addOrder(Order.desc("id"))
                .list();

        long usersCount = (long) session.createCriteria(UsersEntity.class).setProjection(Projections.rowCount()).uniqueResult();

        map.addAttribute(KEY_CURRENT_PAGE_INDEX,pageIndex);
        map.addAttribute(KEY_USER_LIST,list);
        map.addAttribute(KEY_USERS_COUNT,usersCount);
        map.addAttribute(KEY_MAX_PAGE_COUNT,usersCount/PER_PAGE_COUNT+1);
        map.addAttribute(KEY_PER_PAGE_COUNT,PER_PAGE_COUNT);
    }
}
