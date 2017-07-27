<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Very Typical Vending Machine</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.css" rel="stylesheet">
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
                margin-top: 12.5%;
                background-color: black;
                color: white;
                width:500px;
                height:250px;
                font-size: 8em;
            }

            .item-btn .money-btn{

            }
        </style>
    </head>
    <body class="black">
        <div class="container black">
            <div class="row text-center">
                <h1> Vending Machine </h1>
                <hr/>
            </div>
            <div class="row">
                <div  class="text-center" id="items">
                    <a href="loadMachine"><input type="button" class="btn btn-primary" value="OPEN"/></a>


            </div>
        </div>
    </body>
</html>

