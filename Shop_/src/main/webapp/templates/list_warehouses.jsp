<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Склады</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        <%@include file='css/ad.css' %>
    </style>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
    <ul class="nav navbar-nav">
        <div class="navbar-header">
            <a href="/" class="navbar-brand">Shop</a>
        </div>
        <li><a href="/admin/product/add">Добавление продукта</a></li>
        <li><a href="/admin/warehouse/add">Добавление склада</a></li>
        <li><a href="/admin/stocktaking/add">Добавление учетной записи</a></li>
        <li><a href="/admin/user/all">Список пользователей</a></li>
        <li><a href="/admin/product/all">Список продуктов</a></li>
        <li><a href="/admin/warehouse/all">Список складов</a></li>
        <li><a href="/admin/order/all">Список заказов</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="logout">Выход из админки</a></li>
    </ul>
</nav>
<div class="collapse navbar-collapse"><a class="btn btn-success" href="/admin/main">Админка</a>
    <a class="btn btn-success" href="/admin/warehouse/add">Добавить склад</a></div>
<h1>Доступные склады</h1>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>Город</th>
        <th>Улица</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <core:forEach var="war" items="${warehouses}">
        <tr>
                <td>${war.id}</td>
                <td>${war.city}</td>
                <td>${war.street}</td>
                <td>
                    <a href="/admin/warehouse/${war.id}" class="btn btn-success">Посмотреть склад</a>
                </td>
                <td><a href="/admin/warehouse/delete?id=${war.id}" class="btn btn-danger">Delete</a></td>
        </tr>
    </core:forEach>
    </tbody>
</table>
</body>
</html>
