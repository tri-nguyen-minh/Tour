<%-- 
    Document   : tourCreate
    Created on : Jun 8, 2020, 2:20:05 PM
    Author     : TNM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Tour Creation Page</title>
        <meta charset="utf-8">
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <header class="header">
            <div class="top">
                <div class="welcome">Welcome, ${sessionScope.USER.name}!</div>
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
                        <a href="TourCreateController">Create a new Tour</a>
                    </li>
                </ul>
            </nav>
        </header>
        <div class="main">
            <section class="user-main">
                <div class="info">
                    <h1 id="header">Create a new Tour!</h1>
                    <form action="TourCreateController" method="POST">
                        <div class="create-left">
                            <h1 class='label'>Tour's Name</h1><input type="text" name="txtName" placeholder="Tour's Name" value="${param.txtName}"/></br>
                            <font color='red' font-size='10' style="margin-left: 5px">
                            ${requestScope.INVALID.nameInvalid}
                            </font></br>
                            <h1 class='label'>Destination</h1><input type="text" name="txtDestination" placeholder="Tour's Destination" value="${param.txtDestination}"/></br>
                            <font color='red' font-size='10' style="margin-left: 5px">
                            ${requestScope.INVALID.destinationInvalid}
                            </font></br>
                            <h1 class='label'>Quota</h1><input type="text" name="txtQuota" placeholder="Tour's Quota" value="${param.txtQuota}"/></br>
                            <font color='red' font-size='10' style="margin-left: 5px">
                            ${requestScope.INVALID.quotaInvalid}
                            </font></br>
                            <h1 class='label'>Price</h1><input type="text" name="txtPrice" placeholder="Tour's Price" value="${param.txtPrice}"/></br>
                            <font color='red' font-size='10' style="margin-left: 5px">
                            ${requestScope.INVALID.priceInvalid}
                            </font></br>
                            <input type="submit" name="action" value="Add Tour" style="width: 150px;margin-left: 155px; margin-top: 35px"/>
                        </div>
                        <div class="create-right">
                            <h1 class='label'>Departing Date</h1><input id="date-input" type="date" name="dateDepart" value="${param.dateDepart}"/></br>
                            <font color='red' font-size='10'>
                            ${requestScope.INVALID.dateDepartInvalid}
                            </font></br>
                            <h1 class='label'>Finishing Date</h1><input id="date-input" type="date" name="dateFinish" value="${param.dateFinish}"/></br>
                            <font color='red' font-size='10'>
                            ${requestScope.INVALID.dateFinishInvalid}
                            </font></br>
                            <h1 class='label'>Image Link</h1>
                            <select class="comboBox" id="txtImage" name="txtImage" onclick="testImage()">
                                <option value="TBA.jpg" ${requestScope.IMAGE eq 'TBA.jpg' ? 'selected="selected"' : ''}>TBA.jpg</option>
                                <c:forEach items="${requestScope.IMAGELIST}" var="image">
                                    <option value="${image}" ${requestScope.IMAGE eq image ? 'selected="selected"' : ''}>${image}</option>
                                </c:forEach>
                            </select>
                            <font color='red' font-size='10'>
                            Images from tourIMG folder!
                            </font></br></br>
                            <img src="img/tourIMG/TBA.jpg" id="imageFrame" width="300" height="168" style="margin-left: 5px;border: 2px solid">
                        </div>
                    </form>

                </div>
            </section>
        </div>
        <script>
            function testImage() {
                var image = document.getElementById("txtImage").value;
                var name = 'img/tourIMG/' + image;
                document.getElementById("imageFrame").src = name;
            }
        </script>
    </body>
</html>
