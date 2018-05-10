<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить склад</title>
    <style>
        <%@include file='css/add.css' %>
    </style>
</head>
<body>
<div class="login-page">
    <div class="form">
        <h1>Добавление склада</h1>
        <sf:form method="post" modelAttribute="warehouse_form" cssClass="login-form">
            <sf:input path="city" placeholder="Город" cssClass="form_component"/>
            <div class="error">
                <sf:errors path="city"/>
            </div>
            <sf:input path="street" placeholder="Улица" cssClass="form_component"/>
            <div class="error">
                <sf:errors path="street"/>
            </div>
            <button type="submit">Добавить</button>
            <h6><a href="/admin/main">Админка</a></h6>
            <h6><a href="/admin/warehouse/all">Склады</a></h6>
        </sf:form>
    </div>
</div>
</body>
</html>
