<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="bucket" scope="request" type="mate.academy.internetshop.model.Bucket"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>Bucket</title>
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
    <strong>BUCKET</strong>
</div>
<div class="container text-center">
    <table class="table table-bordered text-center">
        <thead class="thead-dark text-center">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Delete</th>
        </tr>
        </thead>
        <c:forEach var="item" items="${bucket.items}">
            <tr>
                <td>
                    <c:out value="${item.itemId}"/>
                </td>
                <td>
                    <c:out value="${item.name}"/>
                </td>
                <td>
                    <c:out value="${item.price}"/>
                </td>
                <td>
                    <a class="font-weight-bold" style="color: darkred"
                       href="${pageContext.request.contextPath}/servlet/deleteItemFromBucket?bucket_id=${bucket.bucketId}&item_id=${item.itemId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a class="font-weight-bold" href="${pageContext.request.contextPath}/servlet/completeOrder">COMPLETE ORDER</a>
    </p>
</div>
</body>
</html>
