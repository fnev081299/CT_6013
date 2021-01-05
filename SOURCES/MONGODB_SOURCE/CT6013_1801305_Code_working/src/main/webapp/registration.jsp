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
    <title>Registration Page</title>
</head>
<body>
    <div class="container">
        <%--Registration form--%>
        <form action="/registration" method="post">
            <%--Student information--%>
            <div id="name">
                <h2 class="name">Name</h2>
                First Name:<input type="text" name="firstname" class="firstname">
                Last Name: <input type="text" name="lastname" class="lastname"> <br>
            </div>
            <div id="school_info">
                <h2 class="name">Student Info</h2>
                Student ID: <input type="text" name="student_id" id="student_id">
                Year Group: <input type="text" name="year_group" id="year_group"> <br>
            </div>
            <div id="email_password">
                <h2 class="name">Login Details</h2>
                Email: <input type="email" name="email" id="email">
                Password: <input type="password" name="password" id="password"> <br>
            </div>
            <div id="selections">
                <h2 class="name">Module Registration</h2>

                Lesson Selection 1: <br>
                <select name="selection1" id="selection1" class="selection1">
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

                Lesson Selection 2: <br>
                <select name="selection2" id="selection2" class="selection2">
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

                Lesson Selection 3: <br>
                <select name="selection3" id="selection3" class="selection3">
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

                Lesson Selection 4: <br>
                <select name="selection4" id="selection4" class="selection4">
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

                Lesson Selection 5: <br>
                <select name="selection5" id="selection5" class="selection5">
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
            </div>

            <!--Submit-->
            <input type="submit" value="Enrol" class="register">

        </form>

        <!--redirect to other pages-->
        <div class="container signin">
            <a href="/teacherRegistration.jsp" class="teacher_registration">Teacher Registration</a>
            <a href="/login.jsp" class="login">Login</a>
        </div>

    </div>
</body>

</html>
