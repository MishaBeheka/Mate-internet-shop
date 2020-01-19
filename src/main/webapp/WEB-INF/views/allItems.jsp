<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<html>
<head>
    <title>All items</title>
</head>
<body>
<div class="container">
    <h2>These are all items</h2>
    <table class="table table-bordered">
        <tr>
            <th>ID</th>
            <th>Item</th>
            <th>Price</th>
            <th>Add item to bucket</th>
        </tr>
        <tbody>
        <c:forEach var="item" items="${items}">
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
                    <a href="${pageContext.request.contextPath}/servlet/addItemToBucket?item_id=${item.itemId}">Add in bucket</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p><a href="${pageContext.request.contextPath}/servlet/index">Home</a></p>
    <p><a href="${pageContext.request.contextPath}/servlet/bucket">Go to bucket</a></p>
</div>
</body>
</html>
