<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="publicContextPath" value="${pageContext.request.contextPath}/public" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/ticket_app_style.css"/>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#start_date").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'yy-mm-dd'
            });
            $("#end_date").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'yy-mm-dd'
            });
        });
    </script>
</head>
<body>

<div class="jumbotron text-center" style="padding: 30px; background-color: #eeeeee;">
    <h1 class="display-3 page_title">Ticketing system</h1>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-5 int_div rounded-lg" style="background-color: #006600">
            <h2>Access Section</h2>
            <form class="form-inline" action="${publicContextPath}/access" method="post">
                <label for="accessCode" class="mr-sm-2">Access code:</label>
                <input type="text" class="form-control form-control-sm mb-2 mr-sm-2" name="accessCode" id="accessCode"
                       placeholder="Enter the access code">
                <input type="submit" class="btn btn-info btn-sm mb-2" value="Submit">
            </form>
            <br>
            <span>Code entered: <strong>${access_code}</strong>. ${access_message}</span>
            <br>
            <br>
            <span>Ticket code: <strong>${new_ticket_code}</strong>.
                Ticket enter date: <strong>${new_ticket_enter_date}</strong></span>
        </div>

        <div class="col-sm-5 int_div rounded-lg" style="background-color: #336666">
            <h2>Payment Section</h2>
            <form class="form-inline" action="${publicContextPath}/payments" method="post">
                <label for="ticketCode" class="mr-sm-2">Ticket code:</label>
                <input type="text" class="form-control form-control-sm mb-2 mr-sm-2" name="ticketCode" id="ticketCode"
                       placeholder="Enter the ticket code">
                <input type="submit" class="btn btn-info btn-sm mb-2" value="Calculate">
            </form>
            <br>
            <span><strong>${payment_message}</strong></span>
            <br>
            <br>
            <form class="form-inline" action="${publicContextPath}/cash" method="post">
                <label for="ticketCodeforPayment" class="mr-sm-2">Ticket code:</label>
                <input type="text" class="form-control form-control-sm mb-2 mr-sm-2" name="tickeCodeForPayment" id="ticketCodeforPayment"
                       placeholder="Enter the ticket code">
                <label for="amountToPay" class="mr-sm-2">Amount to pay:</label>
                <input type="text" class="form-control form-control-sm mb-2 mr-sm-2" name="amountToPay" id="amountToPay"
                       placeholder="Insert amount to pay">
                <input type="submit" class="btn btn-info btn-sm mb-2" value=" Pay ">
                <span><strong>${payed_amount_message}</strong></span>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-5 int_div rounded-lg" style="background-color: #003366">
            <h2>Buy Subscription Section</h2>
            <form class="form-inline" action="${publicContextPath}/subscriptions" method="post">
                <label for="start_date" class="mr-sm-2">Choose period:</label>
                <input type="text" class="form-control form-control-sm mb-2 mr-sm-2" id="start_date" name="startDate"
                       placeholder="Choose start date" required>
                <input type="text" class="form-control form-control-sm mb-2 mr-sm-2" id="end_date" name="endDate"
                       placeholder="Choose end date" required>
                <input type="submit" class="btn btn-info btn-sm mb-2" value="Submit">
            </form>
            <br>
            <div>
                <span>Subscription details:</span>
                <span style="background-color: white; color: red;"> <b>${dates_error}</b> </span><br>
                <br>
                <span>Start date: <strong>${sub_start_date}</strong>. End date: <strong>${sub_end_date}</strong>. Code:
                    <strong>${sub_code}</strong>.</span>
            </div>
        </div>

        <div class="col-sm-5 int_div rounded-lg" style="background-color: #660000;">
            <h2>Exit Section</h2>
            <form class="form-inline" action="${publicContextPath}/exit" method="post">
                <label for="exitCode" class="mr-sm-2">Ticket / Subscription code:</label>
                <input type="text" class="form-control form-control-sm mb-2 mr-sm-2" name="exitCode" id="exitCode"
                       placeholder="Enter your code">
                <input type="submit" class="btn btn-info btn-sm mb-2" value="Check">
            </form>
            <br>
            <span>You entered this code: <strong>${exit_code}</strong></span>
            <br>
            <br>
            <span><strong>${exit_message}</strong></span>
        </div>
    </div>
</div>

<%--<a href="${pageContext.request.contextPath}/personList">Person List</a>--%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>