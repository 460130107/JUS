package com.plter.jus.tpls;

/**
 * Created by plter on 6/18/15.
 */
public class Menu {
    public static final String KEY_MAIN_MENU_HTML = "mainMenuHtml";

    public static String getMainMenuHtmlCode(String contextPath){
        return String.format("<div class=\"mainmenu\">\n" +
                "    <ul class=\"menu\">\n" +
                "        <li><a href=\"%s/\">首页</a></li>\n" +
                "        <li><a href=\"useradmin\">用户管理</a></li>\n" +
                "        <li><a href=\"#\">用户组管理</a></li>\n" +
                "    </ul>\n" +
                "</div>",contextPath);
    }
}
