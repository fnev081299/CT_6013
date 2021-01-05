<%@ page import="Models.StudentGradeModel" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: nicolaseuliarte
  Date: 23/10/2020
  Time: 13:54
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
    <title>Student Home</title>
</head>
<body>
    <div class="registration-box">
        <%--Strudent home form--%>
        <form action="/student_home" method="post">
            <%--Student id for checking grades--%>
            Student ID: <br><input type="text" name="student_id"> <br>

            <!--Submit-->
            <input type="submit" value="Student Transcript" class="grades">
        </form>
    </div>

    <!--redirect to other pages-->
    <div class="container signin">
        <a href="/login.jsp" class="login">Logout</a>
    </div>

    <%--table for grades--%>
    <div class="container signin">
        <%try{  String studentNumber = (String)request.getAttribute("student");%>

        <%--get the student id and average grade--%>
        <h1><% out.println("Student ID : " + studentNumber);%></h1><br>

        <%--catch possible errors--%>
        <%}catch(Exception e){ out.println("Please enter your student ID");}%>

        <table style="width:100%">
            <tr>
                <th>Module</th>
                <th>Code</th>
                <th>Grade 1</th>
                <th>Grade 2</th>
            </tr>

            <%--Show student grade information--%>
            <%try{%>
            <% ArrayList<StudentGradeModel> grades = (ArrayList) request.getAttribute("grades");
                for(StudentGradeModel grade : grades) { %>
            <tr>
                <td><% out.println(grade.getModuleName());%></td>
                <td><% out.println(grade.getModule_Code());%></td>
                <td><% out.println(grade.getGrade1());%></td>
                <td><% out.println(grade.getGrade2());%></td>
            </tr>

            <%} }catch(Exception e){}%>
        </table>

    </div>
</body>
</html>
