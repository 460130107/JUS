package com.plter.jus.auth;

import com.plter.jus.Constants;
import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.annotation.RequireSuperAdmin;
import com.plter.jus.auth.funcs.fun.ShowFunctionList;
import com.plter.jus.auth.funcs.group.*;
import com.plter.jus.auth.funcs.main.ShowMainPage;
import com.plter.jus.auth.funcs.user.*;
import com.plter.jus.auth.tools.RenderTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.FuncshipEntity;
import com.plter.jus.db.entities.GroupshipEntity;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by plter on 6/23/15.
 */
public class Functions {

    static public void call(Class funcClass,HttpServletRequest request,HttpServletResponse response){
        String funcName = funcClass.getName();
        Function f = AllFunctions.get(funcName);
        if (f!=null){
            try {
                Method m = f.getClass().getMethod("execute",HttpServletRequest.class,HttpServletResponse.class);
                if (m.isAnnotationPresent(RequireLogin.class)){
                    if (Login.CurrentUser.isLogged(request)){
                        if (!m.isAnnotationPresent(RequireSuperAdmin.class)){
                            long uid = Login.CurrentUser.getLoggedId(request);

                            if (uid!= Constants.SUPER_ADMIN_ID){
                                Session session = DbConnection.openSession();
                                boolean pass = false;//是否成功通过权限验证

                                List<GroupshipEntity> list = session.createCriteria(GroupshipEntity.class).add(Restrictions.eq("userid", uid)).list();
                                if (list.size()>0) {
                                    List<Long> groupIds = list.stream().map(GroupshipEntity::getGroupid).collect(Collectors.toList());
                                    List<FuncshipEntity> funcships = session.createCriteria(FuncshipEntity.class).add(Restrictions.in("groupid",groupIds)).list();
                                    session.close();

                                    if (funcships.stream().map(FuncshipEntity::getFuncname).collect(Collectors.toList()).contains(funcName)){
                                        f.execute(request,response);
                                        pass = true;
                                    }
                                }else {
                                    session.close();
                                }

                                if (!pass){
                                    RenderTool.setRenderPage(request, "errors/AccessDenied");
                                }
                            }else {
                                f.execute(request, response);
                            }
                        }else {
                            if (Login.CurrentUser.getLoggedId(request)==Constants.SUPER_ADMIN_ID){
                                f.execute(request,response);
                            }else {
                                RenderTool.setRenderPage(request, "errors/AccessDenied");
                            }
                        }
                    }else {
                        showLoginPage(request,response);
                    }
                }else {
                    f.execute(request,response);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    static private void showLoginPage(HttpServletRequest request,HttpServletResponse response){
        try {
            StringBuffer callback = request.getRequestURL();
            String params = request.getQueryString();
            if (params!=null){
                callback.append("?").append(params);
            }

            response.sendRedirect(String.format("%s/u/lp?redirect=%s", request.getContextPath(), URLEncoder.encode(callback.toString(),"UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static final Map<String,Function> AllFunctions = new HashMap<>();

    public static Map<String, Function> getFunctions() {
        return AllFunctions;
    }

    static public void addFunc(Function func){
        AllFunctions.put(func.getClass().getName(),func);
    }

    static {
        addFunc(new AddUser());
        addFunc(new Login());
        addFunc(new ShowMainPage());
        addFunc(new ShowAdminPage());
        addFunc(new Reg());
        addFunc(new ShowGroupAdminPage());
        addFunc(new AddGroup());
        addFunc(new ShowFunctionList());
        addFunc(new EditPermissions());
        addFunc(new UpdatePermissions());
        addFunc(new ShowEditUserPage());
        addFunc(new UpdateUserInfo());
        addFunc(new AddGroupship());
        addFunc(new RemoveGroupship());
    }
}
