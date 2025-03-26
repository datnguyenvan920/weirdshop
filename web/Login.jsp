<%-- 
    Document   : Login
    Created on : Jan 16, 2025, 10:41:32 AM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Login | E-Shopper</title>
        <link href="asset/css/bootstrap.min.css" rel="stylesheet">
        <link href="asset/css/font-awesome.min.css" rel="stylesheet">
        <link href="asset/css/prettyPhoto.css" rel="stylesheet">
        <link href="asset/css/price-range.css" rel="stylesheet">
        <link href="asset/css/animate.css" rel="stylesheet">
        <link href="asset/css/main.css" rel="stylesheet">
        <link href="asset/css/responsive.css" rel="stylesheet">
        <link rel="shortcut icon" href="asset/images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="asset/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="asset/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="asset/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="asset/images/ico/apple-touch-icon-57-precomposed.png">
    </head>
    <body>
        <jsp:include page="/Navbar.jsp" />
        <section id="form"><!--form-->
            <div class="container">
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-1">
                        <div class="login-form"><!--login form-->
                            <h2>Login to your account</h2>
                            <form action="login" method="get">
                                <input type="email" placeholder="Email" name="email"/>
                                <input type="password" placeholder="Password" name="password"/>

                                <button type="submit" class="btn btn-default">Login</button>
                            </form>
                        </div><!--/login form-->
                        <c:if test="${not empty loginmessage}">
                            <p style="margin-top: 10px; color: red">${loginmessage}</p>      <!--Log message-->
                        </c:if>
                    </div>
                    <div class="col-sm-1">
                        <h2 class="or">OR</h2>
                    </div>
                    <div class="col-sm-4">
                        <div class="signup-form"><!--sign up form-->
                            <h2>New User Signup!</h2>
                            <form action="register" method="get">
                                <input type="text" placeholder="Name" name="username"/>
                                <input type="email" placeholder="Email Address" name="email"/>      
                                <input type="password" placeholder="Password" name="password"/>
                                <button type="submit" class="btn btn-default">Signup</button>
                            </form>
                        </div><!--/sign up form-->
                        <c:if test="${not empty signupmessage}">
                            <p style="margin-top: 10px; color: red">${signupmessage}</p>         <!--Log message-->
                        </c:if>
                    </div>
                </div>
            </div>
        </section><!--/form-->

        <jsp:include page="/Footer.jsp" />


        <script src="asset/js/jquery.js"></script>
        <script src="asset/js/price-range.js"></script>
        <script src="asset/js/jquery.scrollUp.min.js"></script>
        <script src="asset/js/bootstrap.min.js"></script>
        <script src="asset/js/jquery.prettyPhoto.js"></script>
        <script src="asset/js/main.js"></script>
    </body>
</html>
