<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>All users</title>
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
    <strong>LIST OF USERS</strong>
</div>
<div class="container text-center">
    <table class="table table-bordered text-center">
        <thead class="thead-dark text-center">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Login</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.userId}"/>
                </td>
                <td>
                    <c:out value="${user.firstName}"/>
                </td>
                <td>
                    <c:out value="${user.lastName}"/>
                </td>
                <td>
                    <c:out value="${user.login}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
