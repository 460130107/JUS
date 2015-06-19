<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理-JUS</title>
    ${jQueryHtml}${jQueryUIHtml}${mainStyleHtml}
</head>
<body>
<h1>用户管理</h1>
${mainMenuHtml}

<div class="ui-widget main-content">
    <div class="ui-widget-content" style="padding: 10px">
        <div>共${usersCount}个用户，每页呈现${perPageCount}条数据，共${maxPageCount}页，当前第${currentPageIndex+1}页</div>
        <table border="1">
            <tr><th>ID</th><th>用户名</th><th>邮箱</th></tr>
            <c:forEach items="${userList}" var="user">
                <tr><td>${user.id}</td><td>${user.name}</td><td>${user.email}</td></tr>
            </c:forEach>
        </table>

        <c:if test="${currentPageIndex>0||currentPageIndex<maxPageCount-1}">
            <div style="padding-top: 10px">
                <c:if test="${currentPageIndex>0}">
                    <a class="button" href="?pi=${currentPageIndex-1}">上一页</a>
                </c:if>
                <c:if test="${currentPageIndex<maxPageCount-1}">
                    <a class="button" href="?pi=${currentPageIndex+1}">下一页</a>
                </c:if>
            </div>
        </c:if>
    </div>
</div>

${mainJs}
</body>
</html>