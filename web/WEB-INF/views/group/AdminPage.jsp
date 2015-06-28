<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户组管理</title>
    <%@include file="../tpls/jQuery.jsp"%>
    <%@include file="../tpls/jQueryUI.jsp"%>
    <%@include file="../tpls/MainStyle.jsp"%>
</head>
<body>
<h1>用户组管理</h1>
<%@include file="../tpls/NavMenu.jsp"%>

<div class="ui-widget main-content">
    <div class="ui-widget-content" style="padding: 10px">
        <div>共${groups.size()}条数据。<a href="#" class="button" id="btn-add-group">添加组</a></div>
        <table width="100%" border="1" style="margin-top: 10px">
            <tr><th>ID</th><th>名称</th><th>描述</th><th width="120">操作</th></tr>
            <c:forEach items="${groups}" var="group">
                <tr>
                    <td>${group.id}</td><td>${group.name}</td><td>${group.description}</td><td style="text-align: center"><a href="${pageContext.request.contextPath}/g/ep?gid=${group.id}" class="button">编辑权限</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div id="dialog-add-group" style="display: none;text-align: center">
    <form action="${pageContext.request.contextPath}/g/add" method="post">
        <p>组名：<input name="name" type="text" required="required"></p>
        <p>描述：<textarea name="desc" rows="5"></textarea></p>
        <input type="submit" class="button">
    </form>
</div>

<%@include file="../tpls/MainScript.jsp"%>

<script>
    $("#btn-add-group").click(function () {
        $("#dialog-add-group").dialog({width:350,title:"添加组",modal:true});
    });
</script>
</body>
</html>