<%--
    Document   : jinjahome
    Created on : Jul 19, 2017, 12:07:24 PM
    Author     : vincentsiciliano
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jinja Home</title>
        <link href="${pageContext.request.contextPath}/css/jinjaStyle.css" type="text/css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">

        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Syncopate" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
    </head>
    <body style="background-color: whitesmoke">
        <%@include file="navbar-user.jsp" %>

        <div class="container-fluid">
            <article>
                <div class="row">
                    <div class="col-md-2">
                    </div>

                    <div class="feed col-md-8">
                        <c:forEach var="currentPost" items="${postList}">
                            <a href="${pageContext.request.contextPath}/viewpost/${currentPost.postId}" method="GET">

                                <hr>
                                <img style="max-width: 100%; box-shadow: 5px 5px 5px #888888; " src="http://aboutvideo.info/wp-content/uploads/2017/02/Ghost-In-The-Shell-movie-trailer-2017-min.jpg">
                                <hr>

                                <h1>${currentPost.title}</h1>
                                <br>

                            </a>
                        </c:forEach>
                    </div>

                    <div class="col-md-2">

                    </div>
                </div>

                <div class="row">


                    <div class="col-md-offset-5 col-md-2">
                        <c:choose>
                            <c:when test="${index == null}">

                                <ul class="pagination pagination-sm">
                                    <li><a href="${pageContext.request.contextPath}/home/page/1">next »</a></li>
                                </ul>

                            </c:when>
                            <c:when test="${index gt 1 && fn: length(postList) le 20}">
                                <ul class="pagination pagination-sm">
                                    <li><a href="${pageContext.request.contextPath}/home/page/${index - 1}">« back</a></li>
                                </ul>
                            </c:when>
                            <c:when test="${index ge 1}">
                                <ul class="pagination pagination-sm">
                                    <li><a href="${pageContext.request.contextPath}/home/page/${index - 1}">« back</a></li>
                                    <li><a href="${pageContext.request.contextPath}/home/page/${index + 1}">next »</a></li>
                                </ul>
                            </c:when>
                        </c:choose>
                    </div>

                </div>
            </article>
        </div>

        
        <script src="js/bootstrap.js"></script>
        <script src="js/home.js"></script>
        <script
            src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"
            integrity="sha256-xI/qyl9vpwWFOXz7+x/9WkG5j/SVnSw21viy8fWwbeE="
        crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/navbar.js"></script>
    </body>
</html>
