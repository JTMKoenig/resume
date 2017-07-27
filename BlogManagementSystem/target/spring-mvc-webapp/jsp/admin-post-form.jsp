<%--
    Document   : viewpost{id}
    Created on : Jul 19, 2017, 12:07:24 PM
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
        <title>Jinja Home</title>
        <!--CSS Files -->
        <link href="${pageContext.request.contextPath}/css/jinjaStyle.css" type="text/css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css" type="text/css">
        <link href="${pageContext.request.contextPath}/category/magicsuggest-min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.css" rel="stylesheet">
        <!--Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Syncopate" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    </head>

    <style>
        option[value="0"] {

            background-color: #b2dba1;
        }

        option[value="1"] { /* value not val */
            background-color:#f5e79e;
        }

        option[value="2"] { /* value not val */
            background: #dca7a7;
        }
        textarea{
            width:100%;
            max-width:100%;
            height: 100px;
            max-height:400px;
        }
        hr {
            border-width:10px;
            width:70%;
        }
    </style>

    <body style="background-color: whitesmoke">
        <%@include file="navbar-user.jsp" %>


        <div class="container-fluid">
            <article>

                <form role="form" action="${pageContext.request.contextPath}/makeadminedit" method="POST"  modelAttribute="adminEditPost">
                    <input type="hidden" name="postId"  value="${postId}"/>
                    <input type="hidden" name="userId" value="${userId}"/>

                    <div class="row">
                        <div class="col-md-offset-2 col-md-2">
                            <label for="titleFlag">Title:</label>
                            <select name="titleFlag" id="titleFlag" class="selectpicker form-control">
                                <option value="${titleFlag}" selected style="color: grey">${flagMap['titleFlag']}</option>
                                <option value="0">Approved</option>
                                <option value="1">Needs Review</option>
                                <option value="2">Flagged</option>
                            </select>
                        </div>

                        <div  id="title-div" class="col-md-6">
                            <input name="title" value="<c:out value="${title}"></c:out>" class="form-control"/>
                            </div>






                            <div name="titleNotes" class="col-md-offset-2 col-md-8">
                                <h4>Title Notes for Author:</h4>
                                <textarea></textarea>
                            </div>
                        </div>
                        <hr>

                        <div class="row">
                            <div class="col-md-offset-2 col-md-2">
                                <label for="authorFlag">Author:</label>
                                <select name="authorFlag" id="authorFlag" class="selectpicker form-control">
                                    <option value="${authorFlag}" selected style="color: grey">${flagMap['authorFlag']}</option>
                                <option value="0">Approved</option>
                                <option value="1">Needs Review</option>
                                <option value="2">Flagged</option>
                            </select>
                        </div>

                        <div id="username-div" class="col-md-6">
                            <!-- <div  id="username-div">-->
                            <select name="userName" id="userName" class="selectpicker form-control">
                                <c:forEach var="currentUser" items="${userList}">

                                    <option value="${currentUser.userId}"><c:out value="${currentUser.userName}"></c:out></option>

                                </c:forEach>
                            </select>

                            <!--</div>-->
                        </div>

                        <div class="col-md-offset-2 col-md-8">
                            <h4>Notes for Author:</h4>
                            <textarea></textarea>
                        </div>
                    </div>


                    <hr>

                    <div class="row">
                        <div class="col-md-offset-2 col-md-2">
                            <label for="startDateFlag">Start Date:</label>
                            <select name="startDateFlag" id="startDateFlag" class="selectpicker form-control">
                                <option value="${startDateFlag}" selected style="color: grey">${flagMap['startDateFlag']}</option>
                                <option value="0">Approved</option>
                                <option value="1">Needs Review</option>
                                <option value="2">Flagged</option>
                            </select>
                        </div>

                        <div id="start-div" class="col-md-6">
                            <input name="startDate" value="<c:out value="${startDate}"></c:out>" class="form-control"/>
                            </div>

                            <div class="col-md-offset-2 col-md-8">
                                <h4>Notes for Author:</h4>
                                <textarea></textarea>
                            </div>

                        </div>

                        <hr>

                        <div class="row">
                            <div class="col-md-offset-2 col-md-2">
                                <label for="endDateFlag">End Date:</label>
                                <select name="endDateFlag" id="endDateFlag" class="selectpicker form-control">
                                    <option value="${endDateFlag}" selected style="color: grey">${flagMap['endDateFlag']}</option>
                                <option value="0">Approved</option>
                                <option value="1">Needs Review</option>
                                <option value="2">Flagged</option>
                            </select>
                        </div>




                        <div id="end-div" class="col-md-6">
                            <!-- <div  id="end-div">-->
                            <input name="endDate" value="<c:out value="${endDate}"></c:out>" class="form-control"/>
                            </div>

                            <div class="col-md-offset-2 col-md-8">
                                <h4>Notes for Author:</h4>
                                <textarea></textarea>
                            </div>

                        </div>

                        <hr>

                        <div class="row">
                            <div class="col-md-offset-2 col-md-2">
                                <label for="bodyFlag">Body:</label>
                                <select name="bodyFlag" id="bodyFlag" class="selectpicker form-control">
                                    <option value="${bodyFlag}" selected style="color: grey">${flagMap['bodyFlag']}</option>
                                <option value="0">Approved</option>
                                <option value="1">Needs Review</option>
                                <option value="2">Flagged</option>
                            </select>
                            <br/>
                            <label for="imageFlag">Images:</label>
                            <select name="imageFlag" id="imageFlag" class="selectpicker form-control">
                                <option value="${imageFlag}" selected style="color: grey">${flagMap['imageFlag']}</option>
                                <option value="0">Approved</option>
                                <option value="1">Needs Review</option>
                                <option value="2">Flagged</option>
                            </select>


                        </div>


                        <div id="body-div" class="col-md-6">
                            <em name="postBody" id="editor"><c:out value="${postBody}"></c:out></em>
                                <br>

                            </div>

                            <div class="col-md-offset-2 col-md-8">
                                <h4>Notes for Author:</h4>
                                <textarea></textarea>
                            </div>

                        </div>


                        <input type="submit" style="width:100%"class="btn btn-primary btn-create" value="Submit"/>
                    </form>
                </article>
            </div>
        </body>
        <!--jQuery -->
        <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <!--Jinja js -->
    <script src="${pageContext.request.contextPath}/js/ReviewStatus.js"></script>
    <script src="${pageContext.request.contextPath}/js/home.js"></script>
    <!--Tiny MCE -->
    <script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/imageupload.js"></script>
    <script src="${pageContext.request.contextPath}/js/tinymce/jquery.tinymce.min.js"></script>
    <script>tinymce.init({selector: 'em'});</script>
    <!-- Datepicker js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#startDate , #endDate").datepicker({
                autoclose: true,
                orientation: "bottom auto",
                useCurrent: false
            });

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
        data: [<c:forEach items="${categories}" var="category">
        '${category}',
        </c:forEach>],
                matchCase: false
        });
        }
        ;
        );
    </script>

</html>
