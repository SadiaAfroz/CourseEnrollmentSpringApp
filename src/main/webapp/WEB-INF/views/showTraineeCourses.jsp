<%--
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 5/20/21
  Time: 11:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<table class="highlight centered responsive-table">
    <thead>
    <tr>
        <th>Course Id</th>
        <th>Course Title</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${courses}" var="cr">
        <tr>
            <td><c:out value="${cr.id}"/></td>
            <td><c:out value="${cr.title}"/></td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
