package com.plter.jus.auth.funcs.group;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.tools.RedirectTool;
import com.plter.jus.auth.tools.StatusTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.GroupsEntity;
import com.plter.jus.db.entities.GroupshipEntity;
import com.plter.jus.errors.ErrorCodes;
import com.plter.jus.errors.ErrorMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by plter on 7/3/15.
 */
public class AddGroupship extends Function {
    @Override
    @RequireLogin
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        do {
            String uidString = request.getParameter("uid");
            if (uidString==null){
                StatusTool.setErrorCodeAndMsg(request,ErrorCodes.NO_UID, ErrorMessages.NO_UID);
                break;
            }
            long uid = 0;
            try{uid=Long.parseLong(uidString);}catch (Exception e){}
            if (uid<=0){
                StatusTool.setErrorCodeAndMsg(request,ErrorCodes.NO_UID, ErrorMessages.NO_UID);
                break;
            }

            String groupName = request.getParameter("groupname");
            if (groupName==null){
                StatusTool.setErrorCodeAndMsg(request,ErrorCodes.NO_GROUP_NAME,ErrorMessages.NO_GROUP_NAME);
                break;
            }

            Session session = DbConnection.openSession();

            List<GroupsEntity> list = session.createCriteria(GroupsEntity.class).add(Restrictions.eq("name", groupName)).list();
            if (list.size()<=0){
                StatusTool.setErrorCodeAndMsg(request, ErrorCodes.NO_SUCH_GROUP_IN_DB, ErrorMessages.NO_SUCH_GROUP_IN_DB);

                session.close();
                break;
            }

            GroupsEntity group = list.get(0);

            try {
                Transaction transaction = session.beginTransaction();
                GroupshipEntity groupship = new GroupshipEntity();
                groupship.setGroupid(group.getId());
                groupship.setUserid(uid);
                session.save(groupship);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }

            session.close();

            RedirectTool.redirectTo(request, response, "/u/ep?uid=" + uid);
        }while (false);
    }
}
