<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.ModuleModel" %>
<%@ page import="Models.StudentGradeModel" %><%--
  Created by IntelliJ IDEA.
  User: nicolaseuliarte
  Date: 19/10/2020
  Time: 13:53
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
    <title>Lecturer Home</title>
</head>
<body>
    <div class="container">
        <%--Teacher view form--%>
        <form action="/teacherView" method="post">
            <%--information needed to view lecture students--%>
            Teacher Email:<br><input type="email" name="teacher_email"><br>

            Module: <br>
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

            <!--Submit-->
            <input type="submit" value="Student Passes" class="grades">
        </form>

         <%--Viewing the student grades for the module selected--%>
        <div class="container signin">
            <%try{  String module = (String)request.getAttribute("module");%>
            <h1><% out.println("Module : " + module);%></h1><br>
            <%}catch(Exception e){ out.println("Please enter the information needed");}%>

            <table style="width:100%">

                <tr>
                    <th>Student ID</th>
                    <th>Grade 1</th>
                    <th>Grade 2</th>
                </tr>
                <%try{%>
                <% ArrayList<StudentGradeModel> grades = (ArrayList) request.getAttribute("grades");
                    for(StudentGradeModel grade : grades) { %>
                <tr>
                    <td><% out.println(grade.getStudent_Number());%></td>
                    <td><% out.println(grade.getGrade1());%></td>
                    <td><% out.println(grade.getGrade2());%></td>
                </tr>

                <%} }catch(Exception e){}%>
            </table>

        </div>

    <%--View passes form--%>
    <div class="container">
        <form action="/teacherViewClass" method="post">
            <input type="submit" value="Module Passes" class="grades">
        </form>
    </div>

        <!--redirect to other pages-->
        <div class="container signin">
            <a href="/teacherViewSpecific.jsp">Student View</a>
            <a href="/addGrade.jsp">Update Student Grade</a>
            <a href="/login.jsp">Logout</a>
        </div>
    </div>

    <%--Viewing the student passes for all the module selected--%>
    <div class="container signin">
        <table style="width:100%">
            <tr>
                <th>Module Code</th>
                <th>Passes</th>
            </tr>
            <%try{%>
            <% ArrayList<ModuleModel> passes = (ArrayList) request.getAttribute("passes");
                for(ModuleModel pass : passes) { %>
            <tr>
                <td><% out.println(pass.getModule_Code());%></td>
                <td><% out.println(pass.getPasses());%></td>
            </tr>

            <%} }catch(Exception e){}%>
        </table>
    </div>

</body>
</html>
