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
                    <h2>Sightings</h2>
                </div>
            </div>
            <div class='row'>
                <form method="POST" action="sighting/add" class="col-md-4 addForm">
                    <h2>Add New Sighting</h2>
                    <hr align="left"/>
                    <div class="form-group">
                        <label for="date">Date:</label>
                        <div class='input-group date' id='datetimepicker1'>
                            <input type='text' class="form-control" name="date" required/>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="locationId">Location:</label>
                        <select class="form-control" name="locationId">
                            <option value="Select Location" selected disabled>Select Location</option>
                            <c:forEach items="${locations}" var="l">
                                <option value="${l.locationId}">${l.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <a href="${pageContext.request.contextPath}/location">
                            +add new location |
                        </a>
                    </div>
                    <div class="form-group" id="metabeingSelect">
                        <label for="metabeingId">Metabeing:</label>
                        <select class="form-control" name="metabeingId">
                            <option value="Select Metabeing" selected disabled>Select Metabeing</option>
                            <c:forEach items="${metas}" var="m">
                                <option value="${m.metabeingId}">${m.name}</option>

                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <a href="${pageContext.request.contextPath}/metabeing">
                            +add new metabeing |
                        </a>
                        <a id="moreMetasBtn">
                            +add more metabeings |
                        </a>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" name="description" required></textarea>

                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-default cancel-btn">
                            Clear
                        </button>
                        <input type="submit" id="addSighting" class="btn btn-primary" value="Add Sighting" />
                    </div>
                </form>


                <div id='currentSightings' class='col-md-8 mainTable'>
                    <table id="library-table" class="table table-hover">
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
                </div>
            </div>


        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/javascript.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datepicker();
            });
        </script>
    </body>
</html>

