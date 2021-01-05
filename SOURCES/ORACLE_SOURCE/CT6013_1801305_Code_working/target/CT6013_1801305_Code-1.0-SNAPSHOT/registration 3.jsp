<%--
  Created by IntelliJ IDEA.
  User: nicolaseuliarte
  Date: 09/10/2020
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
    <form action="/CT6013_1801305_Code-1.0-SNAPSHOT/registration" method="post">
        First Name: <br><input type="text" name="firstname"> <br>
        Last Name: <br><input type="text" name="lastname"> <br>
        Email: <br><input type="email" name="email"> <br>
        Password: <br><input type="password" name="password"> <br> <br>

        <input type="submit" value="Register">

    </form>

    <a href="/CT6013_1801305_Code-1.0-SNAPSHOT/teacherRegistration.jsp">Teacher Registration</a>
</body>
</html>
