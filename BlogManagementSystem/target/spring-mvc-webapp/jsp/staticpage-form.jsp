<%--
    Document   : newpostform
    Created on : Jul 19, 2017, 5:34:12 PM
    Author     : vincentsiciliano
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
        <title>New Post</title>

        <link href="${pageContext.request.contextPath}/css/jinjaStyle.css" type="text/css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css" type="text/css">

        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Syncopate" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">


        <link href="${pageContext.request.contextPath}/category/magicsuggest-min.css" rel="stylesheet">

        <script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/imageupload.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/jquery.tinymce.min.js"></script>
        <script>tinymce.init({selector: 'textarea'});</script>


    </head>
    <style>
        label{
            margin-bottom: -5px;
        }

        input{
            margin-bottom: 5px;
        }

    </style>


    <body style="background-color:whitesmoke">

        <%@include file="navbar-user.jsp" %>

        <div class="container-fluid">

            <h1>Create Static Page</h1>

            <form class="form-group" id="staticPageForm" name="staticPageForm" method="POST" action="${pageContext.request.contextPath}/createstaticpage">

                <div class="row">

                    <div class="col-md-4">
                        <label for="staticPageTitle">Title: </label>
                        <input class="form-control" id="staticPageTitle" name="staticPageTitle">

                    </div>

                    <div class="col-md-6">

                    </div>
                    <div class="col-md-2">

                    </div>

                </div>

                <div class ="row">
                    <div class="col-md-12">
                        <br/>
                        <textarea name="staticPageBody" id="editor" class="btn btn-block btn-default"></textarea>

                    </div>

                    <div class="col-md-offset-8 col-md-4">
                        <br/>
                        <input type="submit" class="btn btn-block btn-default" value="Submit Static Page">
                    </div>
                </div>
            </form>
        </div>







        <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
        <script src="js/bootstrap.js"></script>

        <script
            src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"
            integrity="sha256-xI/qyl9vpwWFOXz7+x/9WkG5j/SVnSw21viy8fWwbeE="
        crossorigin="anonymous"></script>




    </body>
</html>
