<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<html>
<head>
    <title>All users</title>
</head>
<body>
<div class="container">
    <h2>These are all users</h2>
    <table class="table table-bordered">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Login</th>
        </tr>
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
    <p><a href="${pageContext.request.contextPath}/servlet/index">Home</a></p>
</div>

</body>
</html>
