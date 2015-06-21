<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<c:if test="${success}">
    <%response.sendRedirect(request.getContextPath()+"/useradmin");%>
</c:if>
<c:if test="${!success}">
    <c:out value="添加用户失败"/>
</c:if>
</body>
</html>