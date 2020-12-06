<%-- 
    Document   : login
    Created on : Jun 5, 2020, 2:04:23 PM
    Author     : TNM
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="utf-8">
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <header class="header">
            <div class="top">
            </div>
            <div class="head">
                <img src="img/Loginlogo.jpg" width="414" height="110">
            </div>
        </header>

        <div class="main">
            <section class="user-main">
                <div class="info">
                    <h1>Log in to your account!</h1>
                    <form id="form" action="AccountController" method="POST">
                        <input type="hidden" name="txtNameSearch" value="${requestScope.NAMESEARCH}"/>
                        <input type="hidden" name="dateLow" value="${requestScope.DATELOW}"/>
                        <input type="hidden" name="dateHigh" value="${requestScope.DATEHIGH}"/>
                        <input type="hidden" name="priceRange" value="${requestScope.PRICERANGE}"/>
                        <input type="hidden" name="PAGE" value="${requestScope.PAGE}"/>
                        <p class="login-field">
                            <input type="text" name="txtId" placeholder="Username" value="${param.txtId}">
                        </p>
                        <p class="login-field">
                            <input type="password" name="txtPassword" placeholder="Password">
                        </p>
                        <p>
                            <font color="red">
                            ${requestScope.ERROR}
                            </font>
                        </p>
                        <p>
                            <input type="submit" id="login" name="action" value="Login">
                        </p>
                    </form>
                </div>
            </section>
        </div>
    </body>
</html>