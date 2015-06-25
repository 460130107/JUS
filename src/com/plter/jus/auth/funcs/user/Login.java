package com.plter.jus.auth.funcs.user;

import com.plter.jus.auth.Function;
import com.plter.jus.auth.tools.PasswordTool;
import com.plter.jus.db.DbConnection;
import com.plter.jus.db.entities.UsersEntity;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by plter on 6/24/15.
 */
public class Login extends Function {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        do {

            String name = request.getParameter("name");
            if (name==null){
                request.setAttribute("errorMsg","No name input");
                break;
            }
            String pass = request.getParameter("pass");
            if (pass==null){
                request.setAttribute("errorMsg","No password input");
                break;
            }

            pass = PasswordTool.translatePassword(pass);

            Session session = DbConnection.getSession();
            List<UsersEntity> list = session.createCriteria(UsersEntity.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("pass", pass)).list();
            session.close();

            if (list.size()>0){
                CurrentUser.setLogged(request, true);
                CurrentUser.setLoggedName(request, name);
                CurrentUser.setLoggedId(request, list.get(0).getId());

                String redirect = request.getParameter("redirect");
                if (redirect==null){
                    redirect = request.getContextPath();
                }

                try {
                    response.sendRedirect(redirect);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                request.setAttribute("errorMsg","登陆失败");
            }
        }while (false);

    }


    static public class CurrentUser {

        static private final String KEY_LOGGED = "logged";
        static private final String KEY_LOGGED_ID = "loggedId";
        static private final String KEY_LOGGED_NAME = "loggedName";

        static public boolean isLogged(HttpServletRequest request){
            return getValueFromSession(request.getSession(),KEY_LOGGED,false);
        }

        static public void setLogged(HttpServletRequest request,boolean logState){
            request.getSession().setAttribute(KEY_LOGGED,true);
        }

        static public void setLoggedId(HttpServletRequest request,long id){
            request.getSession().setAttribute(KEY_LOGGED_ID, id);
        }

        static public long getLoggedId(HttpServletRequest request){
            return getValueFromSession(request.getSession(),KEY_LOGGED_ID,0L);
        }

        static public void setLoggedName(HttpServletRequest request,String name){
            request.getSession().setAttribute(KEY_LOGGED_NAME, name);
        }

        static public String getLoggedName(HttpServletRequest request){
            return getValueFromSession(request.getSession(),KEY_LOGGED_NAME,"");
        }


        static public<T> T getValueFromSession(HttpSession session,String key,T defaultValue){
            Object obj = session.getAttribute(key);
            return obj!=null?(T)obj:defaultValue;
        }
    }
}
