<%--
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 5/19/21
  Time: 9:56 PM
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
        <div class="card-content">
            <form:form action="course" method="post" modelAttribute="course">
                <h1><spring:message code="messages.courseTitlePrompt"/></h1><br>
                <form:hidden path="id"/>
                <spring:message code="messages.courseTitleInput"/><form:input type="text" path="title"/>
                <form:errors path="title" cssClass="error"/><br>
                <button type="submit" class="btn"><spring:message code="messages.save"/></button>
            </form:form>
            <p><span style="color: red; "> <c:out value="${messagesaveorupdate}"/></span></p>
        </div>
    </div>
</div>
</body>
</html>
