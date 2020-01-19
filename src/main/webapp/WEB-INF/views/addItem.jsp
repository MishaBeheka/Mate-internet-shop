<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add item</title>
</head>
<body>
<form action="/internet_shop_war_exploded/servlet/addItem" method="post">
    <div class="container">
        <h1>Add Item</h1>
        <p>Please fill in this form to create an item.</p>
        <hr>

        <label for="name_item"><b>New item</b></label>
        <input type="text" placeholder="Enter item" name="name_item" id="name_item" required>

        <label for="price"><b>Price</b></label>
        <input type="number" placeholder="Enter price of item" name="price" id="price" required>
        <hr>

        <button type="submit">Add new item</button>
    </div>

</form>
<p><a href="${pageContext.request.contextPath}/servlet/index">Home</a></p>
</body>
</html>
