<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Админка</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        <%@include file='css/admin_page.css' %>
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
</body>
</html>
