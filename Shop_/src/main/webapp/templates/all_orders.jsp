<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заказы</title>
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
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="logout">Выход</a></li>
    </ul>
</nav>
<h1>Заказы пользователя</h1>
<table class="table table-bordered">
    <thead>
    <tr>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <th>ID</th>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_USER')">
            <th>Номер заказа</th>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_USER')">
            <th>Товары</th>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <th></th>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <th>Пользователь</th>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_USER')">
                <th></th>
            </security:authorize>
            <th></th>
        </security:authorize>
        <th>Тип</th>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <th></th>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_USER')">
            <th></th>
        </security:authorize>
    </tr>
    </thead>

    <core:forEach var="order" items="${orders}">
        <tbody>
        <tr>

            <td>${order.id}</td>

            <td><core:forEach var="productInOrder" items="${productInOrders}">
                <core:if test="${order.id == productInOrder.order.id}">
                    <h6><b>Название: </b>${productInOrder.product.name}
                        <b>Цена: </b>${productInOrder.product.price*productInOrder.quantity}
                        рублей <b>Тип: </b>${productInOrder.product.type}
                        <b>Количество: </b>${productInOrder.quantity}</h6>
                </core:if>
            </core:forEach></td>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <td>${order.user.email}</td>
            </security:authorize>
            <td>${order.typeOrder}</td>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <sf:form action="/admin/order/all" method="post" modelAttribute="order_form">
                    <td><sf:select path="typeOrder">
                        <sf:option value="SEARS">Поиск товара на складе</sf:option>
                        <sf:option value="COMPLETED">Товар готов</sf:option>
                    </sf:select></td>
                    <td>
                        <sf:button value="${order.id}" name="order_id" class="btn btn-success">Изменить</sf:button>
                    </td>
                </sf:form>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_USER')">
                <td><a class="btn btn-danger" href="/orders/delete?id=${order.id}">Отменить</a></td>
            </security:authorize>
        </tr>
        </tbody>
    </core:forEach>
</table>
</body>
</html>
