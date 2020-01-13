<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="bucket" scope="request" type="mate.academy.internetshop.model.Bucket"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
This is bucket
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>


    </tr>
    <c:forEach var="item" items="${bucket.items}">
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
            <a href="${pageContext.request.contextPath}/deleteItemFromBucket?bucket_id=${bucket.bucketId}&item_id=${item.itemId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<p><a href="${pageContext.request.contextPath}/completeOrder">COMPLETE ORDER</a></p>
<p><a href="index">Home</a></p>

</body>
</html>
