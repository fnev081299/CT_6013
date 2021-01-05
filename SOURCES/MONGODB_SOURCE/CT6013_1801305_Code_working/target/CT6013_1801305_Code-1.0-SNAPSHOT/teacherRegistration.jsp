<%--
  Created by IntelliJ IDEA.
  User: nicolaseuliarte
  Date: 16/10/2020
  Time: 13:57
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
        .register {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        .register:hover {
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
    <title>Teacher Registration</title>
</head>
<body>
    <div class="container">
        <%--Teacher registration form--%>
        <form action="/teacherRegistration" method="post">

            <%--Lecturer Information--%>
            First Name: <br><input type="text" name="firstname"> <br>
            Last Name: <br><input type="text" name="lastname"> <br>
            Email: <br><input type="email" name="email"> <br>
            Password: <br><input type="password" name="password"> <br> <br>

            Selection 1: <br>
            <select name="selection1" id="selection1">
                <option value="CT6013">Advanced Databases</option>
                <option value="CT6046">Robotics</option>
                <option value="CT6042">Secure Coding</option>
                <option value="CT6027">Advanced Topics</option>
                <option value="CT6038">Dissertation Proposal</option>
                <option value="CT6039">Dissertation</option>
                <option value="CT5022">Project Management</option>
                <option value="CT5038">Agile</option>
                <option value="CT5055">Artificial Intelligence</option>
                <option value="CT5057">Algorithms</option>
                <option value="CT5025">OOP</option>
                <option value="CT4009">Web Development</option>
                <option value="CT4021">Intro to Programming</option>
                <option value="CT4023">System Design</option>
                <option value="CT4020">Smart Business</option>
                <option value="CT4022">Intro to Security</option>
            </select> <br><br>

            Selection 2: <br>
            <select name="selection2" id="selection2">
                <option value="CT6013">Advanced Databases</option>
                <option value="CT6046">Robotics</option>
                <option value="CT6042">Secure Coding</option>
                <option value="CT6027">Advanced Topics</option>
                <option value="CT6038">Dissertation Proposal</option>
                <option value="CT6039">Dissertation</option>
                <option value="CT5022">Project Management</option>
                <option value="CT5038">Agile</option>
                <option value="CT5055">Artificial Intelligence</option>
                <option value="CT5057">Algorithms</option>
                <option value="CT5025">OOP</option>
                <option value="CT4009">Web Development</option>
                <option value="CT4021">Intro to Programming</option>
                <option value="CT4023">System Design</option>
                <option value="CT4020">Smart Business</option>
                <option value="CT4022">Intro to Security</option>
            </select><br><br>

            Selection 3: <br>
            <select name="selection3" id="selection3">
                <option value="CT6013">Advanced Databases</option>
                <option value="CT6046">Robotics</option>
                <option value="CT6042">Secure Coding</option>
                <option value="CT6027">Advanced Topics</option>
                <option value="CT6038">Dissertation Proposal</option>
                <option value="CT6039">Dissertation</option>
                <option value="CT5022">Project Management</option>
                <option value="CT5038">Agile</option>
                <option value="CT5055">Artificial Intelligence</option>
                <option value="CT5057">Algorithms</option>
                <option value="CT5025">OOP</option>
                <option value="CT4009">Web Development</option>
                <option value="CT4021">Intro to Programming</option>
                <option value="CT4023">System Design</option>
                <option value="CT4020">Smart Business</option>
                <option value="CT4022">Intro to Security</option>
            </select><br><br>

            Selection 4: <br>
            <select name="selection4" id="selection4">
                <option value="CT6013">Advanced Databases</option>
                <option value="CT6046">Robotics</option>
                <option value="CT6042">Secure Coding</option>
                <option value="CT6027">Advanced Topics</option>
                <option value="CT6038">Dissertation Proposal</option>
                <option value="CT6039">Dissertation</option>
                <option value="CT5022">Project Management</option>
                <option value="CT5038">Agile</option>
                <option value="CT5055">Artificial Intelligence</option>
                <option value="CT5057">Algorithms</option>
                <option value="CT5025">OOP</option>
                <option value="CT4009">Web Development</option>
                <option value="CT4021">Intro to Programming</option>
                <option value="CT4023">System Design</option>
                <option value="CT4020">Smart Business</option>
                <option value="CT4022">Intro to Security</option>
            </select><br><br>

            Selection 5: <br>
            <select name="selection5" id="selection5">
                <option value="CT6013">Advanced Databases</option>
                <option value="CT6046">Robotics</option>
                <option value="CT6042">Secure Coding</option>
                <option value="CT6027">Advanced Topics</option>
                <option value="CT6038">Dissertation Proposal</option>
                <option value="CT6039">Dissertation</option>
                <option value="CT5022">Project Management</option>
                <option value="CT5038">Agile</option>
                <option value="CT5055">Artificial Intelligence</option>
                <option value="CT5057">Algorithms</option>
                <option value="CT5025">OOP</option>
                <option value="CT4009">Web Development</option>
                <option value="CT4021">Intro to Programming</option>
                <option value="CT4023">System Design</option>
                <option value="CT4020">Smart Business</option>
                <option value="CT4022">Intro to Security</option>
            </select><br><br>

            <!--Submit-->
            <input type="submit" value="Register" class="register">

        </form>
    </div>

    <!--redirect to other pages-->
    <div class="container signin">
        <a href="/registration.jsp">Student Registration</a><br>
        <a href="/login.jsp">Login</a><br>
    </div>

</body>
</html>
