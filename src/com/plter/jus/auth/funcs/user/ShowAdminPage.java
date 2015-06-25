package com.plter.jus.auth.funcs.user;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.tools.AttrTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.UsersEntity;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by plter on 6/25/15.
 */
public class ShowAdminPage extends Function{

    public static final String KEY_CURRENT_PAGE_INDEX = "currentPageIndex";
    public static final String KEY_MAX_PAGE_COUNT = "maxPageCount";
    public static final String KEY_USERS_COUNT = "usersCount";
    public static final String KEY_USER_LIST = "userList";
    public static final String KEY_PER_PAGE_COUNT = "perPageCount";
    static public final int PER_PAGE_COUNT = 100;

    @Override
    @RequireLogin
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Session session = DbConnection.getSession();

        int pageIndex = AttrTool.getRequestParam(request,"pi"/*Page Index*/,0);

        List<UsersEntity> list = session.createCriteria(UsersEntity.class).setFirstResult(pageIndex * PER_PAGE_COUNT)
                .setMaxResults(PER_PAGE_COUNT)
                .addOrder(Order.desc("id"))
                .list();

        long usersCount = (long) session.createCriteria(UsersEntity.class).setProjection(Projections.rowCount()).uniqueResult();

        request.setAttribute(KEY_CURRENT_PAGE_INDEX, pageIndex);
        request.setAttribute(KEY_USER_LIST, list);
        request.setAttribute(KEY_USERS_COUNT, usersCount);
        request.setAttribute(KEY_MAX_PAGE_COUNT, usersCount / PER_PAGE_COUNT + 1);
        request.setAttribute(KEY_PER_PAGE_COUNT, PER_PAGE_COUNT);

        session.close();
    }
}
