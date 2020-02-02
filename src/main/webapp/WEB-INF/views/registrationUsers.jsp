<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>Registration of User</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/registrationUsers" method="post">
        <div class="container col-lg-4">
            <h2 class="text-center">REGISTRATION</h2>
            <p class="text-center">Please fill in this form to create an account.</p>
            <hr>
            <div class="form-group">
                <label for="first_name"><b>First Name</b></label>
                <input type="text" class="form-control" placeholder="Enter First Name" name="first_name" id="first_name" required>
            </div>

            <div class="form-group">
                <label for="last_name"><b>Last Name</b></label>
                <input type="text" class="form-control" placeholder="Enter Last Name" name="last_name" id="last_name" required>
            </div>

            <div class="form-group">
                <label for="email"><b>Email</b></label>
                <input type="text" class="form-control" placeholder="Enter email" name="email" id="email" required>
            </div>

            <div class="form-group">
                <label for="psw"><b>Password</b></label>
                <input type="password" class="form-control" placeholder="Enter Password" name="psw" id="psw" required>
            </div>

            <div class="form-group">
                <label for="psw-repeat"><b>Repeat Password</b></label>
                <input type="password" class="form-control" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>
            </div>
            <hr>
            <button type="submit" class="btn btn-lg btn-primary btn-block">Register</button>

            <div class="container signin">
                <p>Already have an account? <a href="login">Sign in</a>.</p>
            </div>
        </div>
    </form>
</body>
</html>
