<%-- 
    Document   : contacts
    Created on : Jun 23, 2017, 9:33:33 AM
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
        <title>Company Contacts</title>
    </head>
    <body>
        <div class="container">
            <h1>Contact Details</h1>
            <hr/>
            
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/displayContactsPage">Contacts</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>
                </ul>    
            </div>
                <!--*START* Main Content-->
                
                <p>
                    Name: ${contact.firstName} ${contact.lastName}
                </p>
                
                <p>
                    Company: ${contact.company}
                </p>
                
                <p>
                    Phone: ${contact.phone}
                </p>
                
                <p>
                    Email: ${contact.email}
                </p>
                
                <!--*END* Main Content-->
        </div>
        
    </body>
</html>
