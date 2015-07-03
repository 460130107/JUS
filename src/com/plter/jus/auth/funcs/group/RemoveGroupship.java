package com.plter.jus.auth.funcs.group;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.tools.AttrTool;
import com.plter.jus.auth.tools.RedirectTool;
import com.plter.jus.auth.tools.StatusTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.GroupshipEntity;
import com.plter.jus.errors.ErrorCodes;
import com.plter.jus.errors.ErrorMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 7/3/15.
 */
public class RemoveGroupship extends Function {

    @Override
    @RequireLogin
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        do {
            long uid = AttrTool.getRequestLongParam(request,"uid",0);
            if (uid==0){
                StatusTool.setErrorCodeAndMsg(request,ErrorCodes.NO_UID,ErrorMessages.NO_UID);
                break;
            }

            long gid = AttrTool.getRequestLongParam(request,"gid",0);
            if (gid==0){
                StatusTool.setErrorCodeAndMsg(request,ErrorCodes.NO_UID,ErrorMessages.NO_UID);
                break;
            }

            Session session = DbConnection.openSession();

            try {
                Transaction transaction = session.beginTransaction();
                session.createSQLQuery(String.format("DELETE FROM groupship WHERE groupship.groupid=%d AND groupship.userid=%d",gid,uid)).executeUpdate();
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }

            session.close();

            RedirectTool.redirectTo(request, response, "/u/ep?uid=" + uid);
        }while (false);
    }
}
