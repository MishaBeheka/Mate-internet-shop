<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="orders" scope="request" type="java.util.List<mate.academy.internetshop.model.Order>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>Orders</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/servlet/index">HOME</a>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="getAllUsers"> LIST OF USERS </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="addItem"> ADD ITEMS IN THE SHOP </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="getAllItems"> SHOW ITEMS IN THE SHOP </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="bucket"> BUCKET </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="orders"> MY ORDERS </a>
            </li>
        </ul>
    </div>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/logout">LOGOUT</a>
</nav>
<div class="alert alert-info text-center">
    <strong>MY ORDERS</strong>
</div>
<div class="container text-center">
    <table class="table table-bordered text-center">
        <thead class="thead-dark text-center">
        <tr>
            <th>Order id</th>
            <th>Items</th>
            <th>Delete</th>
        </tr>
        </thead>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>
                    <c:out value="${order.orderId}"/>
                </td>
                <td>
                    <table class="table table-bordered text-center">
                        <thead class="thead-light text-center">
                        <tr>
                            <th>Item name</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <c:forEach var="item" items="${order.items}">
                            <tr>
                                <td>
                                    <c:out value="${item.name}"/>
                                </td>
                                <td>
                                    <c:out value="${item.price}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td>
                    <a class="font-weight-bold" style="color: darkred" href="${pageContext.request.contextPath}/servlet/deleteOrder?order_id=${order.orderId}">DELETE</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
