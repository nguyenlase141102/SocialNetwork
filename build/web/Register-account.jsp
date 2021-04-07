

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
                                <p style="color: red">${errorRegister}</p>
                                <h2 class="log-title">Register</h2>
                                <p>
                                    Don’t use Winku Yet? <a href="#" title="">Take the tour</a> or <a href="#" title="">Join now</a>
                                </p>
                                <form  action="SocialNetworkManagement" method="post">
                                    <div class="form-group">	
                                        <input type="email" required="required" name="txtUserEmail" value="${valueSaveEmail}"/>
                                        <label class="control-label" for="input"><a href="https://wpkixx.com/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="6c29010d05002c">[email&#160;protected]</a></label><i class="mtrl-select"></i>
                                    </div>
                                    <div class="form-group">	
                                        <input type="text" required="required" name="txtUserName" value="${valueSaveName}"/>
                                        <label class="control-label" for="input">User Name</label><i class="mtrl-select"></i>
                                    </div>
                                    <div class="form-group">	
                                        <input type="password" required="required" name="txtUserPassword" value="${valueSavePassword}"/>
                                        <label class="control-label" for="input">Password</label><i class="mtrl-select"></i>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" checked="checked"/><i class="check-box"></i>Accept Terms & Conditions ?
                                        </label>
                                    </div>
                                    <a href="Login-account.jsp" title="" class="already-have">Already have an account</a>
                                    <div class="submit-btns">
                                        <input type="submit" name="btAction" value="Register"/>
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