<%--
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 5/20/21
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                    <form:form action="trainee" method="post" modelAttribute="trainee">
                        <h1><spring:message code="messages.traineeTitlePrompt" text="default text" /></h1><br>
                        <form:hidden path="id" value="${id}"/>
                        <spring:message code="messages.traineeNameInput" text="Name" /><form:input type="text" path="name" /><br>
                        <spring:message code="messages.traineeEmailInput" text="Email" /><form:input type="text" path="email"/><br>
                        <button type="submit" class="btn">Save</button>
                    </form:form>
                    <p><span style="color: red; "><c:out value="${messagesaveorupdate}"/></span></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
