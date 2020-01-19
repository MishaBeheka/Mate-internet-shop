<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<html>
<head>
    <title>Access denied</title>
</head>
<body>
<div class="alert alert-danger">
    <strong>Danger!</strong> Sorry but requested is denied for you :(
</div>

<div class="form-inline">
    <p>
        <a href="${pageContext.request.contextPath}/servlet/index">Home</a>
        <a href="${pageContext.request.contextPath}/login">Login</a>
    </p>
</div>
</body>
</html>
