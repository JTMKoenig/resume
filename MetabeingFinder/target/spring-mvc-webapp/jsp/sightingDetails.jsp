<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
                    <h2>Sighting #${sight.sightingId}  - ${sight.location.name}</h2>
                </div>
            </div>

            <div class='row'>
                <form method="POST" action="${pageContext.request.contextPath}/sighting/update/${sight.sightingId}" class="col-md-4 updateForm">
                    <h2>Edit Sighting #${sight.sightingId}</h2>
                    <div class="form-header">

                    </div>
                    <hr align="left"/>
                    <div class="form-group">
                        <label for="date">Date:</label>
                        <div class='input-group date' id='datetimepicker1'>
                            <input type='text' class="form-control" name="date" value="${date}" required/>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="locationId">Location:</label>
                        <select class="form-control" name="locationId">
                            <option value="${sight.location.locationId}" selected>${sight.location.name}</option>
                            <c:forEach items="${locations}" var="l">
                                <option value="${l.locationId}">${l.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <a class="add-new-power">+add new Location</a>
                    </div>
                    <div class="form-group">
                        <c:forEach items="${metas}" var="currentMetas">
                            <label for="metabeingId">Metabeing:</label>
                            <select class="form-control" name="metabeingId">
                                <option value="${currentMetas.metabeingId}" selected >${currentMetas.name}</option>
                                <c:forEach items="${allMetas}" var="m">
                                    <option value="${m.metabeingId}">${m.name}</option>
                                </c:forEach>
                            </select>
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <a id="addMetaBtn">+add new Metabeing</a>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" name="description" required>${sight.description}</textarea>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-default cancel-btn">
                            Clear
                        </button>
                        <input type="submit" class="btn btn-primary" value="Save Sighting" />
                    </div>
                </form>

                <div id='organizationPhoto' class='col-md-4'>
                    <img src="${pageContext.request.contextPath}/images/sightingPlaceholder.png" class="detailsPic"/>
                </div>
                <div class="col-md-4" id="organizationDetails">
                    <h3>Sighting on ${date}</h3>
                    ${sight.description}
                    <h3>Location</h3>
                    <a href="${pageContext.request.contextPath}/location/show/${sight.location.locationId}">${sight.location.name}</a><br/>
                    ${sight.location.city}, ${sight.location.state} - ${sight.location.country}
                    <h3>Metabeings Involved</h3>
                    <c:forEach items="${metas}" var="m">
                        <a href="${pageContext.request.contextPath}/metabeing/show/${m.metabeingId}">${m.name}</a><br/>
                    </c:forEach>
                </div>


            </div>
            <div class="deleteBtn col-md-4">
                <form method="POST" action="${pageContext.request.contextPath}/sighting/delete/${sight.sightingId}">
                    <button class="btn btn-danger" onclick="return confirm('Are You Sure?')">DELETE</button>
                </form>
            </div>
            <h2>${message}</h2>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript">
                        $(function () {
                            $('#datetimepicker1').datepicker();
                        });
        </script>

    </body>
</html>

