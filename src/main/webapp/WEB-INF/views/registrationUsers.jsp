<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration of User</title>
</head>
<body>
Let's create a new User
<form action="/internet_shop_war_exploded/registrationUsers" method="post">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="first_name"><b>First Name</b></label>
        <input type="text" placeholder="Enter First Name" name="first_name" required>

        <label for="last_name"><b>Last Name</b></label>
        <input type="text" placeholder="Enter Last Name" name="last_name" required>

        <label for="address"><b>Address</b></label>
        <input type="text" placeholder="Enter address" name="address" required>

        <label for="phone"><b>Phone</b></label>
        <input type="text" placeholder="Enter phone" name="phone" required>

        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Enter email" name="email" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <label for="psw-repeat"><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
        <hr>

        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <button type="submit" class="registerbtn">Register</button>
    </div>

    <div class="container signin">
        <p>Already have an account? <a href="#">Sign in</a>.</p>
    </div>
</form>
</body>
</html>
