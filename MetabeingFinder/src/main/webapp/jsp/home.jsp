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
    </head>
    <body>
        <div class="container">
            <h1>H.E.R.O - Metabeing Central Hub</h1>
            <hr/>
            <div class='row'>
                <div class='col-md-4'>
                    <jsp:include page="nav.jsp" />
                    <p>Welcome to the official H.E.R.O database. We believe in a world of safety. We believe in a world of order. This database brings us both.</p>
                </div>
                <div id='currentSightings' class='col-md-8'>
                    <h2>Most Recent Sightings</h2>
                    <table id="library-table" class="table table-hover">
                        <tr>
                            <th width="15%">Date</th>
                            <th width="20%">Location</th>
                            <th width="20%">Metabeings</th>
                            <th width="45%">Description</th>

                        </tr>
                        <tbody id="content-rows">

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
                </div>
            </div>
            <div class='row'>

            </div>

            <h2>${message}</h2>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

