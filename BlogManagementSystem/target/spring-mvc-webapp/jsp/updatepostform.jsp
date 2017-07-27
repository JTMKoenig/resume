<%-- 
    Document   : editpostform
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
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="css/bootstrap-theme.css" type="text/css">

        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Syncopate" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">

        <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=58l4eygxylx2nw9uky1d52tlzhkabmm54ihelc0gjjfj4o3t"></script>  
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
            
            <h1>Edit Post</h1>

            <form class="form-group" id="newPostForm" name="newPostForm" modelAttribute="newPost" method="POST" action="${pageContext.request.contextPath}/updatepost">

                <div class="row">

                    <div class="col-md-4">
                        <label for="newPostTitle">Title: </label>
                        <input class="form-control" id="newPostTitle" name="newPostTitle">

                        <label for="newPostUserName">Author: </label>
                        <input class="form-control" id="newPostUserName" name="newPostUserName">

                        <label for="newPostStartDate">Start Date: </label>
                        <input class="form-control" id="newPostStartDate" name="newPostStartDate">

                        <label for="newPostEndDate">End Date: </label>
                        <input class="form-control" id="newPostEndDate" name="newPostEndDate">

                        <label for="newPostCategory">Category(s): </label>
                        <input class="form-control" id="newPostCategory" name="newPostCategory">

                    </div>

                    <div class="col-md-6">
                        <img style="max-height: 300px; box-shadow: 5px 5px 5px #888888" src="http://aboutvideo.info/wp-content/uploads/2017/02/Ghost-In-The-Shell-movie-trailer-2017-min.jpg">
                    </div>
                    <div class="col-md-2">
                        <a class="btn btn-block btn-default" href="#">Upload Image</a>
                    </div>

                </div>

        </div>





    <br>
    <div class="container-fluid">
        <textarea id="newPostBody" name="newPostBody"> </textarea>
            <input style="width:33%" type="submit" class="btn btn-block btn-default" value="Submit for Review">
    <br>
    <br>
        
    </div>
    <hr>
    <br>
    <input style="width:33%" type="submit" class="btn btn-block btn-default" value="Submit for Review">
    <br>
    <br>


</form>



<script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
<script src="js/bootstrap.js"></script>
<script src="js/home.js"></script>
<script
    src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"
    integrity="sha256-xI/qyl9vpwWFOXz7+x/9WkG5j/SVnSw21viy8fWwbeE="
crossorigin="anonymous"></script>
</body>
</html>
