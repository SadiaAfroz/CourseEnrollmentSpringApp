<%--
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 5/15/21
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
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
                    <form:form action="traineedetailsbycourseid" modelAttribute="coursedetails">
                        <h1>Trainee details by Courseid: </h1><br>
                        Enter course id : <form:input type="text" path="id"/><br>
                        <form:errors path="id"/><br>
                        <button type="submit" class="btn">Submit</button>
                    </form:form>
                    <p><span style="color: red; ">${messagetrainee}</span></p>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form:form action="courses/?id=${coursedetails.id}" method="post" modelAttribute="coursedetails">
                        <h1>Save or Update a Course: </h1><br>
                        Enter course Title : <form:input type="text" path="title" required="required"/><br>
                        <button type="submit" class="btn">Save</button>
                    </form:form>
                    <p><span style="color: red; "> ${messagesaveorupdate}</span></p>
                </div>
            </div>
        </div>
<%--        <div class="col l6">--%>
<%--            <div class="card yellow lighten-3">--%>
<%--                <div class="card-content">--%>
<%--                    <form:form action="courses" method="post" modelAttribute="coursedetails">--%>
<%--                        <h1>Update Course Title: </h1><br>--%>
<%--&lt;%&ndash;                        Enter course id : <form:input type="text" path="id" required="required"/></br>&ndash;%&gt;--%>
<%--                        Enter new Title : <form:input type="text" path="title" required="required"/></br>--%>
<%--                        <button type="submit" class="btn">Update</button>--%>
<%--                    </form:form>--%>
<%--                    <p><span style="color: red; ">${messageupdatecoursetitle}</span></p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form:form action="removecourse" method="post" modelAttribute="coursedetails">
                        <h1>Remove Course:</h1><br>
                        Enter course id : <form:input type="text" path="id" required="required"/><br>
                        <button type="submit" class="btn">Remove</button>
                    </form:form>
                    <p><span style="color: red; ">${messageremovecourse}</span></p>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form:form action="getcourses" method="get">
                        <h1>Get All Courses: </h1><br>
                        <button type="submit" class="btn">Get Courses</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
