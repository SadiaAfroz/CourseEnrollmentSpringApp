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
                    <form:form action="coursedetailsbytraineeid/?id=${traineedetails.id}" modelAttribute="traineedetails">
                        <h4>Course details by Traineeid: </h4><br>
                        <button type="submit" class="btn">Submit</button>
                    </form:form>
                    <p><span style="color: red; ">${messagecourse}</span></p>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form:form action="trainees/?id=${traineedetails.id}" method="post" modelAttribute="traineedetails">
                        <h5>Save or Update a Trainee: </h5><br>
                        Enter Trainee Name : <form:input type="text" path="name" required="required"/><br>
                        Enter Trainee Email : <form:input type="text" path="email" required="required"/><br>
                        <button type="submit" class="btn">Save</button>
                    </form:form>
                    <p><span style="color: red; "> ${messagesaveorupdate}</span></p>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form:form action="removetrainee/?id=${traineedetails.id}" method="post"
                               modelAttribute="traineedetails">
                        <h4>Remove Trainee : </h4><br>
                        <button type="submit" class="btn">Remove</button>
                        <p><span style="color: red; ">${messageremovetrainee}</span></p>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
