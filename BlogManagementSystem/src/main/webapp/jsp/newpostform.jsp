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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">

        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Syncopate" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">


        <link href="${pageContext.request.contextPath}/category/magicsuggest-min.css" rel="stylesheet">

        <script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/imageupload.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/jquery.tinymce.min.js"></script>



    </head>
    <style>
        label{
            margin-bottom: -5px;
        }

        input{
            margin-bottom: 5px;
        }

        hr {
            border-width:10px;
        }

    </style>


    <body style="background-color:whitesmoke">

        <%@include file="navbar-user.jsp" %>

        <div class="container-fluid">

            <h1>Create Post</h1>
            <div class="row">


                <form id="newPostForm" name="newPostForm" method="POST" enctype="multipart/form-data">

                    <div class="col-md-8">
                        <textarea name="newPostBody" id="editor" class="btn btn-block btn-default"></textarea>
                    </div>

                    <div class="col-md-4">


                        <label for="newPostTitle">Title: </label>
                        <input class="form-control" id="newPostTitle" name="newPostTitle" required>

                        <label for="newPostUserName">Author: </label>
                        <input class="form-control" readonly value="${pageContext.request.userPrincipal.name}"/>

                        <label for="newPostStartDate">Start Date: </label>
                        <div class='input-group date' id='startDate'>
                            <input type="text" class="form-control" id="newPostStartDate" name="newPostStartDate" required>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>

                        <label for="newPostEndDate">End Date: </label>
                        <div class='input-group date' id='endDate'>
                            <input type="text" class="form-control" id="newPostEndDate" name="newPostEndDate" value="No End Date">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>

                        <label for="newPostCategories">Category(s):</label>

                        <input type="text" class="form-control" name="newPostCategories" id="categoryChips" />

                        <div class="form-group">
                            <label for="graphic">Header Image:</label>
                            <input type="file"
                                   name="graphic"/>
                        </div>

                        <br/>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <div class='input-group'>
                                <input type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/createpost" value="Create Post"/>
                            </div>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_CONTENTMANAGER')">

                            <div class='input-group'>
                                <input type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/createpost" value="Submit for Review"/>
                            </div>
                        </sec:authorize>

                    </div>






                </form>


            </div>
        </div>







        <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
        <script src="js/bootstrap.js"></script>

        <script
            src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"
            integrity="sha256-xI/qyl9vpwWFOXz7+x/9WkG5j/SVnSw21viy8fWwbeE="
        crossorigin="anonymous"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript">
            $(function () {
                $("#startDate , #endDate").datepicker({
                    autoclose: true,
                    orientation: "bottom auto",
                    useCurrent: false
                });

                $("#startDate").datepicker(
                        'setDate', new Date()
                        );
                $("#startDate").on("change", function () {
                    $("#endDate").data('datepicker').setStartDate($("#startDate").datepicker('getDate'));
                });
                $("#endDate").on("change", function () {
                    var endDate = $("#endDate").datepicker('getDate');
                    $("#startDate").data('datepicker').setEndDate(endDate);
                });
            });
        </script>



        <!-- Categories js -->
        <script src="${pageContext.request.contextPath}/category/magicsuggest-min.js"></script>

        <script type="text/javascript">

            $(function () {
            $('#categoryChips').magicSuggest({
            maxEntryLength: 25,
                    useCommaKey: true,
                    data: [<c:forEach items="${categories}" var="category">
                    '${category}',
            </c:forEach>],
                    matchCase: false
            });
            }
            );
        </script>
    </body>
</html>
