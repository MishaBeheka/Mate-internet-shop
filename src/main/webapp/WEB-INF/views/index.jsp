<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<html>
<head>
    <title>Index</title>
</head>
<body>
<p><a href="getAllUsers">Show users</a></p>
<p><a href="addItem">Add item to shop</a></p>
<p><a href="getAllItems">Show items in the shop</a></p>
<p><a href="bucket">Show Bucket</a></p>
<p><a href="orders">Show Orders</a></p>
<p><a href="${pageContext.request.contextPath}/login">Login</a></p>
<p><a href="${pageContext.request.contextPath}/logout">Logout</a></p>
</body>
</html>
