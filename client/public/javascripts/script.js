$(function() {
      $("#commodity-dropdown").change(function() {
    	    	$("#commodity-table-body").empty();   	     	    
    	    	$.get(  "/RemoteCommodities/"+$(this).val(), function( data ) {
	    		    //JSON FORMAT: "{\"commodity\":[{\"commodityName\":\"wheat\",\"rate\":\"5\",\"unit\":\"kWh\"}]}"
		    	    var obj = jQuery.parseJSON(data);
		    	    $.each(obj.commodity,function(i,v){ 
		    	    	$("#commodity-table-body").append("<tr><td>"+v.commodityName+"</td><td>"+v.rate+"</td><td>"+v.unit+"</td></tr>");
		    	    });
    	    	});    	   
      });
});