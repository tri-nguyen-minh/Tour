<%-- 
    Document   : cartPage
    Created on : Jun 9, 2020, 12:00:08 AM
    Author     : TNM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${sessionScope.USER.name}'s Cart</title>
        <meta charset="utf-8">
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <header class="header">
            <div class="top">
                <div class="welcome">Thank you for booking at our agency!</div>
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
                        <c:url value="BookingController" var="ReloadLink">
                            <c:param name="action" value="Reload"/>
                            <c:param name="txtDiscount" value="${requestScope.DISCOUNTCODE}"/>
                            <c:param name="discountValue" value="${requestScope.DISCOUNTVALUE}"/>
                        </c:url>
                        <c:if test="${sessionScope.CARTSIZE == 0}" var="CartCheck">
                            <a href="${ReloadLink}">My Cart</a>
                        </c:if>
                        <c:if test="${!CartCheck}">
                            <a href="${ReloadLink}">My Cart (${sessionScope.CARTSIZE})</a>
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
                        <c:if test="${sessionScope.CARTSIZE != 0}" var="CartCheck">
                            <form action="BookingController" method="POST">
                                <input type="hidden" name="action" id="action"/>
                                <h2 style="font-size: 20px; margin-top: -50px"> Booked Tour(s):
                                    <b style="font-size: 23px">${sessionScope.CARTSIZE}</b></h2>
                                <font color='red' font-size='10' style="margin-left: 5px">
                                ${requestScope.CONFIRMERROR}
                                </font></br>
                                <table cellspacing="0" cellpadding="5" style="width: 950px">
                                    <thead style="">
                                        <tr>
                                            <th  class="first-col">Tour's Name</th>
                                            <th class="mid-col">Destination</th>
                                            <th class="mid-col">Departing Time</th>
                                            <th class="mid-col">Tour's Price</th>
                                            <th class="mid-col">Booked Slot(s)</th>
                                            <th  class="last-col">Total Price</th>

                                            <th class="delete-col">
                                                <input type="submit" value="Remove" id="cart-button" onclick="confirmDeletion()"/>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody style=" margin-top: 10px">
                                        <c:forEach items="${sessionScope.CART}" var="tour">
                                            <tr>
                                                <td  class="cart-first">${tour.tourName}</td>
                                                <td  class="cart-mid">${tour.destination}</td>
                                                <td  class="cart-mid">${tour.dateStart}</td>
                                                <td  class="cart-mid">$${tour.price} per slot</td>
                                                <td  class="cart-mid">${tour.quota}</td>
                                                <td  class="cart-mid">
                                                    $${tour.quota*tour.price*(100-requestScope.DISCOUNTVALUE)/100}
                                                </td>
                                                <td  class="cart-last">
                                                    <input type="checkbox" value="${tour.id}" name="${tour.id}ToDelete" style="width: 18px; height: 18px"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table></br>
                                
                                <div class="tour-right" style=" margin-top: 40px">
                                    <input style="width: 180px; margin: -20px -80px; float: right" class="confirm-button" type="submit" value="Confirm Booking" onclick="confirmBooking()"/>
                                </div>
                            </form>
                        </c:if>
                        <c:if test="${!CartCheck}">
                            <h1 id="header">You haven't booked any tour!</h1>   
                        </c:if>
                        <font color='red' font-size='10' style="margin-left: 5px">
                        ${requestScope.CONFIRMERROR}
                        </font></br>
                    </div>
                </div>
            </section>
        </div>
        <script>
            function confirmDeletion() {
                if (confirm("Tour(s) marked for deletion will be removed from your cart." +
                        "\nDo you want to proceed?")) {
                    document.getElementById("action").value = "Delete";
                } else {
                    document.getElementById("action").value = "Reload";
                }
            }
            function update() {
                document.getElementById("action").value = "Update";
            }
            function useDiscount() {
                document.getElementById("action").value = "Use Discount";
            }
            function confirmBooking() {
                if (confirm("Proceed to book tour(s)?")) {
                    document.getElementById("action").value = "Confirm";
                } else {
                    document.getElementById("action").value = "Reload";
                }
            }
        </script>
    </body>
</html>
