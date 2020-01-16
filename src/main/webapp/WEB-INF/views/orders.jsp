<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="orders" scope="request" type="java.util.List<mate.academy.internetshop.model.Order>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
It is orders
<table border="1">
    <tr>
        <th>Order id</th>
        <th>Items</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.orderId}"/>
            </td>
            <td>
                <table border="1">
                    <tr>
                        <th>Item name</th>
                        <th>Price</th>
                    </tr>
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
                <a href="${pageContext.request.contextPath}/servlet/deleteOrder?order_id=${order.orderId}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href="${pageContext.request.contextPath}/servlet/index">Home</a></p>
</body>
</html>
