package com.plter.jus.auth.funcs.user;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.tools.RenderTool;
import com.plter.jus.auth.tools.StatusTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.UsersEntity;
import com.plter.jus.errors.ErrorCodes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 7/1/15.
 */
public class UpdateUserInfo extends Function {
    @Override
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

            String name = request.getParameter("name");
            if (name==null){
                StatusTool.setErrorCode(request,ErrorCodes.NO_USER_NAME);
                break;
            }

            String nicename = request.getParameter("nicename");
            if (nicename==null){
                StatusTool.setErrorCode(request,ErrorCodes.NO_NICENAME);
                break;
            }

            String email = request.getParameter("email");
            if (email==null){
                StatusTool.setErrorCode(request,ErrorCodes.NO_EMAIL);
                break;
            }

            String phone = request.getParameter("phone");
            if (phone==null){
                StatusTool.setErrorCode(request,ErrorCodes.NO_PHONE);
                break;
            }

            String url = request.getParameter("url");
            if (url==null){
                StatusTool.setErrorCode(request,ErrorCodes.NO_URL);
                break;
            }

            String statusString = request.getParameter("status");
            if (statusString==null){
                StatusTool.setErrorCode(request,ErrorCodes.NO_USER_STATUS);
                break;
            }
            int status = -1;
            try{status = Integer.parseInt(statusString);}catch (Exception e){}
            if (status<0){
                StatusTool.setErrorCode(request,ErrorCodes.NO_USER_STATUS);
                break;
            }

            UsersEntity user = null;

            Session session = DbConnection.openSession();
            try {
                Transaction transaction = session.beginTransaction();
                user = (UsersEntity) session.load(UsersEntity.class,uid);
                user.setEmail(email);
                user.setName(name);
                user.setNicename(nicename);
                user.setPhone(phone);
                user.setUrl(url);
                user.setStatus(status);
                transaction.commit();
            }catch (Exception e){
                StatusTool.setErrorCode(request,ErrorCodes.CAN_NOT_WRITE_TO_DB);
                break;
            }

            ShowEditUserPage.retrieveUserGroups(session,request,uid);

            session.close();

            if (user==null){
                StatusTool.setErrorCode(request,ErrorCodes.NU_SUCH_USER_IN_DB);
                break;
            }

            request.setAttribute(KEY_USER,user);
            StatusTool.setErrorCode(request, ErrorCodes.NO_ERROR);
        }while (false);

        RenderTool.setRenderPage(request, "user/EditUser");
    }

    static public final String KEY_USER = "user";
}
