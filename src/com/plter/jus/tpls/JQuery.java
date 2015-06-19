package com.plter.jus.tpls;

/**
 * Created by plter on 6/18/15.
 */
public class JQuery {

    public static final String KEY_JQUERY_HTML = "jQueryHtml";
    public static final String KEY_JQUERY_UI_HTML = "jQueryUIHtml";

    public static String getJQueryHtmlCode(){
        return "<script src=\"http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js\"></script>";
    }

    public static String getJQueryUIHtmlCode(){
        return "<link href=\"http://apps.bdimg.com/libs/jqueryui/1.9.2/themes/base/minified/jquery-ui.min.css\" rel=\"stylesheet\">\n" +
                "<script src=\"http://apps.bdimg.com/libs/jqueryui/1.9.2/jquery-ui.min.js\"></script>";
    }
}
