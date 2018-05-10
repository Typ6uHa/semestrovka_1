<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        <%@include file='css/ad.css' %>
    </style>
</head>
<body>
<h1>Пользователи</h1>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Email</th>
        <th>Роль</th>
        <th>Активность</th>
        <th></th>
        <th></th>
        <th></th>
        <th><a class="btn btn-success" href="/admin/main">Админка</a></th>
    </tr>
    </thead>

    <core:forEach var="user" items="${users}">
        <tbody>
        <tr>
            <sf:form method="post" modelAttribute="user_modify_form">
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td><core:if test="${user._confirm == false}">Пользователь не активен</core:if>
                    <core:if test="${user._confirm == true}">Пользователь активен</core:if></td>
                <td><sf:select path="role">
                    <sf:option value="ROLE_ADMIN">Admin</sf:option>
                    <sf:option value="ROLE_USER">User</sf:option>
                    <sf:option value="ROLE_ANONIM">Anonim</sf:option>
                </sf:select></td>

                <td><sf:select path="_confirm">
                    <sf:option value="true">Активен</sf:option>
                    <sf:option value="false">Не активен</sf:option>
                </sf:select></td>
                <td>
                    <sf:button value="${user.id}" name="user_id" class="btn btn-success">Изменить</sf:button>
                </td>
                <td><a class="btn btn-danger" href="/admin/user/delete?id=${user.id}">Удалить</a></td>

            </sf:form>
        </tr>
        </tbody>
    </core:forEach>
</table>
</body>
</html>
