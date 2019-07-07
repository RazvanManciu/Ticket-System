<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<h1>Ticketing system</h1>
<h2>Welcome ${user}</h2>
<div>
    <div style="border: 5px solid red; padding: 20px 30px;">
        <h2>Access Section</h2>
        <form action="/access" method="post">
            Access code: <input type="text" name="accessCode" placeholder="Please enter the access code !">
            <input type="submit" value="Enter">
        </form>
        <p>${access_code}</p>
    </div>
</div>
<div>
    <div style="border: 5px solid blue; padding: 20px 30px;">
        <h2>Payment Section</h2>
        <form action="/payments" method="post">
            Ticket code: <input type="text" name="ticketCode" placeholder="Please enter the ticket code !">
            <input type="submit" value="Calculate amount">
        </form>
        <p>You must pay: ${ticket_amount} $</p>
    </div>
</div>
<div>
    <div style="border: 5px solid green; padding: 20px 30px;">
        <h2>Exit Section</h2>
        <form action="/exit" method="post">
            Ticket / Subscription code: <input type="text" name="exitCode" placeholder="Please enter your code !">
            <input type="submit" value="Check">
        </form>
        <p>You entered this code: ${exit_code}</p>
    </div>
</div>
<div>
    <div style="border: 5px solid magenta; padding: 20px 30px;">
        <h2>Buy Subscription Section</h2>
        <form action="/subscription" method="post">
            Choose dates: <input type="text" name="exitCode" placeholder="Please enter your code !">
            <input type="submit" value="Check">
        </form>
        <p>.. ${exit_code}</p>
    </div>
</div>

<%--<a href="${pageContext.request.contextPath}/personList">Person List</a>--%>

</body>

</html>