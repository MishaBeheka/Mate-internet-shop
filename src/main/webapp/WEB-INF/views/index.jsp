<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>Index</title>
</head>
<body>
<div class="jumbotron text-center" style="margin-bottom:0">
    <h1>It is a simple model web-store</h1>
    <p>I wish you good shopping;)</p>
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
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
</body>
</html>
