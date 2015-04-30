$(function () {
    $("#commodity-dropdown").change(function () {
        $("#commodity-table-body").empty();

        var x = document.getElementById("alert alert-danger");
        var longitude = -1;
        var latitude = -1;

        function getLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(showPosition);
            } else {
                alert("Geolocation is not supported by this browser.");
            }
        }

        function showPosition(position) {
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
        }

        var temperaturegood = false;
        var temperatureResponseString = "No";
        var userTemperature = -1000;

        /*    	    	$.get("/weather?longitude="+longitude+"&latitude="+latitude, function( data ) {
         alert(userTemperature);
         userTemperature = parseInt(data);

         if(userTemperature > -5){
         temperaturegood = true;
         temperatureResponseString = "Yes";
         }
         });    	 */

        $.get("/RemoteCommodities/" + $(this).val(), function (data) {
            //JSON FORMAT: "{\"commodity\":[{\"commodityName\":\"wheat\",\"rate\":\"5\",\"unit\":\"kWh\"}]}"
            var obj = jQuery.parseJSON(data);
            $.each(obj.commodity, function (i, v) {

                var commodityUpperThresholdTemp = parseInt(v.upperTempThreshold);
                var commodityLowerThresholdTemp = parseInt(v.lowerTempThreshold);

                if (userTemperature > commodityUpperThresholdTemp || userTemperature < commodityLowerThresholdTemp) {
                    $("#commodity-table-body").append("<tr><td>" + v.commodityName + "</td><td>" + v.rate + "</td><td>" + v.unit + "</td><td>" + "No" + "</td></tr>");
                }

                else {
                    $("#commodity-table-body").append("<tr><td>" + v.commodityName + "</td><td>" + v.rate + "</td><td>" + v.unit + "</td><td>" + "Yes" + "</td></tr>");
                }
            });
        });
    });

    $("#location-dropdown").change(function () {
        console.log('ebola');
        $("#location-table-body").empty();
        var id = $(this).val();
        var name = $(this).find("option:selected").text();

        var alert = document.getElementById("alert alert-danger");
        var longitude = -1;
        var latitude = -1;

        var userTemperature = -1000;

        $.get("/weather/id?id=" + id, function (data) {
            //var obj = jQuery.parseJSON(data);
            $("#location-table-body").append("<tr><td>" + name + "</td><td>" + data + "°C</td></tr>");
            //$("#location-table-body").append("<tr><td>" +data.text+ "</td><td>" + v.rate + "</td><td>" + v.unit + "</td><td>" + "Yes" + "</td></tr>");

        });
    });
});

