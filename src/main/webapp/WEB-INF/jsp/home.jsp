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
    <div style="border: 2px solid red; padding: 20px 40px;">
        <h2>Access Section</h2>
        <form action="/access" method="post">
            Access code: <input type="text" name="accessCode" placeholder="Please enter the access code !">
            <input type="submit" value="Enter">
        </form>
        <p>${message2}</p>
    </div>
</div>


<%--<a href="${pageContext.request.contextPath}/personList">Person List</a>--%>

</body>

</html>