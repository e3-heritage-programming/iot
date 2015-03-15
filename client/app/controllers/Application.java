package controllers;

import java.util.concurrent.TimeUnit;

import play.*;
import play.mvc.*;
import play.libs.ws.*;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import views.html.*;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class Application extends Controller {

    // TODO read from application settings/configuration
	private final static String REMOTE_REST_SERVICE = "http://localhost:9005/";
    private final static String REMOTE_COMMODITIES_SERVICE_URL = "http://localhost:9005/Commodities";
    private final static String COMMODITY_NAME_PARAMETER = "commodityName";

    public static Result getWeather(String longitude, String latitude){
        WSRequestHolder holder = WS.url(REMOTE_REST_SERVICE+"weather?longitude="+longitude+"&latitude="+latitude);
        Promise<WSResponse> responsePromise = holder.get();
        WSResponse rsp = responsePromise.get(60, TimeUnit.SECONDS);
        System.out.println(rsp.getBody().toString());
        return ok(rsp.getBody().toString());
    }
    
    public static Result staticContent(){
        WSRequestHolder holder = WS.url(REMOTE_COMMODITIES_SERVICE_URL);
        Promise<WSResponse> responsePromise = holder.get();
        WSResponse rsp = responsePromise.get(60, TimeUnit.SECONDS);

        JsonNode json = null;
        Map<String,String> commodities = new HashMap<String,String>();
        
        try{
        	json = Json.parse(rsp.getBody());
        }catch(Exception e){//could be of wrong response from server or no response at all (non 200 http request)
        	//return empty list of commodities
        	return ok(views.html.Application.index.render(commodities));
        }
        
        //JSON FORMAT : "{\"Commodities\":[{\"commodityName\":\"wheat\",\"rate\":\"5$\",\"unit\":\"kWh\"},{\"commodityName\":\"rice\",\"rate\":\"5$\",\"unit\":\"kWh\"}]}"
        JsonNode commodityParentNode = json.get("Commodities");
        
        for(int jsonCounter=0; jsonCounter<commodityParentNode.size(); jsonCounter++){
            JsonNode commodityNode = commodityParentNode.get(jsonCounter);
            commodities.put(commodityNode.findValue("commodityName").toString().replace("\"",""), commodityNode.findValue("rate").toString().replace("\"",""));
        }
        
        return ok(views.html.Application.index.render(commodities));
    }
    
    
    public static Result index(){
    	return ok("Welcome to my First Web Application!!");
    }

    public static Result getRemoteCommodities() {
        //Used for tests:
        //WSRequestHolder holder = WS.url("http://api.geonames.org/postalCodeLookupJSON?postalcode=6600&country=AT&username=demo");
        WSRequestHolder holder = WS.url(REMOTE_COMMODITIES_SERVICE_URL);

        Promise<WSResponse> responsePromise = holder.get();
        WSResponse rsp = responsePromise.get(60, TimeUnit.SECONDS);

        // Later we will return response as is.
       // return ok("{\"Commodities\":" + rsp.getBody() + "}");
        return ok(rsp.getBody());
    }

    public static Result getRemoteCommodity(String commodityName) {
        WSRequestHolder holder = WS.url(REMOTE_COMMODITIES_SERVICE_URL+"/"+commodityName);
        Promise<WSResponse> responsePromise = holder.get();
        WSResponse rsp = responsePromise.get(60, TimeUnit.SECONDS);
        JsonNode json = null;
        
/*        try{
        	json = Json.toJson(rsp.getBody());
        }catch(Exception e){
        	//TODO : return empty json
        }*/
 
        return ok(rsp.getBody());
    }
}
