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
    if (AlertType == 1) {
        alert("Заполните хотя бы одно поле!");
    } else if ((AlertType == 2)) {
        alert("Не правильный формат числа");
    }
    return result;
}
