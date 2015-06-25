<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" session="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>JUS(Java Users System)</title>

    <%@include file="tpls/MainStyle.jsp"%>
    <%@include file="tpls/jQuery.jsp"%>
    <%@include file="tpls/jQueryUI.jsp"%>
</head>
<body>
<h1>JUS(Java Users System)</h1>
<%@include file="tpls/NavMenu.jsp"%>
<div class="ui-widget main-content">
    <div class="ui-widget-content" style="padding: 10px">
        欢迎使用JUS！<br>
    </div>
</div>
<%@include file="tpls/MainScript.jsp"%>
</body>
</html>