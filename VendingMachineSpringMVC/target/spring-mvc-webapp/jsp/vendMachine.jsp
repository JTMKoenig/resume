    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Beach Day Vending</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <style>
            .center-text{
                text-align: center;
                background-color: black;
                color: white;
            }



            .black {
                background-color: black;
            }

            h1, h2, h3, body, p {
                color: white;
            }

            .btn{
                background-color: black;
                color: white;
            }

            .item {
                border: 1px white solid;
                width:30%;
                margin:8px;
                display: inline-block;
            }
        </style>
    </head>
    <body class="black">
        <div class="container black">
            <div class="row text-center">
                <h1> Beach Supplies </h1>
                <hr/>
            </div>
            <div class="row">
                <div  class="col-md-9" id="items">
                    
                        <ul style="list-style:none">
                        <c:forEach items="${items}" var="item">
                            <li>
                                <a href="${pageContext.request.contextPath}/pickItem/${item.id}">
                                    <p class="item col-md-3">
                                        ${item.id}<br/>
                                        ${item.name}<br/>
                                        ${item.price}<br/>
                                        ${item.qty}
                                    </p>
                                </a>
                            </li>
                            
                        </c:forEach>
                        </ul>
                   
                    </div>
                <div class="col-md-3 text-center" id="money">
                    <div class="row">

                        <form class="form-horizontal" role="form" id="moneyForm">
                            <h3>Total $ In</h3>
                            <div class="form-group text-center">
                                <input type="text" id="moneyIn" placeholder="$0.00" value="${money}" class="center-text" readonly/>
                            </div>
                            <div class="form-group">
                                <a href="${pageContext.request.contextPath}/addMoney/1">
                                <button type="button" id="dollarBtn" class="btn btn-default col-md-offset-2 col-md-4 money-btn" data-amt="100" >
                                    Add Dollar
                                </button>
                                </a>
                                <a href="${pageContext.request.contextPath}/addMoney/2">
                                <button type="button" id="quarterBtn" class="btn btn-default col-md-4 money-btn" data-amt="25">
                                    Add Quarter
                                </button>
                                </a>
                            </div>
                            <div class="form-group">
                                <a href="${pageContext.request.contextPath}/addMoney/3">
                                <button type="button" id="dimeBtn" class="btn btn-default col-md-offset-2 col-md-4 money-btn" data-amt="10">
                                    Add Dime
                                </button>
                                </a>
                                <a href="${pageContext.request.contextPath}/addMoney/4">
                                <button type="button" id="nickelBtn" class="btn btn-default col-md-4 money-btn" data-amt="5" >
                                    Add Nickel
                                </button>
                                </a>
                            </div>
                        </form>
                        <hr/>


                    </div>
                    <div class="row">

                        <form class="form-horizontal" role="form" id="messagesForm">
                            <h3>Messages</h3>
                            <div class="form-group">
                                <textarea id="messageScreen" class="center-text" readonly>${message}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="itemScreen" class="col-md-3 control-label">
                                    Item:
                                </label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control center-text" id="itemScreen" value="${item}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <a href="${pageContext.request.contextPath}/buyItem/${item}/${money}">
                                <button type="button" class="btn btn-default" id="makePurchaseBtn">
                                    Make Purchase
                                </button>
                                </a>
                            </div>

                        </form>
                    </div>
                    <hr/>

                    <div class="row">

                        <form class="form-horizontal" role="form" id="changeForm">
                            <h3>Change</h3>
                            <div class="form-group">
                                <textarea class="col-md-12 center-text" id="changeScreen" readonly>${change}</textarea>
                            </div>
                            <div class="form-group">
                                <a href="${pageContext.request.contextPath}/returnChange">
                                <button type="button" class="btn btn-default col-md-12"  id="returnChangeBtn">
                                    Change Return
                                </button>
                                </a>
                            </div>

                        </form>
                    </div>
                </div>

                    </div>
            </div>
        </div>
    </body>
</html>

