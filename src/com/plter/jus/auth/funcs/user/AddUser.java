package com.plter.jus.auth.funcs.user;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.tools.PasswordTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.UsersEntity;
import org.apache.commons.codec.digest.DigestUtils;
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
        Session session = DbConnection.getSession();

        try {
            do {
                String pass = request.getParameter("pass");
                if (pass==null){
                    request.setAttribute("success", false);
                    request.setAttribute("errorMsg","密码不能为空");
                    break;
                }
                String name = request.getParameter("name");
                if (name==null){
                    request.setAttribute("success", false);
                    request.setAttribute("errorMsg","用户名不能为空");
                    break;
                }
                String email = request.getParameter("email");
                if (email==null){
                    request.setAttribute("success", false);
                    request.setAttribute("errorMsg","邮箱不能为空");
                }

                pass = PasswordTool.translatePassword(pass);

                Transaction transaction = session.beginTransaction();

                UsersEntity entity = new UsersEntity();
                entity.setName(name);
                entity.setEmail(email);
                entity.setPass(pass);
                session.save(entity);

                transaction.commit();

                request.setAttribute("success", true);
            }while (false);
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("success", false);
        }

        session.close();
    }
}
