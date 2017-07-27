<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<?php $thisPage="home"; ?>
<html>
    <head>
        <title>Hello Controller Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styling.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>H.E.R.O - Metabeing Central Hub</h1>
            <hr/>
            <div class='row'>
                <div class='col-md-4'>
                    <jsp:include page="nav.jsp" />
                </div>
                <div class="col-md-8">
                    <h2>Organizations</h2>
                </div>
            </div>
            <div class='row'>
                <form method="POST" action="organization/add" class="col-md-4 addForm">
                    <h2>Add New Organization</h2>
                    <div class="form-header">

                    </div>
                    <hr align="left"/>

                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" name="name"
                               placeholder="Name" required/>
                    </div>
                    <div class="form-group">
                        <label for="location">Location:</label>
                        <select class="form-control" name="locationSelection">
                            <c:forEach items="${locations}" var="l">
                                <option value="${l.locationId}">${l.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <a class="add-new-power">+add new to list</a>
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input type="text" class="form-control" name="phone"
                               placeholder="Phone"/>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" class="form-control" name="email"
                               placeholder="Email"/>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" name="description" required></textarea>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-default cancel-btn">
                            Clear
                        </button>
                        <input type="submit" id="addOrganization" class="btn btn-primary" value="Add Organization" />
                    </div>
                </form>


                <div id='organizationTable' class='col-md-8 mainTable'>
                    <table id="library-table" class="table table-hover">
                        <tr>
                            <th width="15%">Name</th>
                            <th width="20%">Description</th>
                            <th width="20%">City</th>
                            <th width="45%">Country</th>

                        </tr>
                        <tbody id="content-rows">

                            <!--list of Sightings-->
                            <c:forEach items="${organizations}" var="o">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/organization/show/${o.organizationId}">
                                            ${o.name}
                                        </a>
                                    </td>
                                    <td>
                                        ${o.description}
                                    </td>
                                    <td>
                                        ${o.location.city}
                                    </td>
                                    <td>
                                        ${o.location.country}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <h2>${message}</h2>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

