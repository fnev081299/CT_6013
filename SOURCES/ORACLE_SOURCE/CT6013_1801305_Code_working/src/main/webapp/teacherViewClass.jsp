<%@ page import="Models.ModuleModel" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: nicolaseuliarte
  Date: 24/10/2020
  Time: 19:24
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
    <title>View Class</title>
</head>
<body>
    <div class="container">

        <%--lecturer view class form--%>
        <form action="/teacherViewClass" method="post">

            <%--Submit--%>
            <input type="submit" value="Student View" class="grades">
        </form>

        <!--redirect to other pages-->
        <div class="container signin">
            <a href="/teacherView.jsp">Home</a>
            <a href="/login.jsp">Logout</a>
        </div>
    </div>

    <!--table of modules and the number of student passes-->
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
