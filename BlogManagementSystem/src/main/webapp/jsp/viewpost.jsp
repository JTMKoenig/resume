<%--
    Document   : viewpost{id}
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
        <title>${post.title}</title>
        <link href="${pageContext.request.contextPath}/css/jinjaStyle.css" type="text/css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">


        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Syncopate" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">

        <style>
            .commentTitle{
                font-weight: bold;
                padding-top:16px;
            }

            .username {
                font-weight: bold;
                color:darkblue;
            }

            .comments{
                margin-top:0px;
            }

            hr{
                margin-top:10px;
                margin-bottom:10px;
            }
            .seperator {
                margin-top: 15px;
            }
            textarea.form-control {
                margin-top:10px;
                height:100px;
            }
            .submit-btn{
                float:right;
                margin-top:8px;
            }
            .comment-delete{
                float:right;
                background:none!important;
                border:none;
                padding:0!important;
            }
            .category{
                border: #07a 2px solid;
                padding: 6px;
                background: #07a;
                border-radius: 12px;
                color: white;

            }
            a.category {
                text-decoration: none;
                display:inline-block;
                margin: 10px 2px 0px 2px;

            }
            .category:hover{
                background:white;
                border: #204d74 2px solid;
            }

        </style>

    </head>
    <body style="background-color: whitesmoke">
        <%@include file="navbar-user.jsp" %>

        <div class="container-fluid">
            <article>
                <div class="row">
                    <div class="col-md-2">
                    </div>

                    <div class="feed col-md-8">



                        <div class="col-md-12">
                            <h1><c:out value="${post.title}"></c:out></h1>
                            <sec:authorize access="hasRole('ROLE_CONTENTMANAGER')">
                                <c:choose>
                                    <c:when test="${pageContext.request.userPrincipal.name == post.user.userName}">
                                        <a class="btn btn-default" href="${pageContext.request.contextPath}/contentmanagereditform/${post.postId}">Edit Post</a>
                                    </c:when>
                                </c:choose>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="btn btn-default" href="${pageContext.request.contextPath}/adminpostform/${post.postId}">Edit Post</a>
                            </sec:authorize>
                            <h5><c:out value="${post.user.userName}"></c:out></h5>
                            <h5>${date}</h5>



                            <br>

                            <div name="postBody" id="postBody"><c:out value="${post.postBody}" escapeXml="false">   </c:out></div>
                                <div class="col-md-12">
                                    <h4 class="text-center" style="font-weight: bold">- Categories -</h4>
                                <c:forEach items="${categories}" var="category">
                                    <a class="category" href="${pageContext.request.contextPath}/category/${category.categoryId}">#${category.categoryId}</a>
                                </c:forEach>
                                <br/><br/>
                            </div>

                        </div>

                    </div>



                    <div class="col-md-2">

                    </div>
                </div>
                <div class="seperator"></div>
                <div class="row">
                    <div class="col-md-offset-2 col-md-8 comments feed">
                        <div class="col-md-12">
                            <sec:authorize access="isAnonymous()">

                                <form role="form" method="POST" action="${pageContext.request.contextPath}/comment/login/${post.postId}">
                                    <div class="form-group">
                                        <textarea name="comment" class="form-control" placeholder="add a comment..." maxlength="1000"></textarea>

                                        <button class="submit-btn btn btn-primary" type="submit">Log In</button>
                                    </div>
                                </form>

                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_USER')">

                                <form role="form" method="POST" action="${pageContext.request.contextPath}/comment/post/${post.postId}">
                                    <div class="form-group">
                                        <textarea name="comment" class="form-control" placeholder="add a comment..." maxlength="1000"></textarea>

                                        <button class="submit-btn btn btn-primary" type="submit">Post</button>
                                    </div>
                                </form>

                            </sec:authorize>

                        </div>

                        <div class="commentTitle col-md-12">
                            <c:choose>
                                <c:when test="${fn: length(comments) == 0}">
                                    no comments
                                </c:when>
                                <c:when test="${fn: length(comments) gt 1}">
                                    ${fn: length(comments)} comments
                                </c:when>
                                <c:when test="${fn: length(comments) == 1}">
                                    ${fn: length(comments)} comment
                                </c:when>
                            </c:choose>
                            <hr/>
                        </div>



                        <div class="commentFeed col-md-12">
                            <c:forEach items="${comments}" var="comment">
                                <div class="username">${comment.user.userName}</div>
                                <c:choose>
                                    <c:when test="${comment.user.userName == pageContext.request.userPrincipal.name}">
                                        <form method="POST" action="${pageContext.request.contextPath}/comment/delete/${comment.commentId}/post/${post.postId}">
                                            ${comment.commentBody}
                                            <button class="comment-delete" type="submit"><span class="fa fa-trash text-danger" aria-hidden="true"></span></button>
                                        </form>
                                    </c:when>
                                    <c:when test="${comment.user.userName!= pageContext.request.userPrincipal.name}">
                                        ${comment.commentBody}
                                    </c:when>

                                </c:choose>

                                <hr/>
                            </c:forEach>
                        </div>
                    </div>


            </article>

        </div>









        <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>
    </body>
</html>
