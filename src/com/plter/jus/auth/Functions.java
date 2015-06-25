package com.plter.jus.auth;

import com.plter.jus.auth.annotation.RequireLogin;
import com.plter.jus.auth.funcs.user.ShowAdminPage;
import com.plter.jus.auth.funcs.main.ShowMainPage;
import com.plter.jus.auth.funcs.user.AddUser;
import com.plter.jus.auth.funcs.user.Login;
import com.plter.jus.auth.funcs.user.Reg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by plter on 6/23/15.
 */
public class Functions {

    static public void call(Class funcClass,HttpServletRequest request,HttpServletResponse response){
        Function f = AllFunctions.get(funcClass.getName());
        if (f!=null){
            try {
                Method m = f.getClass().getMethod("execute",HttpServletRequest.class,HttpServletResponse.class);
                if (m.isAnnotationPresent(RequireLogin.class)&&!Login.CurrentUser.isLogged(request)){
                    try {
                        //Show login page
                        response.sendRedirect(String.format("%s/u/lp?redirect=%s",request.getContextPath(),request.getRequestURI()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        m.invoke(f,request,response);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }


    private static final Map<String,Function> AllFunctions = new HashMap<>();

    static public void addFunc(Function func){
        AllFunctions.put(func.getClass().getName(),func);
    }

    static {
        addFunc(new AddUser());
        addFunc(new Login());
        addFunc(new ShowMainPage());
        addFunc(new ShowAdminPage());
        addFunc(new Reg());
    }
}
