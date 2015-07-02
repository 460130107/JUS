package com.plter.jus.auth.funcs.user;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.tools.RenderTool;
import com.plter.jus.auth.tools.StatusTool;
import com.plter.jus.db.DbConnection;
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

            Session session = DbConnection.getSession();
            List<UsersEntity> list = session.createCriteria(UsersEntity.class).add(Restrictions.eq("id", uid)).list();
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

    static public final String KEY_USER = "user";
}
