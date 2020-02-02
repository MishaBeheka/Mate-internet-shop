<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>${errorMsg}</div>
<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="container col-lg-4">
        <h1 class="text-center">LOGIN</h1>
        <p class="text-center">Please fill in this form to sign into account.</p>
        <hr>

        <div class="form-group">
            <label for="login"><b>Email</b></label>
            <input type="text" class="form-control" placeholder="Enter login" name="login" id="login" required>
        </div>

        <div class="form-group">
            <label for="psw"><b>Password</b></label>
            <input type="password" class="form-control" placeholder="Enter Password" name="psw" id="psw" required>
        </div>

        <hr>
        <button type="submit" class="registerbtn btn-primary btn-lg">Login</button>
        <hr>

        <div class="container signin">
            <p>Don't have an account? <a href="registrationUsers">Sign up</a>.</p>
        </div>
    </div>

</form>
</body>
</html>
