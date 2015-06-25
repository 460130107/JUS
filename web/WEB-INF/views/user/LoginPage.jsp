<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>用户登陆 - JUS</title>
    <%@include file="../tpls/jQuery.jsp"%>
    <%@include file="../tpls/jQueryUI.jsp"%>
    <%@include file="../tpls/MainStyle.jsp"%>

    <style type="text/css">
        .ui-dialog-titlebar-close{
            display: none;
        }
    </style>
</head>
<body>

<div id="login-panel" style="display: none;text-align: center;">
    <form action="${pageContext.request.contextPath}/u/lp" method="post">
        <p>名称：<input type="text" name="name" required="required"></p>
        <p>密码：<input type="password" name="pass" required="required"></p>
        <input type="hidden" name="redirect" value="${param.redirect}">

        <input type="submit" class="button" value="登陆">
        <a href="#" class="button" id="btn-show-reg-panel">注册新用户</a>
    </form>
</div>
<div id="reg-panel" style="display: none;text-align: center">
    <form action="${pageContext.request.contextPath}/u/reg" method="post">
        <p>名称：<input type="text" name="name" required="required"></p>
        <p>密码：<input type="password" name="pass" required="required"></p>
        <p>邮箱：<input type="email" name="email" required="required"></p>
        <input type="hidden" name="redirect" value="${param.redirect}">
        <input type="submit" class="button" value="注册">
        <a class="button" href="#" id="btn-show-login-panel">直接登陆</a>
    </form>
</div>

<script>

    function initProperties(){
        $("#reg-panel").dialog({title:"注册",width:350,closeOnEscape:false,autoOpen:false});
        $("#login-panel").dialog({width:350,title:"登陆",closeOnEscape:false,autoOpen:false});
    }

    function showRegPanel(){
        $("#login-panel").dialog("close");
        $("#reg-panel").dialog("open");
    }

    function showLoginPanel(){
        $("#login-panel").dialog("open");
        $("#reg-panel").dialog("close");
    }

    $("#btn-show-reg-panel").click(showRegPanel);
    $("#btn-show-login-panel").click(showLoginPanel);

    initProperties();

    <%
    String pageMode = request.getParameter("page");
    if (pageMode!=null&&pageMode.equals("reg")){
    %>
        showRegPanel();
    <%}else {%>
        showLoginPanel();
    <%}%>
</script>

<%@include file="../tpls/MainScript.jsp"%>
</body>
</html>