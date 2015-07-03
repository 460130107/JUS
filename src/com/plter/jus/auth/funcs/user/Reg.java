package com.plter.jus.auth.funcs.user;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.tools.PasswordTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.UsersEntity;
import com.plter.jus.errors.ErrorMessages;
import com.plter.jus.Constants;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by plter on 6/25/15.
 */
public class Reg extends Function {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        do {
            String name = request.getParameter("name");
            if (name==null){
                request.setAttribute(Constants.KEY_ERROR_MSG, ErrorMessages.NO_NAME);
                break;
            }
            String pass = request.getParameter("pass");
            if (pass==null){
                request.setAttribute(Constants.KEY_ERROR_MSG, ErrorMessages.NO_PASS);
                break;
            }
            String email = request.getParameter("email");
            if (email==null){
                request.setAttribute(Constants.KEY_ERROR_MSG, ErrorMessages.NO_EMAIL);
                break;
            }

            Session session = DbConnection.openSession();
            try {
                Transaction transaction = session.beginTransaction();
                UsersEntity entity = new UsersEntity();
                entity.setName(name);
                entity.setEmail(email);
                entity.setPass(PasswordTool.translatePassword(pass));
                entity.setRegtime(Timestamp.valueOf(LocalDateTime.now()));
                session.save(entity);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
                request.setAttribute(Constants.KEY_ERROR_MSG, ErrorMessages.CAN_NOT_WRITE_TO_DB);
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
