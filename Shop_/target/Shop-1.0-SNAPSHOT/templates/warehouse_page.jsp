<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница склада</title>
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
<h1>Склад</h1>
<div >
    <h4>Город: ${warehouse.city}</h4>
</div>
<div>
    <h4>Улица: ${warehouse.street}</h4>
</div>
<div>
    <h4>Всего продуктов на складе: ${quantity}</h4>
</div>
<h2>Учет продуктов</h2>
<table class="table table-bordered">
    <thead>
    <tr>
        <td>Продукт</td>
        <td>Количество</td>
        <td></td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="stock" items="${stocktaking}">
        <tr>
            <td>${stock.product.name}</td>
            <td>${stock.quantity_product}</td>
            <sf:form method="post" modelAttribute="modify_quantity">
                <td><sf:input path="quantity" cssClass="form-control"/></td>
                <td><sf:button value="${stock.id}" name="stocktaking"
                               class="btn btn-success">Изменить количество</sf:button></td>
            </sf:form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
