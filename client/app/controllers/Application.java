package controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

    // TODO read from application settings/configuration
	private final static String REMOTE_REST_SERVICE = "https://polar-scrubland-6861.herokuapp.com";
    private final static String REMOTE_COMMODITIES_SERVICE_URL = REMOTE_REST_SERVICE + "/Commodities";


    public static Result getWeather(String longitude, String latitude){
        WSRequestHolder holder = WS.url(REMOTE_REST_SERVICE+"weather?longitude="+longitude+"&latitude="+latitude);
        Promise<WSResponse> responsePromise = holder.get();
        WSResponse rsp = responsePromise.get(60, TimeUnit.SECONDS);
        System.out.println(rsp.getBody().toString());
        return ok(rsp.getBody().toString());
    }
    
    /**
     *  Query an external webApplication and display the result data in your web Application.
     */
    public static Result consumeExternalWebserviceData(){
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
        
        return ok(json);
    }
    
    /**
     *  Query an external webApplication and apply template and display data in readable format.
     */
    public static Result consumeExternalWebserviceRender(){
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
    
    
    /**
     *  Displays default content of your web application.
     */
    public static Result index(){
    	return ok("Welcome to my First Web Application!!");
    }

    /**
     * Get all commodities from remote web service.
     */
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

    /**
     * Get a specific commodity information from remote web service.
     * @param commodityName		String commodityName
     * @return
     */
    public static Result getRemoteCommodity(String commodityName) {
        WSRequestHolder holder = WS.url(REMOTE_COMMODITIES_SERVICE_URL+"/"+commodityName);
        Promise<WSResponse> responsePromise = holder.get();
        WSResponse rsp = responsePromise.get(60, TimeUnit.SECONDS);
        /*          JsonNode json = null;
        
      try{
        	json = Json.toJson(rsp.getBody());
        }catch(Exception e){
        	//TODO : return empty json
        }*/
 
        return ok(rsp.getBody());
    }
}
