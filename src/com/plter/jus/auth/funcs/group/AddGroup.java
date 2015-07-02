package com.plter.jus.auth.funcs.group;

import com.plter.jus.Constants;
import com.plter.jus.auth.Function;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.tools.RedirectTool;
import com.plter.jus.auth.tools.RenderTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.GroupsEntity;
import com.plter.jus.errors.ErrorMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/26/15.
 */
public class AddGroup extends Function {

    @Override
    @RequireLogin
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        boolean success = false;

        do {
            String name = request.getParameter("name");
            if (name==null){
                request.setAttribute(Constants.KEY_ERROR_MSG,ErrorMessages.NO_NAME_FOUND);
                break;
            }
            String desc = request.getParameter("desc");
            if (desc==null){
                request.setAttribute(Constants.KEY_ERROR_MSG,ErrorMessages.NO_DESC_FOUND);
                break;
            }

            GroupsEntity e = new GroupsEntity();
            e.setName(name);
            e.setDescription(desc);

            Session session = DbConnection.getSession();
            try {
                Transaction transaction = session.beginTransaction();

                session.save(e);

                transaction.commit();

                success = true;
            }catch (Exception e1){
                e1.printStackTrace();
            }
            session.close();
        }while (false);

        if (success){
            RedirectTool.redirectTo(request,response,"/g/ap");
        }

        RenderTool.setRenderPage(request, "group/AddGroup");
    }
}
