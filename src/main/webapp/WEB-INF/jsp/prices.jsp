<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-5 int_div rounded-lg" style="background-color: #006600">
            <h2>Price section</h2>
            <form class="form-inline" action="/prices" method="post">
                <label for="priceField" class="mr-sm-2">Price:</label>
                <input type="text" class="form-control form-control-sm mb-2 mr-sm-2" name="priceField" id="priceField"
                       placeholder="Enter price">
                <label for="typeField" class="mr-sm-2">Type:</label>
                <input type="text" class="form-control form-control-sm mb-2 mr-sm-2" name="typeField" id="typeField"
                       placeholder="Enter type(ticket or subscription)">
                <input type="submit" class="btn btn-info btn-sm mb-2" value="Submit">
            </form>
            <br>
            <span>${create_price_messages}</span>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>