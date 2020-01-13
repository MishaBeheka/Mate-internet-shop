<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All items</title>
</head>
<body>
<h1>These are all items</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Item</th>
        <th>Price</th>
        <th>Add item to bucket</th>
    </tr>
    <c:forEach var = "item" items = "${items}">
        <tr>
            <td>
                <c:out value="${item.itemId}" />
            </td>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/addItemToBucket?item_id=${item.itemId}">Add in bucket</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href="index">Home</a></p>
<p><a href="bucket">Go to bucket</a></p>
</body>
</html>
