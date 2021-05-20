<%--
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 5/15/21
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CourseApp</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col m6 offset-m3">
            <div>
                <a href="<c:url value="/course"/>">Add New Course</a><br>
                <a href="<c:url value="/trainee"/>">Add New Trainee</a><br>
                <a href="<c:url value="/courselist"/>">View All Courses</a><br>
                <a href="<c:url value="/traineelist"/>">View All Trianess</a><br>
                <a href="<c:url value="/logout"/>">Logout</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
