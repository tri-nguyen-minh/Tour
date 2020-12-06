<%-- 
    Document   : tourSearch
    Created on : Jun 6, 2020, 11:16:00 AM
    Author     : TNM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Tours Page</title>
        <meta charset="utf-8">
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <header class="header">
            <div class="top">
                <c:if test="${sessionScope.USER != null}" var="LoginCheck">
                    <div class="welcome">Welcome, ${sessionScope.USER.name}!</div>
                    <div class="logout">
                        <form action="LogoutController" method="POST">
                            <input type="submit" name="action" value="Logout">
                        </form>
                    </div>
                </c:if>
                <c:if test="${!LoginCheck}">
                    <div class="welcome">Welcome to our Travel Page</div>
                    <div class="logout">
                        <form action="AccountController" method="POST">
                            <input type="hidden" name="txtNameSearch" value="${requestScope.NAMESEARCH}"/>
                            <input type="hidden" name="dateLow" value="${requestScope.DATELOW}"/>
                            <input type="hidden" name="dateHigh" value="${requestScope.DATEHIGH}"/>
                            <input type="hidden" name="priceRange" value="${requestScope.PRICERANGE}"/>
                            <input type="hidden" name="PAGE" value="${requestScope.PAGE}"/>
                            <input type="hidden" name="action" value="Login Account"/>
                            <input type="submit" name="Login" value="Login">
                        </form>
                    </div>
                </c:if>
            </div>
            <div class="head">
            </div>
            <nav style="margin-top: -10px">
                <c:if test="${!LoginCheck}">
                    <ul>
                        <li>
                            <a href="TourSearchController" class="active">Main Page</a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${LoginCheck}">
                    <c:if test="${sessionScope.USER.role eq 'admin'}">
                        <ul>
                            <li>
                                <a href="TourSearchController" class="active">Main Page</a>
                            </li>
                            <li>
                                <a href="TourCreateController">Create a new Tour</a>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.USER.role eq 'user'}">
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
                    </c:if>
                </c:if>
            </nav>
        </header>
        <div class="main">
            <section class="user-main">
                <div class="info">
                    <div class="search-info">
                        <h1 id="headline">Search Tour</h1>
                        <form action="TourSearchController" method="POST">
                            </br></br><h1 style="margin-bottom: 15px">Search by Destination</h1>
                            <input style="width: 200px; margin-left: -1px" type="text" id="id" name="txtNameSearch" value="${requestScope.NAMESEARCH}" placeholder="Tour's Destination"/></br>
                            <h1 style="margin-bottom: 15px">Search by Departing Time</h1>
                            <h1 style="margin-bottom: 15px">FROM:</h1><input type="date" id="date-input" name="dateLow" value="${requestScope.DATELOW}"/></br>
                            <h1 style="margin-bottom: 15px">TO:</h1><input type="date" id="date-input" name="dateHigh" value="${requestScope.DATEHIGH}"/>
                            </br></br>
                            <h1 style="margin-bottom: 15px">Search by Price</h1>
                            <select class="comboBox" name="priceRange" style="width: 200px; height: 30px">
                                <c:forEach items="${requestScope.PRICELIST}" var="priceRange">
                                    <option value="${priceRange}" ${priceRange eq requestScope.PRICERANGE ? 'selected="selected"' :''}>${priceRange}</option>
                                </c:forEach>
                            </select>
                            <input style="margin-left: 0px" type="submit" id="search-test" name="action" value="Search"/>
                        </form>
                    </div>
                    <div class="main-info">
                        <c:if test="${requestScope.TOURLIST != null}">
                            <c:if test="${not empty requestScope.TOURLIST}" var="tourList">
                                <h2 style="font-size: 15px; margin-top: -40px"> Page 
                                    <b style="font-size: 20px">${requestScope.PAGE}</b> 
                                    of <b style="font-size: 20px">${requestScope.TOTALPAGE}</b> Page(s)</h2>
                                    <c:forEach items="${requestScope.TOURLIST}" var="dto">
                                    <div class="tour-box">
                                        <div class="tour-left">
                                            <h2 style="font-size: 17px;">${dto.tourName}</h2></br>
                                            <p style="font-size: 14px; margin-top: -20px">Destination: ${dto.destination}</p></br>
                                            <p style="font-size: 14px; margin-top: -30px">Departing Date: ${dto.dateStart}</p></br>
                                            <p style="font-size: 14px; margin-top: -30px">Finishing Date: ${dto.dateEnd}</p></br>
                                        </div>
                                        <div class="tour-mid">
                                            <h2 style="float: right;font-size: 17px">$${dto.price}</h2></br>
                                            <p style="font-size: 14px; margin-top: 35px">Total slots: ${dto.quota}</p></br>
                                            <p style="font-size: 14px; margin-top: -30px">Available slot: ${dto.slotAvailable}</p></br>
                                            <c:if test="${LoginCheck}">
                                                <c:if test="${sessionScope.USER.role eq 'user'}">
                                                    <c:if test="${dto.status eq 'Inactive'}" var="StatusCheck">
                                                        <b id="not-available">This tour is no longer available</b>
                                                    </c:if>
                                                    <c:if test="${!StatusCheck}">
                                                        <form action="BookingController" method="POST">
                                                            <input type="hidden" name="txtNameSearch" value="${requestScope.NAMESEARCH}"/>
                                                            <input type="hidden" name="dateLow" value="${requestScope.DATELOW}"/>
                                                            <input type="hidden" name="dateHigh" value="${requestScope.DATEHIGH}"/>
                                                            <input type="hidden" name="priceRange" value="${requestScope.PRICERANGE}"/>
                                                            <input type="hidden" name="PAGE" value="${requestScope.PAGE}"/>
                                                            <input type="hidden" name="tourId" value="${dto.id}"/>
                                                            <p id="label">book tour slot(s)</p>
                                                            <select name="slot${dto.id}" class="slot-box">
                                                                <c:forEach begin="1" end="${dto.slotAvailable}" var="slot">
                                                                    <option value="${slot}">${slot}</option>
                                                                </c:forEach>
                                                            </select>
                                                            <input id="book-button" name="action" type="submit" value="Book"/>
                                                        </form>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${!LoginCheck}">
                                                <c:if test="${dto.status eq 'Inactive'}" var="StatusCheck">
                                                    <b id="not-available">This tour is no longer available</b>
                                                </c:if>
                                                <c:if test="${!StatusCheck}">
                                                    <form action="AccountController" method="POST">
                                                        <input type="hidden" name="txtNameSearch" value="${requestScope.NAMESEARCH}"/>
                                                        <input type="hidden" name="dateLow" value="${requestScope.DATELOW}"/>
                                                        <input type="hidden" name="dateHigh" value="${requestScope.DATEHIGH}"/>
                                                        <input type="hidden" name="priceRange" value="${requestScope.PRICERANGE}"/>
                                                        <input type="hidden" name="PAGE" value="${requestScope.PAGE}"/>
                                                        <input type="hidden" name="action" value="Login Account"/>
                                                        <input type="submit" id="login-button" value="Login to book">
                                                    </form>
                                                </c:if>
                                            </c:if>
                                        </div>

                                        <div class="tour-right">
                                            <img class="tour-image" src="${dto.imageLink}" width="304" height="163"/>
                                        </div>
                                    </div>
                                </c:forEach>
                                <c:if test="${requestScope.PAGE !=null}">
                                    <form action="TourSearchController" method="POST">
                                        <input type="hidden" name="txtNameSearch" value="${requestScope.NAMESEARCH}"/>
                                        <input type="hidden" name="dateLow" value="${requestScope.DATELOW}"/>
                                        <input type="hidden" name="dateHigh" value="${requestScope.DATEHIGH}"/>
                                        <input type="hidden" name="priceRange" value="${requestScope.PRICERANGE}"/>
                                        <input type="hidden" name="PAGE" value="${requestScope.PAGE}"/>
                                        <div class="info-left">
                                            <c:if test="${(requestScope.PAGE != 1)}">
                                                <input style="width: 200px" type="submit" name="action" value="Previous Page">
                                            </c:if>
                                        </div>
                                        <div class="info-right">
                                            <c:if test="${(requestScope.PAGE != requestScope.TOTALPAGE)}">
                                                <input style="width: 200px"  type="submit" name="action" value="Next Page">
                                            </c:if>
                                        </div>
                                    </form>
                                </c:if>
                            </div>
                        </c:if>
                        <c:if test="${!tourList}">
                            <h2 style="font-size: 20px; margin-top: -40px">
                                No Tour found!
                            </h2>
                        </c:if>
                    </c:if>
                </div>
            </section>

    </body>
</html>
