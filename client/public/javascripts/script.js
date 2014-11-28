$(function() {
      $("#commodity-dropdown").change(function() {
        //TODO - send GET request to server for data on this commodity
        $.getJSON( "/RemoteCommodities/"+$(this).val(), function( data ) {
            //TODO
        });
      });

      $("#btnRefreshData").click( function()
           {
               $.getJSON( "/Commodities", function( data ) {
                    $.each( data, function( key, val ) {
                        alert(val.rate);
                        $("#appliance_rate_table").append("<tr><td>" + val.name + "</td><td>" + val.rate + "</td></tr>");
                    });
                });//end json
           }
      );
});