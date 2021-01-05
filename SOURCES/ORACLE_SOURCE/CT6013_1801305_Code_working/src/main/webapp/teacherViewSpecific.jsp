<%--
  Created by IntelliJ IDEA.
  User: nicolaseuliarte
  Date: 26/10/2020
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--Styling--%>
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
    </style>
    <title>Find Student</title>
</head>
<body>
    <div class="container">
        <!--lecturer view specific student form-->
        <form action="/teacherViewSpecific" method="post">

            <!--check lecturer is on module for student to be seen-->
            Teacher Email:<br><input type="email" name="teacher_email"><br>
            Module:<br>

            <!--Selecting module-->
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

            <!--student information-->
            Models.StudentModel ID:<br><input type="text" name="student_number"><br>

            <!--Submit-->
            <input type="submit" value="View Student Details" class="grades">
        </form>

        <!--redirect to other pages-->
        <div class="container signin">
            <a href="/teacherView.jsp">Home</a>
            <a href="/login.jsp">Logout</a>
        </div>
    </div>

    <!--table of student information for the module chosen-->
    <div class="container signin">
        <table style="width:100%">
            <tr>
                <th>Module</th>
                <th>Module Code</th>
                <th>Student Number</th>
                <th>Grade 1</th>
                <th>Grade 2</th>
            </tr>

            <%try{%>
            <tr>
                <td><% out.println(request.getAttribute("module"));%></td>
                <td><% out.println(request.getAttribute("courseCode"));%></td>
                <td><% out.println(request.getAttribute("studentNumber"));%></td>
                <td><% out.println(request.getAttribute("grade1"));%></td>
                <td><% out.println(request.getAttribute("grade2"));%></td>
            </tr>
            <%}catch(Exception e){
            }%>
        </table>

    </div>

</body>
</html>
