<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
        <body id="body">
        <div class='container'>

            <!--*START* Top Row Buttons -->
            <div class='row' style="margin-top: 12px">

                <!--Create Blu-Ray Button -->
                <div class="col-md-offset-1 col-md-4">
                    <button type="button" id="add-dvd-btn" class="btn btn-default">
                        Add DVD
                    </button>
                </div>
                <!--Search Form-->
                <form class="form-horizontal" method="POST" action="search" role="form" id="search-form">
                    <div class="form-group">
                        <div class="col-md-2">
                            <select class="form-control" id="search-category" name="searchCategory">
                                <option value="searchCategory"
                                        selected hidden >
                                    Search Category</option>
                                <option value="title">Title</option>
                                <option value="year">Release Year</option>
                                <option value="director">Director</option>
                                <option value="rating">MPAA Rating</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <input type="text"
                                   class="form-control" name="searchTerm" id="search-term"
                                   placeholder="Search Term" required/>
                        </div>
                        <div class="col-md-1">
                            <input type="submit" id="search-btn" value="Search" class="btn btn-default">
                        </div>
                        <div class="col-md-1">
                            <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Home</a>
                        </div>
                    </div>
                </form>
                <hr/>
            </div>
            <!--*END* Top Row Buttons-->

            <!--*START* Dvd Library Table-->
            <div class="row">
                <ul class='list-group' id='errorMessages'></ul>
                <div class="col-md-12">
                    <div id="library-table-div">
                        <table id="library-table" class="table table-hover">
                            <tr>
                                <th width="20%">Title</th>
                                <th width="20%">Release Date</th>
                                <th width="20%">Director</th>
                                <th width="10%">Rating</th>
                                <th width="10%"></th>
                            </tr>
                            <tbody id="content-rows">
                                
                                <!--list of Dvds-->
                                <c:forEach items="${dvdList}" var="dvd">
                                    <tr>
                                <td>
                                <a data-id="${dvd.id}" id="show-dvd-btn">
                                ${dvd.title}
                                </a>
                                </td>
                                <td>
                                    ${dvd.releaseDate}
                                </td>
                                <td>
                                    ${dvd.director}
                                </td>
                                <td>
                                    ${dvd.rating}
                                </td>
                                <td>
                                    <a data-id="${dvd.id}" class="edit-btn">edit</a> |
                                    <a href="${pageContext.request.contextPath}/dvd/delete/${dvd.id}" id="delete-btn">delete</a>                                    
                                </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!--*END* Dvd Library Table-->

            <!--*START* Add/Edit Dvd Form-->
            <div class="row">
                
                <div id="dvd-add-form-div" style="display:none">
                    <form method="POST" action="addDvd" class="col-md-offset-4 col-md-8">
                        <jsp:include page="dvdForm.jsp" />
                        <div class="form-group">
                            <button type="button" class="btn btn-default cancel-btn" style="width:12%">
                                Cancel
                            </button>
                            <input type="submit"  formmethod="POST" formaction="addDvd" id="add-btn" class="btn btn-primary" value="Add DVD" style="width:38%" />
                        </div>
                    </form>
                </div>
               
                    
                <div id="dvd-edit-form-div" style="display:none">
                    <form method="POST" action="update" class="col-md-offset-4 col-md-8">
                        <input type="hidden" name="id" id="dvd-id"/>
                        <jsp:include page="dvdForm.jsp" />
                        <div class="form-group">

                            <button type="button" class="btn btn-default cancel-btn" style="width:12%">
                                Cancel
                            </button>
                        <input type="submit" class="btn btn-primary" value="Save DVD" style="width:38%"/>
                        
                </div>
                        
                    </form>
                </div>
                    
                   
                </div>
            <!--*END* Add/Edit Dvd Form-->

            <!--*START* Dvd Details-->
           <div id="details-div" style="display:none">
                        
                <div class="row" id="details-title"></div>
                <div class="row" id="details-data"></div>
                <div class="row">
                    <hr/>
                    <button type="button" id="back-btn" class="btn btn-primary">
                        Back
                    </button>
                </div>
            </div>
            <!--*END* DVD Details-->
            </div>
        <!-- Placed at the end of the document so the pages load faster -->
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>
        <script> var ctx = "${pageContext.request.contextPath}";</script>
        
    </body>
</html>

