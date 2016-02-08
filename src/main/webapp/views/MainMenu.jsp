<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/functions.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/jquery-1.11.2.min.js" charset="utf-8"></script>
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