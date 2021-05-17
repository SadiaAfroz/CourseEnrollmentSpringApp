<%--
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 5/15/21
  Time: 6:23 PM
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
<body>
<div class="container">
    <div class="row">
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form:form action="enrolltrainee" method="post" >
                        <h1>Enroll New Trainee : </h1><br>
                        Enter trainee id = <input type="text" name="traineeid" required><br>
                        Enter course id = <input type="text" name="courseid" required><br>
                        <button type="submit" class="btn">Enroll</button>
                    </form:form>
                    <p><span style="color: red; "> <c:out value="${message}"/></span></p>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form:form action="removetraineefromcourse" method="post">
                        <h1>Remove Trainee From Course : </h1><br>
                        Enter trainee id = <input type="text" name="traineeid" required><br>
                        Enter course id = <input type="text" name="courseid" required><br>
                        <button type="submit" class="btn">Remove</button>
                    </form:form>
                    <p><span style="color: red; "> <c:out value="${message}"/></span></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
