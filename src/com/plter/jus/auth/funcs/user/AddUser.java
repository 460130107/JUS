package com.plter.jus.auth.funcs.user;

import com.plter.jus.Constants;
import com.plter.jus.auth.Function;
import com.plter.jus.auth.annotation.RequireSuperAdmin;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.tools.AttrTool;
import com.plter.jus.auth.tools.PasswordTool;
import com.plter.jus.auth.tools.RedirectTool;
import com.plter.jus.auth.tools.RenderTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.UsersEntity;
import com.plter.jus.msg.ErrorMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/23/15.
 */
public class AddUser extends Function{


    @Override
    @RequireLogin
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        boolean success = false;
        do {
            String pass = request.getParameter("pass");
            if (pass==null){
                request.setAttribute("errorMsg","密码不能为空");
                break;
            }
            String name = request.getParameter("name");
            if (name==null){
                request.setAttribute("errorMsg","用户名不能为空");
                break;
            }
            String email = request.getParameter("email");
            if (email==null){
                request.setAttribute("errorMsg","邮箱不能为空");
            }

            pass = PasswordTool.translatePassword(pass);

            Session session = DbConnection.getSession();

            try {
                Transaction transaction = session.beginTransaction();

                UsersEntity entity = new UsersEntity();
                entity.setName(name);
                entity.setEmail(email);
                entity.setPass(pass);
                session.save(entity);

                transaction.commit();

                success = true;
            }catch (Exception e){
                e.printStackTrace();
                request.setAttribute(Constants.KEY_ERROR_MSG, ErrorMessages.CAN_NOT_WRITE_TO_DB);
            }

            session.close();
        }while (false);

        if (success){
            RedirectTool.redirectTo(request,response,"/u/ap");
        }

        RenderTool.setRenderPage(request,"user/AddUser");
    }
}
