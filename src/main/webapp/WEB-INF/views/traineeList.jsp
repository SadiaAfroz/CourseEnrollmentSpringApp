<%@ page import="net.therap.model.Trainee" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 4/27/21
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>CourseApp</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>
<body>
<table class="highlight centered responsive-table">
    <thead>
    <tr>
        <th><spring:message code="messages.traineeIdHeader"/></th>
        <th><spring:message code="messages.traineeNameHeader"/></th>
        <th><spring:message code="messages.traineeEmailHeader"/></th>
    </tr>
    </thead>
    <tbody>
    <p><span style="color: red; "><c:out value="${messagefortraineeaction}"/></span></p>
    <c:forEach items="${traineeList}" var="t">
        <tr>
            <td><c:out value="${t.id}"/></td>
            <td><c:out value="${t.name}"/></td>
            <td><c:out value="${t.email}"/></td>
            <td><a class="waves-effect waves-light btn-small" href="<c:url value="trainee?id=${t.id}"/>">
                <spring:message code="messages.edit"/></a></td>
            <td><a class="waves-effect waves-light btn-small" href="<c:url value="traineeremove?id=${t.id}"/>">
                <spring:message code="messages.remove"/></a></td>
            <td><a class="waves-effect waves-light btn-small" href="<c:url value="showenrolledcourses?id=${t.id}"/>">Show
                Enrolled Courses</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a class="waves-effect waves-light btn-small" href="welcome">
    <spring:message code="messages.home"/></a>
</body>
</html>
