$(function() {
      $("#commodity-dropdown").change(function() {
    	    	$("#commodity-table-body").empty();
    	    	$.get("/RemoteLocations/"+$("#commodity-dropdown").val(), function( data ) {
	    		    //JSON FORMAT: "{\"commodity\":[{\"commodityName\":\"wheat\",\"rate\":\"5\",\"unit\":\"kWh\"}]}"
		    	    var obj = jQuery.parseJSON(data);
		    	    $("#commodity-table-body").append("<tr><td>"+obj.name+"</td><td>"+obj.main.temp+"°C</td><td>"+obj.weather[0].description+"</td><td>"+obj.sys.country+"</td></tr>");
    	    	});
      });
});
