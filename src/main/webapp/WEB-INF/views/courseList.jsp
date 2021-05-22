<%@ page import="java.util.Set" %>
<%@ page import="net.therap.model.Course" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 4/27/21
  Time: 2:29 PM
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
        <th><spring:message code="messages.courseIdHeader"/></th>
        <th><spring:message code="messages.courseTitleHeader"/></th>
    </tr>
    </thead>
    <tbody>
    <c:set var="flag" value="${fullFlag}"/>
    <c:choose>
        <c:when test="${flag == '0'}">
            <c:forEach items="${courseList}" var="cr">
                <tr>
                    <td><c:out value="${cr.id}"/></td>
                    <td><c:out value="${cr.title}"/></td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p><span style="color: red; "><c:out value="${messageforaction}"/></span></p>
            <c:forEach items="${courseList}" var="cr">
                <tr>
                    <td><c:out value="${cr.id}"/></td>
                    <td><c:out value="${cr.title}"/></td>
                    <td>
                        <a class="waves-effect waves-light btn-small" href="<c:url value="course?id=${cr.id}"/>">
                            <spring:message code="messages.edit"/></a>
                        <a class="waves-effect waves-light btn-small" href="<c:url value="courseremove?id=${cr.id}"/>">
                            <spring:message code="messages.remove"/></a>
                        <a class="waves-effect waves-light btn-small" href="<c:url value="enrollment?id=${cr.id}"/>">
                            <spring:message code="messages.enrollTrainee"/></a>
                        <a class="waves-effect waves-light btn-small" href="<c:url value="removeenrollment?id=${cr.id}"/>">
                            <spring:message code="messages.removeTrainee"/></a>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<a class="waves-effect waves-light btn-small" href="welcome">
    <spring:message code="messages.home"/></a>
</body>
</html>
