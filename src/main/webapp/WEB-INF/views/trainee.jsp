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

                    <form:form action="coursedetailsbytraineeid" modelAttribute="traineedetails">
                        <h1>Course details by Traineeid: </h1><br>
                        Enter trainee id : <form:input type="text" path="id"/><br>
                        <form:errors path="id"/><br>
                        <button type="submit" class="btn">Submit</button>
                    </form:form>
                    <p><span style="color: red; ">${messagecourse}</span></p>
                </div>
            </div>
        </div>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form:form action="trainees" method="post" modelAttribute="traineedetails">
                        <h1>Save or Update a Trainee: </h1><br>
                        Enter Trainee Name : <form:input type="text" path="name" required="required"/><br>
                        Enter Trainee Email : <form:input type="text" path="email" required="required"/><br>
                        <button type="submit" class="btn">Save</button>
                    </form:form>
                    <p><span style="color: red; "> ${messagesaveorupdate}</span></p>
                </div>
            </div>
        </div>
<%--        <div class="col l6">--%>
<%--            <div class="card yellow lighten-3">--%>
<%--                <div class="card-content">--%>
<%--                    <form:form action="updatetraineeemail" method="post" modelAttribute="traineedetails">--%>
<%--                        <h1>Update Trainee Email : </h1><br>--%>
<%--                        Enter trainee id : <form:input type="text" path="id" required="required"/></br>--%>
<%--                        Enter new email : <form:input type="text" path="email" required="required"/></br>--%>
<%--                        <button type="submit" class="btn">Update</button>--%>
<%--                        <p><span style="color: red; ">${messageupdatetraineeemail}</span></p>--%>
<%--                    </form:form>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col l6">--%>
<%--            <div class="card yellow lighten-3">--%>
<%--                <div class="card-content">--%>
<%--                    <form:form action="updatetraineename" method="post" modelAttribute="traineedetails">--%>
<%--                        <h1>Update Trainee Name : </h1><br>--%>
<%--                        Enter Trainee id : <form:input type="text" path="id" required="required"/></br>--%>
<%--                        Enter new Name : <form:input type="text" path="name" required="required"/></br>--%>
<%--                        <button type="submit" class="btn">Update</button>--%>
<%--                        <p><span style="color: red; ">${messageupdatetraineename}</span></p>--%>
<%--                    </form:form>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="col l6">
            <div class="card yellow lighten-3">
                <div class="card-content">
                    <form:form action="removetrainee" method="post" modelAttribute="traineedetails">
                        <h1>Remove Trainee : </h1><br>
                        Enter trainee id : <form:input type="text" path="id" required="required"/><br>
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
