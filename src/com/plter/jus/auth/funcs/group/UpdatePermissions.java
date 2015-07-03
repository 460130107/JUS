package com.plter.jus.auth.funcs.group;

import com.plter.jus.Constants;
import com.plter.jus.auth.Function;
import com.plter.jus.auth.Functions;
import com.plter.jus.auth.tools.RedirectTool;
import com.plter.jus.auth.tools.RenderTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.FuncshipEntity;
import com.plter.jus.errors.ErrorMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/26/15.
 */
public class UpdatePermissions extends Function {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        boolean success = false;

        long gid = 0;
        do {

            String[] names = request.getParameterValues("funcs");

            if (names==null){
                request.setAttribute(Constants.KEY_ERROR_MSG, ErrorMessages.NO_FUNCS);
                break;
            }

            String groupId = request.getParameter("gid");
            if (groupId==null){
                request.setAttribute(Constants.KEY_ERROR_MSG,ErrorMessages.NO_GROUP_ID);
                break;
            }

            try{gid=Long.parseLong(groupId);}catch (NumberFormatException e){}
            if (gid<=0){
                request.setAttribute(Constants.KEY_ERROR_MSG,ErrorMessages.NO_GROUP_ID);
                break;
            }

            Session session = DbConnection.openSession();

            try {
                Transaction transaction = session.beginTransaction();
                session.createSQLQuery(String.format("DELETE FROM funcship WHERE groupid=%d", gid)).executeUpdate();

                for (String name : names) {
                    if (Functions.getFunctions().containsKey(name)) {
                        FuncshipEntity e = new FuncshipEntity();
                        e.setGroupid(gid);
                        e.setFuncname(name);
                        session.save(e);
                        session.flush();
                        session.clear();
                    }
                }
                transaction.commit();

                success = true;
            }catch (Exception e){
                e.printStackTrace();

                request.setAttribute(Constants.KEY_ERROR_MSG,ErrorMessages.CAN_NOT_WRITE_TO_DB);
            }
            session.close();
        }while (false);

        if (success){
            RedirectTool.redirectTo(request,response,"/g/ep?gid="+gid);
        }
        RenderTool.setRenderPage(request,"group/UpdatePermissions");
    }
}
