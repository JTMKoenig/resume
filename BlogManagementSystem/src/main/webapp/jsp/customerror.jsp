<%--
    Document   : customerror
    Created on : Jul 21, 2017, 9:59:03 AM
    Author     : jono
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <title>Jinja Blog</title>
    </head>
    <body style="background-color: whitesmoke">
        <div class="containter">
            <%@include file="navbar-user.jsp" %>
            <div class="col-md-offset-4 col-md-4">
                <h1>An error has occurred...</h1>
                <h3>${errorMessage}</h3>
            </div>

        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    </body>
</html>
