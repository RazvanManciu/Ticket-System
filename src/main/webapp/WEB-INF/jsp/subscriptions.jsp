<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ticketing System - Private section - Manage subscriptions</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/private_section_style.css"/>
</head>

<body>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <div class="container-fluid" style="padding: 0px;">
        <div class="jumbotron-fluid text-center" style="padding: 30px; background-color: #eeeeee;">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <h1 class="display-3 page_title">Ticketing system</h1>
            <h2 class="display-4 page_title">Welcome ${pageContext.request.userPrincipal.name}</h2>
        </div>

        <nav class="navbar navbar-expand-sm justify-content sticky-top" style="background-color: #006666; margin-bottom: 30px;">
                <%--        <a class="navbar-brand" href="#">Ticketing system</a>--%>
            <a class="navbar-brand mr-sm-3" href="${contextPath}/private/welcome">
                <img src="${contextPath}/resources/img/sorinmiron_img.jpg" alt="User thumbnail" style="width: 45px">
            </a>

            <ul class="navbar-nav">
                <li class="nav-item mx-sm-4">
                    <a class="nav-link" href="${contextPath}/private/prices">Prices</a>
                </li>
                <li class="nav-item mr-sm-4">
                    <a class="nav-link" href="#">Subscriptions</a>
                </li>
                <li class="nav-item mr-sm-4">
                    <a class="nav-link" href="${contextPath}/private/spaces">Parking Spaces</a>
                </li>
                <li class="nav-item mr-sm-4">
                    <a class="nav-link" href="${contextPath}/private/statistics">Statistics</a>
                </li>
                <li class="nav-item mr-sm-4">
                    <a class="nav-link" href="#" onclick="document.forms['logoutForm'].submit()">Logout</a>
                </li>
            </ul>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-7 int_div rounded-lg" style="background-color: #006600">
                    <h2>Manage Subscriptions</h2>
                    <br>
                    <h4>Subscriptions List:</h4>
                    <br>
                    <c:if test="${subscriptionsList != null}">
                        <table>
                            <th>Id</th>
                            <th>Code</th>
                            <th>Start Date</th>
                            <th>Expiration Date</th>
                            <c:forEach items="${subscriptionsList}" var="item">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.code}</td>
                                    <td>${item.startDate}</td>
                                    <td>${item.endDate}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${subscriptionsList == null}">
                        <span>${subscriptions_message}</span>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</c:if>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
