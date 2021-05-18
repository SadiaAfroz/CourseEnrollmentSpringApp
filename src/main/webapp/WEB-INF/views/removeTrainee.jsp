<%--
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 5/18/21
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<table class="highlight centered responsive-table">
    <thead>
    <tr>
        <th>Trainee Id</th>
        <th>Trainee Name</th>
        <th>Trainee Email</th>
    </tr>
    </thead>
    <tbody>
    <p><span style="color: red; ">${messageremove}</span></p>
    <c:forEach items="${trainees}" var="t">
        <tr>
            <td><c:out value="${t.id}"/></td>
            <td><c:out value="${t.name}"/></td>
            <td><c:out value="${t.email}"/></td>
            <td>
                <form:form action="removetraineefromcourse/?traineeid=${t.id}&courseid=${coursedetails.id}"
                           method="post">
                    <button type="submit" class="btn">Enroll</button>
                </form:form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
