var autocomplete;
$(function () {
    var alert = document.getElementById("alert alert-danger");

    $("#commodity-dropdown").change(function () {
        $("#commodity-table-body").empty();
        var commodityId = $(this).val();
        var commodityName = $(this).find("option:selected").text();

        var longitude = -1;
        var latitude = -1;

        function getLocation() {
            if (navigator.geolocation)
                navigator.geolocation.getCurrentPosition(function (position) {
                    latitude = position.coords.latitude;
                    longitude = position.coords.longitude;
                });
            else
                alert("Geolocation is not supported by this browser.");
        }

        var userTemperature = -1000;

        $.get("/Commodities/Id?id=" + commodityId, function (data) {
            var obj = jQuery.parseJSON(data);
            $.each(obj.commodity, function (key, value) {

                var commodityUpperThresholdTemp = parseInt(value.upperTempThreshold);
                var commodityLowerThresholdTemp = parseInt(value.lowerTempThreshold);

                var temperatureResponse = userTemperature > commodityUpperThresholdTemp ||
                    userTemperature < commodityLowerThresholdTemp;
                $("#commodity-table-body").append(getRow(
                    getCell(value.commodityName) +
                    getCell(value.rate) +
                    getCell(value.unit) +
                    getCell(temperatureResponse ? "Yes" : "No")
                ));


            });
        });
    });

    $("#location-dropdown").change(function () {
        updateLocation();
    });

    $("#location-refresh").click(function () {
        updateLocation();
    });


    function findComponent(place, type) {
        var component = _.find(place.address_components, function (component) {
            return _.include(component.types, type);
        });
        return component && component.short_name;
    }

    autocomplete = new google.maps.places.Autocomplete($("#locname")[0], {
            types: ['(cities)']
        }
    );
    google.maps.event.addListener(autocomplete, 'place_changed', function () {
        updateName();
    });

    $('#addloc').submit(function (e) {
        var form = this;
        e.preventDefault();
        setTimeout(function () {
            updateName();
            form.submit();
        }, 500);
    });

    function updateName() {
        var place = autocomplete.getPlace();
        $("#name").val(
            findComponent(place, 'administrative_area_level_3') || findComponent(place, 'locality') +
            ", " +
            findComponent(place, 'country')
        );
    }
});

function getRow(inside) {
    return "<tr>" + inside + "</tr>";
}
function getCell(inside) {
    return "<td>" + inside + "</td>";
}
function getTooltip(inside) {
    return getCell("<span class=\"show-tooltip\">" + inside + "</span>");
}


String.prototype.capitalize = function () {
    return this.charAt(0).toUpperCase() + this.slice(1);
};

function updateLocation() {
    $("#location-table-body").empty();
    var dropdown = $("#location-dropdown");
    var locationId = dropdown.val();
    var locationName = dropdown.find("option:selected").text();
    if (!locationId) {
        return;
    }

    $.get("/Weather/Id?id=" + locationId, function (data) {
        $.each(jQuery.parseJSON(data), function (key, value) {
            try {
                var weather = jQuery.parseJSON(value.data);
                $("#location-table-body").append(getRow(
                    getTooltip(moment(value.date.millis).fromNow()) + // Time
                    getCell(locationName) + // Location name
                    getCell(parseFloat(weather.main.temp).toFixed(1) + "°C") + // Temperature
                    getCell(weather.weather[0].description.capitalize()) + // Weather
                    getCell(weather.wind.speed + " km/h") // Wind
                ));
                $(".show-tooltip").tooltip({
                    title: moment(value.date.millis).format("MMMM Do YYYY, h:mm:ss a"),
                    placement: 'top'
                });
            } catch (e) {
            }
        });
    });
}