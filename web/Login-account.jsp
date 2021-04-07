

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <title>Winku Social Network Toolkit</title>
        <link rel="icon" href="images/fav.png" type="image/png" sizes="16x16"> 

        <link rel="stylesheet" href="css/main.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/color.css">
        <link rel="stylesheet" href="css/responsive.css">

    </head>
    <body>
        <!--<div class="se-pre-con"></div>-->
        <div class="theme-layout">
            <div class="container-fluid pdng0">
                <div class="row merged">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                        <div class="land-featurearea">
                            <div class="land-meta">
                                <h1>Winku</h1>
                                <p>
                                    Winku is free to use for as long as you want with two active projects.
                                </p>
                                <div class="friend-logo">
                                    <span><img src="images/wink.png" alt=""></span>
                                </div>
                                <a href="#" title="" class="folow-me">Follow Us on</a>
                            </div>	
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                        <div class="login-reg-bg">
                            <div class="log-reg-area sign">
                                <p style="color: green">${messagesRegister}</p>
                                <p style="color: red">${errorLogin}</p>
                                <h2 class="log-title">Login</h2>
                                <p>
                                    Don’t use Winku Yet? <a href="#" title="">Take the tour</a> or <a href="#" title="">Join now</a>
                                </p>
                                <form  action="SocialNetworkManagement" method="post">
                                    <div class="form-group">	
                                        <input type="email" id="input" required="required" value="${valueSaveEmail}" name="txtUserEmail"/>
                                        <label class="control-label" for="input">User Email</label><i class="mtrl-select"></i>
                                    </div>
                                    <div class="form-group">	
                                        <input type="password" required="required" value="${valueSavePassword}" name="txtUserPassword"/>
                                        <label class="control-label" for="input">Password</label><i class="mtrl-select"></i>
                                    </div>
                                    <c:if test="${not empty already}">
                                        <div class="form-group">	
                                            <input type="text" required="required" name="authCode"/>
                                            <label class="control-label" for="input">Verify Code</label><i class="mtrl-select"></i>
                                        </div>
                                    </c:if>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" checked="checked"/><i class="check-box"></i>Always Remember Me.
                                        </label>
                                    </div>
                                    <a href="SocialNetworkManagement?btAction=RegisterPage" title="" class="forgot-pwd">Register Account ?</a>
                                    <div class="submit-btns">
                                        <input type="submit" name="btAction" value="Login" />                                
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script data-cfasync="false" src="../../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="js/main.min.js"></script>
        <script src="js/script.js"></script>

    </body>	

</html>