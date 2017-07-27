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
                    <h2>${meta.name} <c:choose><c:when test="${meta.alias!=null}"> - (${meta.alias})</c:when></c:choose></h2>

                        </div>
                    </div>

                    <div class='row'>
                        <form method="POST" action="${pageContext.request.contextPath}/metabeing/update/${meta.metabeingId}" class="col-md-4 updateForm">
                    <h2>Edit ${meta.name}</h2>
                    <div class="form-header">

                    </div>
                    <hr align="left"/>

                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" value="${meta.name}" name="name"
                               placeholder="Name" required/>
                    </div>

                    <div class="form-group">
                        <label for="powers">Powers:</label>
                        <select class="form-control" name="powerId">
                            <c:forEach items="${powers}" var="currentPower">
                                <option value="${currentPower.powerId}" selected>
                                    ${currentPower.powerType}
                                </option>
                                <option value="remove ${currentPower.powerId}">
                                    REMOVE: ${currentPower.powerType}
                                </option>
                                <c:forEach items="${allPowers}" var="power">
                                    <option value="${power.powerId}">${power.powerType}</option>
                                </c:forEach>
                            </c:forEach>
                        </select>

                    </div>
                    <div class="form-group">
                        <a data-toggle="modal" data-target="#myModal">
                            +add new power |
                        </a>
                        <a class="add-new-power">+add more powers</a>
                    </div>
                    <div class="form-group">
                        <label for="organization">Organization:</label>
                        <c:choose>
                            <c:when test="${empty orgs}">
                                <select class="form-control" name="organizationId">
                                    <option value=""selected>NONE</option>
                                    <c:forEach items="${allOrgs}" var="org">
                                        <option value="${org.organizationId}">${org.name}</option>
                                    </c:forEach>
                                </select>
                            </c:when>
                            <c:when test="${!empty orgs}">
                                <select class="form-control" name="organizationId">
                                    <c:forEach items="${orgs}" var="currentOrg">
                                        <option value="${currentOrg.organizationId}" selected>
                                            ${currentOrg.name}
                                        </option>
                                        <option value="remove ${currentOrg.organizationId}">
                                            REMOVE: ${currentOrg.name}
                                        </option>
                                        <c:forEach items="${allOrgs}" var="org">
                                            <option value="${org.organizationId}">${org.name}</option>
                                        </c:forEach>
                                    </c:forEach>
                                </select>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="form-group">
                        <a class="add-new-power">+add new to list |</a>
                        <a class="add-new-power">+add more to Metabeing</a>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" name="description">${meta.description}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="alias">Alias:</label>
                        <input type="text" class="form-control"
                               placeholder="Alias" name="alias" value="${meta.alias}"/>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-default cancel-btn">
                            Clear
                        </button>
                        <input type="submit"  id="updateMetabeing" class="btn btn-primary" value="Save Changes" />
                    </div>
                </form>

                <div id='metabeingDetails' class='col-md-4 metaDetails'>
                    <img src="${pageContext.request.contextPath}/images/metabeingAvatar.png"class="detailsPic"/>
                </div>
                <div class="col-md-4">
                    <h3>About</h3>
                    ${meta.description}
                    <h3>Powers</h3>
                    <c:forEach items="${powers}" var="p">
                        Type: ${p.powerType}<br />
                        Description: ${p.powerDescription}<hr/>
                    </c:forEach>
                </div>
                <div class="col-md-8">
                    <h3>Known Organizations</h3>
                    <c:choose>
                        <c:when test="${empty orgs}">
                            No Known Organizations
                        </c:when>
                        <c:when test="${!empty orgs}">
                            <table id="organization-table" class="table table-hover">
                                <tr>
                                    <th width="15%">Name</th>
                                    <th width="20%">Description</th>
                                    <th width="20%">City</th>
                                    <th width="45%">Country</th>

                                </tr>
                                <tbody id="content-rows">

                                    <!--list of Sightings-->
                                    <c:forEach items="${orgs}" var="o">
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
                        </c:when>
                    </c:choose>

                    <h3>Recent Sightings</h3>
                    <c:choose>
                        <c:when test="${empty sightingMetas}">
                            No Recent Sightings
                        </c:when>
                        <c:when test="${!empty sightingMetas}">
                            <table id="sighting-table" class="table table-hover">
                                <tr>
                                    <th width="15%">Date</th>
                                    <th width="20%">Location</th>
                                    <th width="20%">Metabeings</th>
                                    <th width="45%">Description</th>

                                </tr>
                                <tbody id="content-rows">

                                    <!--list of Sightings-->
                                    <!--list of Sightings-->
                                    <c:forEach items="${sightingMetas}" var="sm">
                                        <tr>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/sighting/show/${sm.key.sightingId}">
                                                    ${sm.key.date}
                                                </a>
                                            </td>
                                            <td>
                                                ${sm.key.location.name}
                                            </td>
                                            <td>
                                                <c:forEach items="${sm.value}" var="m">
                                                    ${m.name}<br />
                                                </c:forEach>
                                            </td>
                                            <td>
                                                ${sm.key.description}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <form method="POST" action="${pageContext.request.contextPath}/metabeing/delete/${meta.metabeingId}">
                        <button class="btn btn-danger" onclick="return confirm('Are You Sure?')">DELETE</button>
                    </form>
                </div>
            </div>
            <jsp:include page="addNewPowerModal.jsp"/>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

