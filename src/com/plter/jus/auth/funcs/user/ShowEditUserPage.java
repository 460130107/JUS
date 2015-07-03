package com.plter.jus.auth.funcs.user;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.tools.RenderTool;
import com.plter.jus.auth.tools.StatusTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.GroupsEntity;
import com.plter.jus.db.entities.GroupshipEntity;
import com.plter.jus.db.entities.UsersEntity;
import com.plter.jus.errors.ErrorCodes;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by plter on 6/30/15.
 */
public class ShowEditUserPage extends Function {
    @Override
    @RequireLogin
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        do {
            String uidString = request.getParameter("uid");
            if (uidString==null){
                StatusTool.setErrorCode(request, ErrorCodes.NO_UID);
                break;
            }

            long uid = 0;
            try {uid = Long.parseLong(uidString);}catch (Exception e){}

            if (uid==0){
                StatusTool.setErrorCode(request,ErrorCodes.NO_UID);
                break;
            }

            Session session = DbConnection.openSession();
            List<UsersEntity> list = session.createCriteria(UsersEntity.class).add(Restrictions.eq("id", uid)).list();
            retrieveUserGroups(session,request,uid);
            session.close();

            if (list.size()<=0){
                StatusTool.setErrorCode(request,ErrorCodes.NU_SUCH_USER_IN_DB);
                break;
            }

            request.setAttribute(KEY_USER,list.get(0));
            StatusTool.setErrorCode(request,ErrorCodes.NO_ERROR);
        }while (false);

        RenderTool.setRenderPage(request,"user/EditUser");
    }


    static void retrieveUserGroups(Session session,HttpServletRequest request,long uid){
        List<GroupshipEntity> list = session.createCriteria(GroupshipEntity.class).add(Restrictions.eq("userid", uid)).list();
        if (list.size()>0){
            Object[] gids = list.stream().map(GroupshipEntity::getGroupid).toArray();
            List glists = session.createCriteria(GroupsEntity.class).add(Restrictions.in("id", gids)).list();
            if (glists.size()>0){
                request.setAttribute(KEY_GROUPS,glists);
            }
        }
    }

    static public final String KEY_GROUPS = "groups";
    static public final String KEY_USER = "user";
}
