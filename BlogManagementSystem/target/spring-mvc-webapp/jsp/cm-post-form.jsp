<%-- 
    Document   : viewpost{id}
    Created on : Jul 19, 2017, 12:07:24 PM
    Author     : vincentsiciliano
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jinja Home</title>
        <link href="${pageContext.request.contextPath}/css/jinjaStyle.css" type="text/css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css" type="text/css">

        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Syncopate" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
        <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=58l4eygxylx2nw9uky1d52tlzhkabmm54ihelc0gjjfj4o3t"></script>  
        <script>tinymce.init({selector: 'textarea'});</script>

    </head>

    <style>
        option[value="0"] {

            background-color: #b2dba1;
        }

        option[value="1"] { /* value not val */
            background-color:#f5e79e;
        }

        option[value="2"] { /* value not val */
            background: #dca7a7;
        }
    </style>
    <body style="background-color: whitesmoke">
        <%@include file="navbar-user.jsp" %>


        <div class="container-fluid">
            <article>

                <form role="form" action="${pageContext.request.contextPath}/makeadminedit" method="POST"  modelAttribute="adminEditPost">
                    <input name="postId" style="display: none" value="${postId}"/>
                    <input name="userId" style="display: none" value="${userId}"/>
                    <div class="row">

                        <div class="col-md-2">


                        </div>

                        <div value="${imageFlage}"id="image-div" class="feed col-md-8">

                            <!--<input type="submit" style="width:100%"class="btn btn-primary btn-create" value="Submit"/>-->
                            <hr>
                            
                            <hr>




                        </div>
                        <div class="col-md-2"></div>
                    </div>

                    <hr>


                    <div class="row">
                        <div class="col-md-2">       

                        </div>

                        <div  status="${titleFlag}" id="title-div" class="col-md-8">
                            <input name="title" value="<c:out value="${title}"></c:out>" class="form-control"/>
                            </div>

                            <div class="col-md-2"></div>

                        </div>

                        <hr>

                        <div class="row">
                            <div class="col-md-2">                        

                        </div>

                        <div status="${authorFlag}" id="username-div" class="col-md-8">
                            <!-- <div  id="username-div">-->
                            <select name="userName" id="userName" class="selectpicker form-control">
                                <c:forEach var="currentUser" items="${userList}">

                                    <option value="${currentUser.userId}"><c:out value="${currentUser.userName}"></c:out></option>

                                </c:forEach>
                            </select>

                            <!--</div>-->
                        </div>

                        <div class="col-md-2"></div>
                    </div>


                    <hr>

                    <div class="row">
                        <div class="col-md-2">                        

                        </div>

                        <div status="${startDateFlag}" id="start-div" class="col-md-8">
                            <input name="startDate" value="<c:out value="${startDate}"></c:out>" class="form-control"/>
                            </div>

                            <div class="col-md-2"></div>

                        </div>

                        <hr>

                        <div class="row">
                            <div class="col-md-2">                        

                        </div>




                        <div status="${endDateFlag}" id="end-div" class="col-md-8">
                            <!-- <div  id="end-div">-->
                            <input name="endDate" value="<c:out value="${endDate}"></c:out>" class="form-control"/>
                            </div>

                            <div class="col-md-2"></div>

                        </div>

                        <hr>

                        <div class="row">
                            <div class="col-md-2">                        

                        </div>


                        <div status="${bodyFlag}" id="body-div" class="col-md-8">
                            <textarea name="postBody" id="postBody"><c:out value="${postBody}"></c:out></textarea>
                                <br>

                            </div>

                            <div class="col-md-2"></div>

                        </div>


                        <input type="submit" style="width:100%"class="btn btn-primary btn-create" value="Submit"/>
                    </form>
                </article>
            </div>









            <script src="https://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/js/CM-Status.js"></script>
        <script
            src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"
            integrity="sha256-xI/qyl9vpwWFOXz7+x/9WkG5j/SVnSw21viy8fWwbeE="
        crossorigin="anonymous"></script>
    </body>
</html>
