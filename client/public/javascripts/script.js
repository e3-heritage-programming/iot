$(function () {
    var alert = document.getElementById("alert alert-danger");

    $("#commodity-dropdown").change(function () {
        $("#commodity-table-body").empty();

        var longitude = -1;
        var latitude = -1;

        function getLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    latitude = position.coords.latitude;
                    longitude = position.coords.longitude;
                });
            } else {
                alert("Geolocation is not supported by this browser.");
            }
        }

        var userTemperature = -1000;

        $.get("/RemoteCommodities/" + $(this).val(), function (data) {
            var obj = jQuery.parseJSON(data);
            $.each(obj.commodity, function (i, v) {
                var commodityUpperThresholdTemp = parseInt(v.upperTempThreshold);
                var commodityLowerThresholdTemp = parseInt(v.lowerTempThreshold);

                var temperatureResponse = userTemperature > commodityUpperThresholdTemp ||
                    userTemperature < commodityLowerThresholdTemp;
                $("#commodity-table-body").append(getRow(
                    getCell(v.commodityName) +
                    getCell(v.rate) +
                    getCell(v.unit) +
                    getCell(temperatureResponse ? "Yes" : "No")
                ));
            });
        });
    });

    $("#location-dropdown").change(function () {
        $("#location-table-body").empty();
        var locationId = $(this).val();
        var locationName = $(this).find("option:selected").text();

        $.get("/weather/id?id=" + locationId, function (data) {
            var weather = jQuery.parseJSON(data);
            console.log(weather);
            $("#location-table-body").append(getRow(
                getCell(locationName) +
                getCell(weather.main.temp + "°C") +
                getCell(weather.weather[0].description.capitalize()) +
                getCell(weather.wind.speed + " km/h")
            ));
        });
    });
});

function getRow(inside) {
    return "<tr>" + inside + "</tr>";
}
function getCell(inside) {
    return "<td>" + inside + "</td>";
}

String.prototype.capitalize = function () {
    return this.charAt(0).toUpperCase() + this.slice(1);
}
