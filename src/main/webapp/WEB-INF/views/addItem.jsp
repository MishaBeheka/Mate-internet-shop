<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>Add item</title>
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
    <strong>ADD ITEMS IN THE SHOP</strong>
</div>
    <form class="form-group" action="/internet_shop_war_exploded/servlet/addItem" method="post">
        <div class="container col-lg-4">
            <p>Please fill in this form to create an item.</p>
            <hr>
            <div class="form-group">
                <label for="name_item"><b> Item</b></label>
                <input type="text" placeholder="Enter item" name="name_item" id="name_item" required>
            </div>
            <div class="form-group">
                <label for="price"><b>Price</b></label>
                <input type="number" placeholder="Enter price of item" name="price" id="price" required>
            </div>
            <hr>
            <button type="submit" class="btn btn-group-sm btn-primary">Add new item</button>
            <hr>
        </div>
    </form>

</body>
</html>
