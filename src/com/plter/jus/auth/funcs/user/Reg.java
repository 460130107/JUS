package com.plter.jus.auth.funcs.user;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.tools.PasswordTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.UsersEntity;
import com.plter.jus.msg.ResponseErrorMessages;
import com.plter.jus.Constants;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by plter on 6/25/15.
 */
public class Reg extends Function {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        do {
            String name = request.getParameter("name");
            if (name==null){
                request.setAttribute(Constants.KEY_ERROR_MSG, ResponseErrorMessages.NO_NAME_FOUND);
                break;
            }
            String pass = request.getParameter("pass");
            if (pass==null){
                request.setAttribute(Constants.KEY_ERROR_MSG,ResponseErrorMessages.NO_PASS_FOUND);
                break;
            }
            String email = request.getParameter("email");
            if (email==null){
                request.setAttribute(Constants.KEY_ERROR_MSG,ResponseErrorMessages.NO_EMAIL_FOUND);
                break;
            }

            Session session = DbConnection.getSession();
            try {
                Transaction transaction = session.beginTransaction();
                UsersEntity entity = new UsersEntity();
                entity.setName(name);
                entity.setEmail(email);
                entity.setPass(PasswordTool.translatePassword(pass));
                session.save(entity);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
                request.setAttribute(Constants.KEY_ERROR_MSG,ResponseErrorMessages.CAN_NOT_WRITE_TO_DB);
            }
            session.close();


            String redirect = request.getParameter("redirect");
            if (redirect==null){
                redirect=request.getContextPath()+"/";
            }
            try {
                response.sendRedirect(redirect);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while (false);
    }
}
