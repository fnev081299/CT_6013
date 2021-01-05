<%--
  Created by IntelliJ IDEA.
  User: nicolaseuliarte
  Date: 16/10/2020
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        /*https://www.w3schools.com/howto/howto_css_register_form.asp*/
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: lightgray;
        }

        * {
            box-sizing: border-box;
        }

        /* Add padding to containers */
        .container {
            padding: 16px;
            background-color: white;
        }

        /* Full-width input fields */
        input[type=text], input[type=password], input[type=email] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus, input[type=password]:focus, input[type=email]:focus {
            background-color: #ddd;
            outline: none;
        }

        /* Overwrite default styles of hr */
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

        /* Set a style for the submit button */
        .Login {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        .Login:hover {
            opacity: 1;
        }

        /* Add a blue text color to links */
        a {
            color: dodgerblue;
        }

        /* Set a grey background color and center the text of the "sign in" section */
        .signin {
            background-color: #f1f1f1;
            text-align: center;
        }
    </style>
    <title>Login</title>
    <meta charset="utf-8">
</head>
<body>
<div class="container">
    <h1>Login</h1><br>

    <%--Login form--%>
    <form action="/login" method="post">

        <%--Information needed--%>
        <div class="textbox">
            <input placeholder="Email" type="email" name="email">
        </div>
        <div class="textbox">
            <input placeholder="Password" type="password" name="password">
        </div>

            <%--Student or staff--%>
        <div class="textbox">
            <select name="selection1" id="selection1" class="selection1">
                <option value="student">Student</option>
                <option value="teacher">Staff</option>
            </select>
        </div>

        <!--Submit-->
        <input class="Login" type="submit" value="Login">
    </form>
</div>

    <!--redirect to other pages-->
    <div class="container signin">
        <!--To get to the registration pages use -
        http://localhost:8080/registration.jsp and
        http://localhost:8080/teacherRegistration.jsp -->

        <!-- <a href="/registration.jsp">Student Registration</a>
        <a href="/teacherRegistration.jsp">Teacher Registration</a> -->
    </div>
</body>
</html>
