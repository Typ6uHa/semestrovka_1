<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить продукт</title>
    <style>
        <%@include file='css/add.css' %>
    </style>
</head>
<body>
<div class="login-page">
    <div class="form">
        <h1>Добавление товара</h1>
        <sf:form method="post" modelAttribute="product_form" cssClass="login-form">
            <sf:input path="name" placeholder="Название" cssClass="form_component"/>
            <div class="error">
                <sf:errors path="name"/>
            </div>
            <sf:textarea path="description" placeholder="Описание" cssClass="form_component"/>
            <div class="error">
                <sf:errors path="description"/>
            </div>
            <sf:input path="price" placeholder="Цена" cssClass="form_component"/>
            <div class="error">
                <sf:errors path="price"/>
            </div>
            <sf:select path="type" placeholder="Тип" cssClass="form_component">
                <sf:option value="Телефоны">Телефоны</sf:option>
                <sf:option value="Компьютеры">Компьютеры</sf:option>
                <sf:option value="Планшеты">Планшеты</sf:option>
                <sf:option value="Теревзоры">Теревзоры</sf:option>
                <sf:option value="Бытовая тнхника">Бытовая тнхника</sf:option>
                <sf:option value="Фотокамеры">Фотокамеры</sf:option>
                <sf:option value="Аудио">Аудио</sf:option>
                <sf:option value="Комплектующие для ПК">Комплектующие для ПК</sf:option>
            </sf:select>
            <button type="submit">Добавить</button>
            <h6><a href="/admin/main">Админка</a></h6>
            <h6><a href="/admin/product/all">Продукты</a></h6>
        </sf:form>
    </div>
</div>
</body>
</html>
