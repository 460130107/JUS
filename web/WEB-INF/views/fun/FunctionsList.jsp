<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" session="false" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>功能列表 - JUS</title>
  <%@include file="../tpls/jQuery.jsp"%>
  <%@include file="../tpls/jQueryUI.jsp"%>
  <%@include file="../tpls/MainStyle.jsp"%>
</head>
<body>
<h1>功能列表</h1>
<%@include file="../tpls/NavMenu.jsp"%>

<div class="ui-widget main-content">
  <div class="ui-widget-content" style="padding: 10px">
    <ol>
      <c:forEach items="${functions}" var="funcName">
        <li>${funcName}</li>
      </c:forEach>
    </ol>
  </div>
</div>

<%@include file="../tpls/MainScript.jsp"%>
</body>
</html>