<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>Access denied</title>
</head>
<body>
<div class="alert alert-danger">
    <strong>Danger!</strong> Sorry but requested page is denied for you :(
</div>

<div class="form-inline">
    <p>
        <a href="${pageContext.request.contextPath}/servlet/index">Home</a>
        <a href="${pageContext.request.contextPath}/login">Login</a>
    </p>
</div>
</body>
</html>
