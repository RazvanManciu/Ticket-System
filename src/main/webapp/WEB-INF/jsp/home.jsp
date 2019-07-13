<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/ticket_app_style.css"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#start_date").datepicker({
                changeMonth:true,
                changeYear:true,
                dateFormat:'yy-mm-dd'
            });
            $("#end_date").datepicker({
                changeMonth:true,
                changeYear:true,
                dateFormat:'yy-mm-dd'
            });
        });
    </script>
</head>
<body>
<h1 class="page_title">Ticketing system</h1>
<h2 class="page_title">Welcome ${user}</h2>
<div class="ext_div">
    <div class="int_div" style="border-color: red;">
        <h2>Access Section</h2>
        <form action="/access" method="post">
            Access code: <input type="text" name="accessCode" placeholder="Please enter the access code !">
            <input type="submit" value="Submit">
        </form>
        <p>${access_code}</p>
        <p>${access_message}</p>
    </div>
</div>
<div class="ext_div">
    <div class="int_div" style="border-color: blue;">
        <h2>Payment Section</h2>
        <form action="/payments" method="post">
            Ticket code: <input type="text" name="ticketCode" placeholder="Please enter the ticket code !">
            <input type="submit" value="Calculate amount">
        </form>
        <p>You must pay: ${ticket_amount} $</p>
    </div>
</div>
<div class="ext_div">
    <div class="int_div" style="border-color: green;">
        <h2>Exit Section</h2>
        <form action="/exit" method="post">
            Ticket / Subscription code: <input type="text" name="exitCode" placeholder="Please enter your code !">
            <input type="submit" value="Check">
        </form>
        <p>You entered this code: ${exit_code}</p>
    </div>
</div>
<div class="ext_div">
    <div class="int_div" style="border-color: magenta;">
        <h2>Buy Subscription Section</h2>
        <form action="/subscriptions" method="post">
            Choose period: <input type="text" id="start_date" name="startDate" placeholder="Choose start date">
            <input type="text" id="end_date" name="endDate" placeholder="Choose end date">
            <input type="submit" value="Submit">
        </form>
        <span>Subscription details: <b>start date: </b> ${sub_start_date}, <b>end date: </b> ${sub_end_date}</span>
    </div>
</div>
<div class="ext_div">
    <div class="int_div" style="border-color: rebeccapurple;">
        <h2>Admin Section</h2>
        <form action="/login" method="post">
            Choose period: <input type="text" name="username" placeholder="User Name">
            <input type="password" name="password" placeholder="Password">
            <input type="submit" value="Submit">
        </form>
        <span>Subscription details: <b>Username: </b> ${user_name}, <b>Password: </b> ${password}</span>
    </div>
</div>

<%--<a href="${pageContext.request.contextPath}/personList">Person List</a>--%>

</body>

</html>