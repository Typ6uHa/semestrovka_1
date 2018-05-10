<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
    <link data-require="bootstrap@3.2.0" data-semver="3.2.0" rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.css"/>
    <script data-require="bootstrap@3.2.0" data-semver="3.2.0"
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.js"></script>
    <script data-require="jquery@2.1.1" data-semver="2.1.1"
            src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://bootstrap-ru.com/204/assets/js/bootstrap-tab.js"></script>
    <script src="http://bootstrap-ru.com/204/assets/js/bootstrap-dropdown.js"></script>
    <style>
        <%@include file='css/ad.css' %>
    </style>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Shop</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/">Главная</a></li>
            <li><a href="/product/all">Каталог</a></li>
            <li><a href="/orders/">Заказы</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout">Выход</a></li>
        </ul>
    </div>
</nav>
<h1>Корзина</h1>
<div class="container">
    <table class="table table-bordered">
        <tr>
            <th>Название</th>
            <th>Категория</th>
            <th>Количество</th>
            <th>Цена</th>
        </tr>
        <div id="table">
            <core:forEach var="productInOrder" items="${productsInOrder}">
                <tr>
                    <td><a href="/product/${productInOrder.product.id}" class="btn btn-link">${productInOrder.product.name}</a></td>
                    <td>${productInOrder.product.type}</td>
                    <td><input type="button" id="minus${productInOrder.product.id}" value="-" onclick="minusCount(${productInOrder.product.id})">
                        <input id="${productInOrder.product.id}" type="text" value="${productInOrder.quantity}" disabled>
                        <input type="button" id="plus${productInOrder.product.id}" value="+" onclick="plusCount(${productInOrder.product.id})">
                        <p id="error" class="error"></p></td>
                    <td>${productInOrder.product.price} рублей</td>
                </tr>
            </core:forEach>
        </div>
    </table>
    <div class="row">
        <div class="col-md-6 col-md-offset-3"><sf:form method="post" modelAttribute="order_form">
            <sf:button value="${order.id}" name="order_id" class="btn btn-success">Оформить заказ</sf:button>
        </sf:form></div>
    </div>
</div>
<script type="text/javascript">

    function minusCount(id) {
        $.ajax({
            url : 'minus',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data : ({
                id: id,
                count: document.getElementById(id).value
            }),
            success: function (resp) {
                success(resp, id);
            }
        });
    }

    function plusCount(id) {
        $.ajax({
            url : 'plus',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data : ({
                id: id,
                count: document.getElementById(id).value
            }),
            success: function (resp) {
                success(resp, id);
            }
        });
    }

    function success(resp, id) {
        if (resp > 0) {
            $("#plus").disabled=false;
            $("#minus").disabled=false;
            $("#error").text("");
            document.getElementById(id).value=resp;
        }
        else if (resp === -1) {
            $("#plus").disabled=true;
            $("#error").text("Вы выбрали максимальное количество товара на складе");
        }
        else if (resp === -2) {
            $("#minus").disabled=true;
            $("#error").text("У вас минимальное количество товара");
        }
        else if (resp === 0) {
            $("#error").text("Что-то пошло не так");
        }
    }
</script>
</body>
</html>
