<jsp:useBean id="error_msg" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>You have trouble with DB</title>
</head>
<body>
<p>You have trouble with DataBase</p>
<h2>${error_msg}</h2>
</body>
</html>
