<%--
    Document   : register
    Created on : Jul 19, 2017, 4:59:49 PM
    Author     : vincentsiciliano
--%>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <link href="${pageContext.request.contextPath}/css/jinjaStyle.css" type="text/css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css" type="text/css">

        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Syncopate" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1">


            <!-- Website CSS style -->
            <link href="css/bootstrap.min.css" rel="stylesheet">

            <!-- Website Font style -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
            <link rel="stylesheet" href="style.css">
            <!-- Google Fonts -->
            <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
            <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

            <title>Jinja Registration</title>
        </head>
        <body style="background-color:  #1b6d85">
            <div id="login-overlay" class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">Login to <b style="font-family: Syncopate">JINJA</b></h4> or go back to our <a href="${pageContext.request.contextPath}/home">main site</a>.
                    </div>
                    <div class="modal-body">
                        <h5>Register once and access all of our content.</h5>
                        <form class="" method="POST" action="${pageContext.request.contextPath}/createuser"  name="newUserForm" id="newUserForm" modelAttribute="newUser">

                            <div class="form-group">
                                <label for="name" class="cols-sm-2 control-label">Your Name</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="name" id="name"  placeholder="Enter your Name"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="email" class="cols-sm-2 control-label">Your Email</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="email" id="email"  placeholder="Enter your Email"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="userName" class="cols-sm-2 control-label">Username</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="userName" id="userName"  placeholder="Enter your Username"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="passWord" class="cols-sm-2 control-label">Password</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="password" class="form-control" name="passWord" id="password"  placeholder="Enter your Password"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="passWord" class="cols-sm-2 control-label">Confirm Password</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="passwordConfirm" class="form-control" name="passWordConfirm" id="confirm"  placeholder="Confirm your Password"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group ">
                                <input type="submit" id="button" class="btn btn-success btn-block" value="Register"/>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/home.js"></script>
    <script
        src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"
        integrity="sha256-xI/qyl9vpwWFOXz7+x/9WkG5j/SVnSw21viy8fWwbeE="
    crossorigin="anonymous"></script>

</body>
</html>
</body>
</html>
