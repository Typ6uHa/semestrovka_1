
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Логин</title>
    <style>
        <%@include file='css/login.css' %>
    </style>
</head>
<body>
<c:if test="${not empty error}">
    <h2 class="error">Неправильные данные</h2>
</c:if>
<div class="login-page">
    <div class="form">
        <h1>Вход</h1>
        <form class="login-form" action="/login/process" method="post">
            <input type="text" name="login" placeholder="Логин" path="login"/>
            <input type="password" name="password" placeholder="Пароль" path="password"/>
            <button type="submit">Войти</button>
            <h6><a href="/registration">Регистрация</a></h6>
            <h6><a href="/">Главная</a></h6>
        </form>
    </div>
</div>
</body>
</html>
