<%--
    Document   : navbar-user
    Created on : Jul 19, 2017, 12:09:14 PM
    Author     : vincentsiciliano
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



        


<div class="container-fluid">
    <nav class = "navbar navbar-default" role = "navigation">

        <div class = "navbar-header">
            <button type = "button" class = "navbar-toggle"
                    data-toggle = "collapse" data-target = "#example-navbar-collapse">
                <span class = "sr-only">Toggle navigation</span>
                <span class = "icon-bar"></span>
                <span class = "icon-bar"></span>
                <span class = "icon-bar"></span>
            </button>

            <a class = "navbar-brand" href = "${pageContext.request.contextPath}/">Jinja</a>
        </div>

        <div class = "collapse navbar-collapse" id = "example-navbar-collapse">

            <ul class = "nav navbar-nav">
                
                    <sec:authorize access="isAnonymous()">
                    <li class = "dropdown">
                        <a href = "#" class = "dropdown-toggle" data-toggle = "dropdown">
                            My Account
                            <b class = "caret"></b>
                        </a>

                        <ul class = "dropdown-menu">
                            <li><a href = "${pageContext.request.contextPath}/login" method="GET">Log In</a></li>
                            <li><a href = "${pageContext.request.contextPath}/register">Register</a></li>
                            <li><a href = "#">View Account</a></li>
                        </ul>
                    </sec:authorize>

                </li>
                





            </ul>
            
            <ul id="staticpage-list">
                
            </ul>

            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/adminhome"><span class="fa fa-cogs"></span> ${pageContext.request.userPrincipal.name}</a></li>
                    </sec:authorize>

                <sec:authorize access="hasRole('ROLE_CONTENTMANAGER')">
                    <li><a href="${pageContext.request.contextPath}/contentmanagerhome"><span class="fa fa-cogs"></span> ${pageContext.request.userPrincipal.name}</a></li>
                    </sec:authorize>

                <sec:authorize access="hasRole('ROLE_USER')">
                    <li><a href="<c:url value="/j_spring_security_logout" />" >Logout</a></li>
                    </sec:authorize>







            </ul>
        </div>

    </nav>
</div>




