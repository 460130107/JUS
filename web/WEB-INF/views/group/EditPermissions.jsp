<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" session="false" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>编辑权限 - JUS</title>
  <%@include file="../tpls/jQuery.jsp"%>
  <%@include file="../tpls/jQueryUI.jsp"%>
  <%@include file="../tpls/MainStyle.jsp"%>
</head>
<body>
<h1>编辑权限</h1>
<%@include file="../tpls/NavMenu.jsp"%>

<div class="ui-widget main-content">
  <div class="ui-widget-content" style="padding: 10px">
    <form action="${pageContext.request.contextPath}/g/up" method="post">
      <ol>
        <c:forEach items="${functions}" var="funcName" varStatus="st">
          <li>
            <input type="hidden" name="gid" value="${param.gid}">
            <input type="checkbox" name="funcs"
            <c:if test="${names.contains(funcName)}">
                   checked="checked"
            </c:if> value="${funcName}" id="cb${st.index}"><label for="cb${st.index}">${funcName}</label>
          </li>
        </c:forEach>
      </ol>
      <input type="submit" class="button" value="保存修改">
    </form>
  </div>
</div>

<%@include file="../tpls/MainScript.jsp"%>
</body>
</html>