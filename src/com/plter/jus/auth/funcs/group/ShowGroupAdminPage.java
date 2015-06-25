package com.plter.jus.auth.funcs.group;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.tools.RenderTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.GroupsEntity;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by plter on 6/25/15.
 */
public class ShowGroupAdminPage extends Function {

    public static final String KEY_GROUPS = "groups";

    @Override
    @RequireLogin
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Session session = DbConnection.getSession();
        List<GroupsEntity> list = session.createCriteria(GroupsEntity.class).list();
        request.setAttribute(KEY_GROUPS,list);
        session.close();

        RenderTool.setRenderPage(request,"group/AdminPage");
    }
}
