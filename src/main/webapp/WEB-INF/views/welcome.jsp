<%--sesionid
  Created by IntelliJ IDEA.
  User: sadia.afroz
  Date: 4/27/21
  Time: 12:43 PM
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
<body style="background: url('/img/books.jpeg'); background-size: cover; background-attachment: fixed;">

<c:set var="url" value="/login"/>
<c:if test="${sessionScope.sesionid == null}">
    <c:redirect url="${url}"/>
</c:if>

<h1 class="white-text">Welcome</h1>
<div class="container">
    <div class="row">
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form action="coursedetailsbytraineeid">
                        <h1>Course details by Traineeid: </h1><br>
                        Enter trainee id : <input type="text" name="traineeid" required><br>
                        <button type="submit" class="btn">Submit</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form action="traineedetailsbycourseid">
                        <h1>Trainee details by Courseid: </h1><br>
                        Enter course id : <input type="text" name="courseid" required><br>
                        <button type="submit" class="btn">Submit</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form action="addnewcourse" method="post">
                        <h1>Add New Course: </h1><br>
                        Enter course Title : <input type="text" name="coursetitle" required><br>
                        <button type="submit" class="btn">Save</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form action="updatecoursetitle" method="post">
                        <h1>Update Course Title: </h1><br>
                        Enter course id : <input type="text" name="courseid" required></br>
                        Enter new Title : <input type="text" name="coursetitle" required></br>
                        <button type="submit" class="btn">Update</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form action="removecourse" method="post">
                        <h1>Remove Course:</h1><br>
                        Enter course id : <input type="text" name="courseid" required><br>
                        <button type="submit" class="btn">Remove</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form action="addnewtrainee" method="post">
                        <h1>Add New Trainee: </h1><br>
                        Enter Trainee Name : <input type="text" name="traineename" required><br>
                        Enter Trainee Email : <input type="text" name="traineeemail" required><br>
                        <button type="submit" class="btn">Save</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form action="updatetraineeemail" method="post">
                        <h1>Update Trainee Email : </h1><br>
                        Enter trainee id : <input type="text" name="traineeid" required></br>
                        Enter new email : <input type="text" name="traineeemail" required></br>
                        <button type="submit" class="btn">Update</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form action="updatetraineename" method="post">
                        <h1>Update Trainee Name : </h1><br>
                        Enter Trainee id : <input type="text" name="traineeid" required></br>
                        Enter new Name : <input type="text" name="traineename" required></br>
                        <button type="submit" class="btn">Update</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form action="removetrainee" method="post">
                        <h1>Remove Trainee : </h1><br>
                        Enter trainee id : <input type="text" name="traineeid" required><br>
                        <button type="submit" class="btn">Remove</button>
                    </form>
                </div>
            </div>
        </div>
    <div class="col l6">
        <div class="card yellow lighten-3">
            <div class="card-content">
                    <form action="enrolltrainee" method="post">
                        <h1>Enroll New Trainee : </h1><br>
                        Enter trainee id = <input type="text" name="traineeid" required><br>
                        Enter course id = <input type="text" name="courseid" required><br>
                        <button type="submit" class="btn">Enroll</button>
                    </form>
            </div>
        </div>
    </div>
    <div class="col l6">
        <div class="card yellow lighten-3">
            <div class="card-content">
                    <form action="removetraineefromcourse" method="post">
                        <h1>Remove Trainee From Course : </h1><br>
                        Enter trainee id = <input type="text" name="traineeid" required><br>
                        Enter course id = <input type="text" name="courseid" required><br>
                        <button type="submit" class="btn">Remove</button>
                    </form>
            </div>
        </div>
    </div>
    <div class="col l6">
        <div class="card yellow lighten-3">
            <div class="card-content">
                    <form action="getallcourses">
                        <h1>Get All Courses: </h1><br>
                        <button type="submit" class="btn">Get Courses</button>
                    </form>
            </div>
        </div>
    </div>
    <div class="col l6">
        <div class="card yellow lighten-3">
            <div class="card-content">
                    <form action="getalltrainees">
                        <h1>Get All Trainees: </h1><br>
                        <button type="submit" class="btn">Get Trainees</button>
                    </form>
            </div>
        </div>
    </div>
    <div class="col l12">
        <div class="card yellow lighten-2">
            <div class="card-content">
                    <form action="logout" class="center-align">
                        <button type="submit" class="btn">Logout</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
