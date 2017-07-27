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
            <h1>Company Contacts</h1>
            <hr/>
            
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/displayContactsPage">Contacts</a></li>
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>
                </ul>    
            </div>
                <!--*START* Main Content-->
                <ul class="list-group" id="errorMessages"></ul>
                <div class="row">
                    <div class="col-md-6">
                        <h2>Search Results</h2>
                        <table id="contactTable" class="table table-hover">
                            
                            <tr>
                                <th width="40%">Contact Name</th>
                                <th width="30%">Company</th>
                                <th width="15%">Phone</th>
                                <th width="15%">Email</th>
                            </tr>
                            <tbody id="contentRows"/>
                        </table>
                    </div>
                    
                    <div class="col-md-6">
                        <h2>Search</h2>
                        <form class="form-horizontal" role="form" id="search-form">
                            <div class="form-group">
                                <label for="search-first-name" class="col-md-4 control-label">First Name</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="search-first-name" placeholder="first name"/>
                                </div>
                            </div>
                            
                            
                            <div class="form-group">
                                <label for="search-last-name" class="col-md-4 control-label">Last Name</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="search-last-name" placeholder="last name"/>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="search-company" class="col-md-4 control-label">Company</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="search-company" placeholder="company"/>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="search-phone" class="col-md-4 control-label">Phone</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="search-phone" placeholder="phone"/>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="search-email" class="col-md-4 control-label">Email</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="search-email" placeholder="email"/>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="button" class="btn btn-default" id="search-button" value="Search"/>
                                </div>
                            </div>
                        </form>
                        
                    </div>
                </div>
                
                
                <!--*END* Main Content-->
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/contactList.js"></script>
    </body>
</html>
