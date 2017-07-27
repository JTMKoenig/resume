<%-- 
    Document   : contacts
    Created on : Jun 23, 2017, 9:33:33 AM
    Author     : jono
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
            <h1>Company Contacts</h1>
            <hr/>
            
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/displayContactsPage">Contacts</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>
                </ul>    
            </div>
                <!--*START* Main Content-->
                <sf:form class="form-horizontal" role="form" method="POST" modelAttribute="contact" action="editContact">
                            <div class="form-group">
                                <sf:errors path="*" cssClass="alert-danger alert text-center" element="div"></sf:errors>
                                <label for="add-first-name" class="col-md-4 control-label">First Name:</label>
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="add-first-name" path="firstName" placeholder="first name"/>
                                    
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="add-last-name" path="lastName" placeholder="last name"/>
                                    
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="add-company" class="col-md-4 control-label">Company:</label>
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="add-company" path="company" placeholder="company"/>
                                   
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="add-email" class="col-md-4 control-label">Email:</label>
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="add-email" path="email" placeholder="email"/>
                                   
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="add-phone" path="phone" placeholder="phone"/>
                                   
                                    <sf:hidden path="contactId"/>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Save Changes"/>
                                </div>
                            </div>
                            
                        </sf:form>
                
                
                <!--*END* Main Content-->
        </div>
        
    </body>
</html>
