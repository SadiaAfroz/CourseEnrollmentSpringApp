<%--
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 4/27/21
  Time: 12:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>CourseApp</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</head>
<body style="background: url('img/single_book.jpg'); background-size: cover; background-attachment: fixed;">
<div class="container">
    <div class="row">
        <div class="col m6 offset-m3">
            <div class="card">
                <div class="card-content">
                    <h1>
                        Login
                    </h1>
                    <div class="form center-align">
                        <p><span style="color: red; ">${errorMessage}</span></p>
                        <form:form action="login" method="post" modelAttribute="admin">
                            <spring:message code="login.usernamePrompt" var="usernamePrompt"/>
                            <spring:message code="login.passwordPrompt" var="passwordPrompt"/>
                            Enter username: <form:input type="text" path="username" placeholder="${usernamePrompt}"/><br>
                            <form:errors path="username"/><br>
                            Enter password: <form:input type="password" path="password" placeholder="${passwordPrompt}"/><br>
                            <form:errors path="password"/><br>
                            <button type="submit" class="btn">Login</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col m6 offset-m3">
            <div>
                <div class="form center-align">
                    <form:form action="getallcourses" method="get">
                        <button type="submit" class="btn">View All Courses</button>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col m6 offset-m3">
            <div>
                <div class="form center-align">
                    <form:form action="getalltrainees" method="get">
                        <button type="submit" class="btn">View All Trainees</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
