<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration of User</title>
</head>
<body>
Let's create a new User
<form action="${pageContext.request.contextPath}/registrationUsers" method="post">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>
        <div>
            <label for="first_name"><b>First Name</b></label>
            <input type="text" placeholder="Enter First Name" name="first_name"  id="first_name" required>
        </div>

        <div>
            <label for="last_name"><b>Last Name</b></label>
            <input type="text" placeholder="Enter Last Name" name="last_name"  id="last_name" required>
        </div>

        <div>
            <label for="address"><b>Address</b></label>
            <input type="text" placeholder="Enter address" name="address" id="address" required>
        </div>

        <div>
            <label for="phone"><b>Phone</b></label>
            <input type="text" placeholder="Enter phone" name="phone" id="phone" required>
        </div>

        <div>
            <label for="email"><b>Email</b></label>
            <input type="text" placeholder="Enter email" name="email" id="email" required>
        </div>

        <div>
            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" id="psw" required>
        </div>

        <div>
            <label for="psw-repeat"><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>

        </div>
        <hr>

        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <button type="submit" class="registerbtn">Register</button>
        <button type="button"  class="btn">Basic</button>
    </div>

    <div class="container signin">
        <p>Already have an account? <a href="login">Sign in</a>.</p>
    </div>
</form>
</body>
</html>
