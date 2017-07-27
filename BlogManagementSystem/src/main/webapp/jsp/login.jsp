<%--
    Document   : createaccount
    Created on : Jul 19, 2017, 4:33:25 PM
    Author     : vincentsiciliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jinja Login</title>

        <link href="${pageContext.request.contextPath}/css/jinjaStyle.css" type="text/css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css" type="text/css">

        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Syncopate" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>


    <body style="background-color:  #1b6d85">


        <div id="login-overlay" class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel">Login to <b style="font-family: Syncopate">JINJA</b></h4> or go back to our <a href="${pageContext.request.contextPath}/home">main site</a>.
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="well">

                                <form role="form" id="loginForm" method="POST" action="j_spring_security_check">
                                    <div class="form-group">
                                        <label for="j_username" class="control-label">Username</label>
                                        <input type="text" class="form-control" name="j_username" required title="Please enter your username" placeholder="username">
                                        <span class="help-block"></span>
                                    </div>
                                    <div class="form-group">
                                        <label for="j_password" class="control-label">Password</label>
                                        <input type="password" class="form-control" name="j_password" placeholder="password" required title="Please enter your password">
                                        <span class="help-block">

                                        </span>
                                    </div>
                                    <div id="loginErrorMsg" class="alert alert-error hide">
                                        <c:if test="${param.login_error == 1}">
                                            <h3>Wrong username or password!</h3>
                                        </c:if>
                                    </div>
                                    <!--
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="remember" id="remember"> Remember login
                                </label>
                                <p class="help-block">(if this is a private computer)</p>
                            </div>
                                    -->
                                    <button type="submit" value="login" name="submit" class="btn btn-success btn-block">Login</button>
                                </form>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <p class="lead">Register now for <span class="text-success">FREE</span></p>
                            <ul class="list-unstyled" style="line-height: 2">
                                <li><span class="fa fa-check text-success"></span> Get access to exclusive member deals</li>
                                <li><span class="fa fa-check text-success"></span> Learn the latest <b style="font-family: Syncopate">Jinja</b> tips</li>
                                <li><span class="fa fa-check text-success"></span> Access to community of developers</li>
                                <li><span class="fa fa-check text-success"></span> Latest Java content</li>
                                <li><span class="fa fa-check text-success"></span> Get a gift <small>(only new customers)</small></li>
                                <li><span class="fa fa-check text-success"></span> Holiday discounts up to 30% off</li>
                            </ul>
                            <p><a href="${pageContext.request.contextPath}/register" class="btn btn-info btn-block">Yes please, register now!</a></p>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <script type="text/javascript">

        </script>
















        <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/home.js"></script>
        <script
            src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"
            integrity="sha256-xI/qyl9vpwWFOXz7+x/9WkG5j/SVnSw21viy8fWwbeE="
        crossorigin="anonymous"></script>
    </body>
</html>
