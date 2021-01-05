<%--
  Created by IntelliJ IDEA.
  User: nicolaseuliarte
  Date: 16/10/2020
  Time: 17:09
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
        .grades {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        .grades:hover {
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

        /* Set CSS for table */
        tr:nth-child(even) {background-color: #f2f2f2;}

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
    <title>Grade Updating</title>
</head>
<body>

    <div class="container">

        <%--Add grade form--%>
        <form action="/addGrade" method="post">

            <%--Needed information--%>
            Teacher email: <br><input type="text" name="teacher_email"> <br>
            Student ID: <br><input type="text" name="student_id"> <br>

            Lesson Selection 1: <br>
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
            </select> <br>

            <!--Grades Updating-->
            Grade 1: <br><input type="text" name="grade1"> <br><br>
            Grade 2: <br><input type="text" name="grade2"> <br><br>

            <!--Submit-->
            <input type="submit" value="Update" class="grades">

        </form>
    </div>

    <!--redirect to other pages-->
    <div class="container signin">
        <a href="/teacherView.jsp">Home</a><br>
        <a href="/login.jsp">Logout</a><br>
    </div>

</body>
</html>
