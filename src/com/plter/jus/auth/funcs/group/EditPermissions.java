package com.plter.jus.auth.funcs.group;

import com.plter.jus.Constants;
import com.plter.jus.auth.Function;
import com.plter.jus.auth.Functions;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.funcs.fun.ShowFunctionList;
import com.plter.jus.auth.tools.RedirectTool;
import com.plter.jus.auth.tools.RenderTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.FuncshipEntity;
import com.plter.jus.msg.ErrorMessages;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by plter on 6/26/15.
 */
public class EditPermissions extends Function {


    static public final String KEY_GROUP_FUNC_NAMES = "names";


    @Override
    @RequireLogin
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        boolean success = false;

        do {
            String groupId = request.getParameter("gid");
            if (groupId==null){
                request.setAttribute(Constants.KEY_ERROR_MSG,ErrorMessages.NO_GROUP_ID_FOUND);
                break;
            }

            long gid = 0;
            try {gid = Long.parseLong(groupId);}catch (NumberFormatException e){}
            if (gid>0){
                Session session = DbConnection.getSession();

                List<FuncshipEntity> list = session.createCriteria(FuncshipEntity.class).add(Restrictions.eq("groupid", gid)).list();
                session.close();

                request.setAttribute(ShowFunctionList.KEY_FUNCTIONS, Functions.getFunctions().keySet());
                request.setAttribute(KEY_GROUP_FUNC_NAMES, list.stream().map(FuncshipEntity::getFuncname).collect(Collectors.toList()));

                success = true;
            }else {
                request.setAttribute(Constants.KEY_ERROR_MSG,ErrorMessages.NO_GROUP_ID_FOUND);
                break;
            }

        }while (false);

        if (!success){
            RedirectTool.redirectTo(request,response,"/g/ap");
        }

        RenderTool.setRenderPage(request,"group/EditPermissions");
    }
}
