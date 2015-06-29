<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" session="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理-JUS</title>
    <%@include file="../tpls/jQuery.jsp"%>
    <%@include file="../tpls/jQueryUI.jsp"%>
    <%@include file="../tpls/MainStyle.jsp"%>
</head>
<body>
<h1>用户管理</h1>
<%@include file="../tpls/NavMenu.jsp"%>
<div class="ui-widget main-content">
    <div class="ui-widget-content" style="padding: 10px">
        <div style="padding-bottom: 10px">
            共${usersCount}个用户，每页呈现${perPageCount}条数据，共${maxPageCount}页，当前第${currentPageIndex+1}页。
            <a href="#" class="button" id="btn-add-user">添加用户</a>
        </div>
        <table border="1" width="100%">
            <tr><th>ID</th><th>用户名</th><th>邮箱</th><th width="120">操作</th></tr>
            <c:forEach items="${userList}" var="user">
                <tr><td>${user.id}</td><td>${user.name}</td><td>${user.email}</td><td style="text-align: center;"><a href="#" class="button">编辑属性</a></td></tr>
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

<div id="dialog-add-user" style="display: none;text-align: center">
    <form action="${pageContext.request.contextPath}/u/add" method="post">
        <p><span>用户：</span><input type="text" required="required" name="name"></p>
        <p><span>密码：</span><input type="password" required="required" name="pass"></p>
        <p><span>邮箱：</span><input type="email" required="required" name="email"></p>
        <input type="submit" value="提交" class="button">
    </form>
</div>

<script>
    $("#btn-add-user").click(function () {
        $("#dialog-add-user").dialog({modal:true,width:350,title:"添加用户"});
    });
</script>
<%@include file="../tpls/MainScript.jsp"%>
</body>
</html>