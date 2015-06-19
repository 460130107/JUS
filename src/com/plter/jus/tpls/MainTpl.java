package com.plter.jus.tpls;

/**
 * Created by plter on 6/18/15.
 */
public class MainTpl {

    public static final String KEY_MAIN_STYLE_HTML ="mainStyleHtml";
    public static final String KEY_MAIN_JS ="mainJs";

    public static String getMainStyleHtmlCode(String contextPath){
        return String.format("<link rel=\"stylesheet\" href=\"%s/static/style.css\">", contextPath);
    }

    public static String getMainJs(String contextPath){
        return String.format("<script src=\"%s/static/script.js\"></script>",contextPath);
    }
}
