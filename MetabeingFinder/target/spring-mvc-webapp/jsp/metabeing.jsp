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
                    <h2>Metabeings</h2>
                </div>
            </div>
            <div class='row'>

                <form method="POST" action="metabeing/add" class="col-md-4 addForm">
                    <h2>Add New Metabeing</h2>
                    <div class="form-header">

                    </div>
                    <hr align="left"/>

                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" name="name"
                               placeholder="Name" required/>
                    </div>

                    <div class="form-group">
                        <label for="powers">Powers:</label>
                        <select class="form-control" name="powerId">
                            <option value="selectPowers"
                                    selected hidden >
                                Select Power</option>
                                <c:forEach items="${allPowers}" var="power">
                                <option value="${power.powerId}">${power.powerType}</option>
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
                        <select class="form-control" name="organizationId">
                            <option value="selectOrganization"
                                    selected hidden >
                                Select Organization</option>
                                <c:forEach items="${allOrgs}" var="org">
                                <option value="${org.organizationId}">${org.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <a class="add-new-power">+add new to list |</a>
                        <a class="add-new-power">+add more to Metabeing</a>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" name="description"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="alias">Alias:</label>
                        <input type="text" class="form-control"
                               placeholder="Alias" name="alias"/>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-default cancel-btn">
                            Clear
                        </button>
                        <input type="submit"  formmethod="POST" formaction="metabeing/add" id="add-metabeing" class="btn btn-primary" value="Add Metabeing" />
                    </div>
                </form>

                <div id='currentSightings' class='col-md-8 mainTable'>

                    <table id="metabeingTable" class="table table-hover">
                        <tr>
                            <th width="15%">Metabeing</th>
                            <th width="30%">Powers</th>
                            <th width="40%">About</th>
                            <th width="15%">Alias</th>

                        </tr>
                        <tbody id="content-rows">

                            <!--list of Metabeings-->
                            <c:forEach items="${metaPowers}" var="mp">
                                <tr>
                                    <td>
                                        <a href="metabeing/show/${mp.key.metabeingId}" id="showMetaDetails">
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
                </div>
            </div>

            <jsp:include page="addNewPowerModal.jsp"/>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

