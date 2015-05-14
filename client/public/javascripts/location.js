$(function() {
      $("#commodity-dropdown").change(function() {
    	    	$("#commodity-table-body").empty(); 
    	    	
    	    	var x = document.getElementById("alert alert-danger");
    	    	var longitude= -1;
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
    	    	var userTemperature = 280;
    	    	
/*    	    	$.get("/weather?longitude="+longitude+"&latitude="+latitude, function( data ) {
    	    		alert(userTemperature);
    	    		userTemperature = parseInt(data);
		    	    
		    	    if(userTemperature > -5){
		    	    	temperaturegood = true;
		    	    	temperatureResponseString = "Yes";
		    	    }
    	    	});    	 */
    	    	
    	    	$.get(  "/RemoteLocations/"+$(this).val(), function( data ) {
	    		    //JSON FORMAT: "{\"commodity\":[{\"commodityName\":\"wheat\",\"rate\":\"5\",\"unit\":\"kWh\"}]}"
		    	    var obj = jQuery.parseJSON(data);
		    	    if ($.isArray(obj)){
			    	    $.each(obj,function(i,v){ 
			    	    	
			    	    	var currentTemperature = parseFloat(v.temp);
			    	    //	var commodityLowerThresholdTemp = parseInt(v.temp.min);
			    	    	
			    	    	if(currentTemperature > userTemperature ){
				    	    	$("#commodity-table-body").append("<tr><td>"+v.name+"</td><td>"+ v.temp+"</td><td>Kelvin</td><td>"+"Too hot Switching on AC"+"</td></tr>");
			    	    	}
			    	    	else{
			    	    		$("#commodity-table-body").append("<tr><td>"+v.name+"</td><td>"+ v.temp+"</td><td>Kelvin</td><td>"+"Not Too hot Switching on Fan"+"</td></tr>");
			    	    	} 	
			    	    });
		    	    }else{
		    	    	
		    	    	var currentTemperature = parseInt(obj.main.temp);
			    	    //	var commodityLowerThresholdTemp = parseInt(v.temp.min);
			    	    	
			    	    	if(currentTemperature > userTemperature ){
				    	    	$("#commodity-table-body").append("<tr><td>"+obj.name+"</td><td>"+ obj.main.temp+"</td><td>Kelvin</td><td>"+"Too hot Switching on AC"+"</td></tr>");
			    	    	}
			    	    	else{
			    	    		$("#commodity-table-body").append("<tr><td>"+obj.name+"</td><td>"+ obj.main.temp+"</td><td>Kelvin</td><td>"+"Not Too hot Switching on Fan"+"</td></tr>");
			    	    	} 	
		    	    	
		    	    }
		    	    
		    	   
    	    	});    	   
      });
});