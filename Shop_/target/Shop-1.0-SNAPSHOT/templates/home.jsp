<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        <%@include file="css/home.css" %>
    </style>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Shop</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/product/all">Каталог</a></li>
            <li><a href="/basket/">Корзина</a></li>
            <li><a href="/orders/">Заказы</a></li>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="/admin/main">Админка</a></li>
            </security:authorize>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <security:authorize access="isAnonymous()">
                <li><a href="registration">Регистрация</a></li>
                <li><a href="login">Вход</a></li>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
            <li><a href="/logout">Выход</a></li>
            </security:authorize>
        </ul>
    </div>
</nav>
</body>
</html>