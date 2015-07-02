<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <c:if test="${errorCode==0}">
      <form:form action="${pageContext.request.contextPath}/u/ep?uid=${user.id}" method="post" modelAttribute="user">
        <p>用户ID：${user.id}，注册时间：${user.regtime}，最后登陆：${user.lastlogtime}</p>
        <input type="hidden" name="id" value="${user.id}">
        <p>帐号：<form:input path="name"/></p>
        <p>呢称：<form:input path="nicename"/></p>
        <p>电话：<form:input path="phone" type="phone"/></p>
        <p>邮箱：<form:input path="email" type="email"/></p>
        <p>主页：<form:input path="url"/></p>
        <p>状态：<form:input path="status" type="number"/></p>
        <input type="submit" class="button" value="保存">
      </form:form>
    </c:if>
    <c:if test="${errorCode==100000}">
      <c:out value="没有指定用户ID"/>
    </c:if>
    <c:if test="${errorCode==100001}">
      <c:out value="数据库中没有该用户"/>
    </c:if>
  </div>
</div>

<%@include file="../tpls/MainScript.jsp"%>
</body>
</html>