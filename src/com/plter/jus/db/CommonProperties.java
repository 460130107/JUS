package com.plter.jus.db;

import com.plter.jus.tpls.JQuery;
import com.plter.jus.tpls.MainTpl;
import com.plter.jus.tpls.Menu;
import org.hibernate.Session;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/18/15.
 */
public class CommonProperties {

    public static final String KEY_CONTEXT_PATH = "contextPath";

    public static void retrieveCommonProperties(Session session,ModelMap map,HttpServletRequest request,HttpServletResponse response){
        String contextPath = request.getContextPath();
        map.addAttribute(KEY_CONTEXT_PATH,contextPath);
        map.addAttribute(MainTpl.KEY_MAIN_STYLE_HTML, MainTpl.getMainStyleHtmlCode(contextPath));
        map.addAttribute(MainTpl.KEY_MAIN_JS, MainTpl.getMainJs(contextPath));
        map.addAttribute(JQuery.KEY_JQUERY_HTML,JQuery.getJQueryHtmlCode());
        map.addAttribute(JQuery.KEY_JQUERY_UI_HTML,JQuery.getJQueryUIHtmlCode());
        map.addAttribute(Menu.KEY_MAIN_MENU_HTML,Menu.getMainMenuHtmlCode(contextPath));
    }

}
