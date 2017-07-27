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
                    <h2>${org.name} <c:choose><c:when test="${org.location.name != null}"> - ${org.location.city}, ${org.location.country}</c:when></c:choose></h2>
                    <h4>Headquarters Name: <a href="${pageContext.request.contextPath}/location/show/${org.location.locationId}">${org.location.name}</a></h4>
                </div>
            </div>

            <div class='row'>
                <form method="POST" action="${pageContext.request.contextPath}/organization/update/${org.organizationId}" class="col-md-4 updateForm">
                    <h2>Edit ${org.name}</h2>
                    <div class="form-header">

                    </div>
                    <hr align="left"/>

                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" name="name"
                               value="${org.name}" required/>
                    </div>
                    <div class="form-group">
                        <label for="location">Location:</label>
                        <select class="form-control" name="locationId">
                            <option value="${org.location.locationId}" selected>${org.location.name}</option>
                            <option value="remove ${org.location.locationId}">REMOVE ${org.location.name}</option>
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
                               value="${org.phone}"/>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" class="form-control" name="email"
                               value="${org.email}"/>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" name="description" required>${org.description}</textarea>
                    </div>

                    <div class="form-group">
                        <button type="button" class="btn btn-default cancel-btn">
                            Clear
                        </button>
                        <input type="submit"  id="updateOrganization" class="btn btn-primary" value="Save Changes" />
                    </div>
                </form>

                <div id='organizationPhoto' class='col-md-4'>
                    <img src="${pageContext.request.contextPath}/images/headquartersPlaceholder.png" class="detailsPic"/>
                </div>
                <div class="col-md-4" id="organizationDetails">
                    <h3>About</h3>
                    ${org.description}
                    <h3>Phone</h3>
                    <c:choose>
                        <c:when test="${empty org.phone}">
                            No Known Phone Number
                        </c:when>
                        <c:when test="${!empty org.phone}">
                            ${org.phone}
                        </c:when>
                    </c:choose>
                    <h3>Email</h3>
                    <c:choose>
                        <c:when test="${empty org.email}">
                            No Known Email Address
                        </c:when>
                        <c:when test="${!empty org.email}">
                            ${org.email}
                        </c:when>
                    </c:choose>
                </div>
                <div class="col-md-8">
                    <h3>Known Members</h3>
                    <c:choose>
                        <c:when test="${empty metaPowers}">
                            No Known Members
                        </c:when>
                        <c:when test="${!empty metaPowers}">
                            <table id="metabeingTable" class="table table-hover">
                                <tr>
                                    <th width="15%">Metabeing</th>
                                    <th width="20%">Powers</th>
                                    <th width="20%">About</th>
                                    <th width="45%">Alias</th>

                                </tr>
                                <tbody id="content-rows">

                                    <!--list of Metabeings-->
                                    <c:forEach items="${metaPowers}" var="mp">
                                        <tr>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/metabeing/show/${mp.key.metabeingId}" id="showMetaDetails">
                                                    ${mp.key.name}
                                                </a>
                                            </td>
                                            <td>
                                                <c:forEach items="${mp.value}" var="p">
                                                    TYPE: ${p.powerType}<br />
                                                    DETAILS: ${p.powerDescription}<br />
                                                </c:forEach>

                                            </td>
                                            <td>
                                                ${mp.key.description}
                                            </td>
                                            <td>
                                                ${mp.key.alias}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                    </c:choose>


                </div>

            </div>
            <div class="deleteBtn col-md-4">
                <form method="POST" action="${pageContext.request.contextPath}/organization/delete/${org.organizationId}">
                    <button class="btn btn-danger" onclick="return confirm('Are You Sure?')">DELETE</button>
                </form>
            </div>
            <h2>${message}</h2>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

