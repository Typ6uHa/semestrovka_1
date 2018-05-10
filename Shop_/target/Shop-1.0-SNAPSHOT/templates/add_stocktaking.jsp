<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление учетной записи</title>
    <style>
        <%@include file='css/add.css' %>
    </style>
</head>
<body>
<div class="login-page">
    <div class="form">
        <h1>Добавление учетной записи</h1>
        <sf:form method="post" modelAttribute="stocktaking_form" cssClass="login-form">
            <sf:select path="product" placeholder="Продукт" cssClass="form_component">
                <core:forEach var="product" items="${products}">
                    <sf:option value="${product.id}">${product.name}, ${product.type}, ${product.price} рублей</sf:option>
                </core:forEach>
            </sf:select>
            <sf:select path="warehouse" placeholder="Склад" cssClass="form_component">
                <core:forEach var="warehouse" items="${warehouses}">
                    <sf:option value="${warehouse.id}">${warehouse.city}, ${warehouse.street}</sf:option>
                </core:forEach>
            </sf:select>
            <sf:input path="quantity" cssClass="form_component"/>
            <button type="submit">Добавить</button>
            <h6><a href="/admin/main">Админка</a></h6>
            <h6><a href="/admin/warehouse/all">Склады</a></h6>
        </sf:form>
    </div>
</div>
</body>
</html>
