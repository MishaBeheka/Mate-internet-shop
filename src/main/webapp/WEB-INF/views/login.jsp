
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
It is login
<form action="/internet_shop_war_exploded/login" method="post">
    <div class="container">
        <h1>Login</h1>
        <p>Please fill in this form to sign into account.</p>
        <hr>

        <label for="login"><b>Email</b></label>
        <input type="text" placeholder="Enter login" name="login" id="login" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" id="psw" required>

        <hr>
        <button type="submit" class="registerbtn">Login</button>
    </div>

    <div class="container signin">
        <p>Don't have an account? <a href="#">Sign up</a>.</p>
    </div>
</form>
</body>
</html>
