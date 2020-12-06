<%-- 
    Document   : discountPage
    Created on : Jun 9, 2020, 4:50:41 PM
    Author     : TNM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>${sessionScope.USER.name}'s Discount List</title>
        <meta charset="utf-8">
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <header class="header">
            <div class="top">
                <div class="welcome">Remember to make use of your discount!</div>
                <div class="logout">
                    <form action="LogoutController" method="POST">
                        <input type="submit" name="action" value="Logout">
                    </form>
                </div>
            </div>
            <div class="head">
            </div>
            <nav>
                <ul>
                    <li>
                        <a href="TourSearchController" class="active">Main Page</a>
                    </li>
                    <li>
                    <c:if test="${sessionScope.CARTSIZE == 0}" var="CartCheck">
                        <a href="cartPage.jsp">My Cart</a>
                    </c:if>
                    <c:if test="${!CartCheck}">
                        <a href="cartPage.jsp">My Cart (${sessionScope.CARTSIZE})</a>
                    </c:if>
                    </li>
                    <li>
                    <c:if test="${sessionScope.DISCOUNTCOUNT == 0}" var="DiscountCheck">
                        <a href="discountPage.jsp">My Discount</a>
                    </c:if>
                    <c:if test="${!DiscountCheck}">
                        <a href="discountPage.jsp">My Discount (${sessionScope.DISCOUNTCOUNT})</a>
                    </c:if>
                    </li>
                </ul>
            </nav>
        </header>
        <div class="main">
            <section class="user-main">
                <div class="info">
                    <div class="main-info" style="margin-right: 400px">
                        <c:if test="${sessionScope.DISCOUNTCOUNT != 0}" var="ListCheck">
                            <h1 id="header" style="margin-left: 20px">Your Available Discount:</h1>
                        <ul>
                        <c:forEach items="${sessionScope.DISCOUNT}" var="discount">
                            <li style="margin-bottom: 20px">
                                <b style="font-size: 18px">Discount Code: <b style="font-size: 24px; color: #e24e42">${discount.discountCode}</b></b></br></br>
                                <b style="font-size: 18px; margin-left: 20px">Use the code before ${discount.dateExpire} to take ${discount.value}% off your next order.</b>
                            </li>
                        </c:forEach>
                            </ul>
                        </c:if>
                        <c:if test="${!ListCheck}">
                    <h1 id="header" style="margin-left: 20px">You don't have any available Discount.</h1>
                        </c:if>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>
