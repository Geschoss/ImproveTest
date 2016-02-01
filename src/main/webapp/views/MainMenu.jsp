<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript">

        function createUser() {
            if (validate()) {
                $('#name').css('background-color', '');
                $('#category').css('background-color', '');
                $('#priceTo').css('background-color', '');
                $('#priceFrom').css('background-color', '');
                var category = $('#category').val();
                var name = $('#name').val();
                var priceTo = $('#priceTo').val();
                var priceFrom = $('#priceFrom').val();
                $.ajax({
                    url: 'find',
                    method: 'post',
                    data: {category: category, name: name, priceTo: priceTo, priceFrom: priceFrom},
                    complete: function (data) {
                        var json = data.responseJSON;
                        var productName = "";
                        var categoryName = "";
                        var productPrice = "";
/*
                        if (json.length > 0) {
*/
                            var table = "<table class=\"bordered\">";
                            table += "<thead>"
                                    + "<tr >"
                                    + "<th > Категория</th>"
                                    + "<th > Наименование</th>"
                                    + "<th > Цена</td>"
                                    + "</tr>"
                                    + "</thead>";
                            $.each(json, function (key, value) {
                                $.each(value, function (key, value) {
                                    if (key != "id") {
                                        if (key == "name") {
                                            productName = value
                                        }
                                        if (key == "price") {
                                            productPrice = value
                                        }
                                        if (key == "category") {
                                            $.each(value, function (key, value) {
                                                if (key == "name") {
                                                    categoryName = value
                                                }
                                            });
                                        }
                                    }

                                });
                                table += "<tr>" + "<td>" + categoryName + "</td>"
                                        + "<td>" + productName + "</td>"
                                        + "<td>" + productPrice.toFixed(2) + "</td>"
                                        + "</tr>";
                            });
                            table += "</table>";
                            $('#products').html(table);
/*
                        }
*/
                    }
                });
            }
        }

        function validate() {
            var result = true;
            var AlertType = 0;
            if (($('#name').val() == '') && ($('#category').val() == '') && ($('#priceTo').val() == '') && ($('#priceFrom').val() == '')) {
                $('#name').css('background-color', 'FCCAE2');
                $('#category').css('background-color', 'FCCAE2');
                $('#priceTo').css('background-color', 'FCCAE2');
                $('#priceFrom').css('background-color', 'FCCAE2');
                result = false;
                AlertType = 1;
            }
            if (isNaN(($('#priceTo').val())) || $('#priceTo').val() < 0) {
                $('#priceTo').css('background-color', 'FCCAE2');
                result = false;
                AlertType = 2;
            }
            if (isNaN(($('#priceFrom').val())) || $('#priceFrom').val() < 0) {
                $('#priceFrom').css('background-color', 'FCCAE2');
                result = false;
                AlertType = 2;
            }
            if(AlertType == 1){
                alert("Заполните хотя бы одно поле!");
            }else if((AlertType == 2)) {
                alert("Не правильный формат числа");
            }
            return result;
        }


    </script>
</head>
<body>

<h1>Прайс-лист</h1>
<br>
<br>
<table class=\"bordered\">
    <thead>
    <tr>
        <td align="center ">Категория:</td>
        <td align="center ">Наименование:</td>
        <td align="center ">Цена от:</td>
        <td align="center ">Цена до:</td>

    </tr>
    </thead>

    <tr>
        <td>
            <input type="text" class="myInputText" name="category" value="${category}" style='text-align: Right' id="category">
        </td>
        <td>
            <input type="text" class="myInputText" name="name" value="${name}" style='text-align: Right' id="name">
        </td>
        <td>
            <input type="text" class="myInputText" name="priceFrom" value="${parPriceFrom}" style='text-align: Right' id="priceFrom">
        </td>
        <td>
            <input type="text" class="myInputText" name="priceTo" value="${parPriceTo}" style='text-align: Right' id="priceTo">
        </td>
        <td><input type="button" class="myButton" value="Найти" style='text-align: Right'
                   onclick="return createUser();"/>
        </td>
    </tr>

</table>
<br>
<br>

<div id="products" class="center">

</div>
</body>
</html>