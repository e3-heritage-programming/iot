package controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import play.libs.Json;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import com.fasterxml.jackson.databind.JsonNode;
import com.sample.Commodities;
import com.sample.Commodity;
import com.sample.Locations;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    /**
     * @param commodityName	String 
     * @return	Details about the specific commodity request if present. If not commodity not found.
     */
    public static Result getCommodity(String commodityName){
    	Commodity value = Commodities.getCommodity(commodityName);
    	
    	if (value != null){
    	String tempJson = "{\"commodity\":["+Json.toJson(value) + "]}";
    		return ok(tempJson);//Json.toJson(value));
    	}else{
    		return ok("Commodity not found");
    	}
    }
    
    public static Result getWeather(String longitude, String latitude){

        WSRequestHolder holder = WS.url("http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude);

        Promise<WSResponse> responsePromise = holder.get();
        WSResponse rsp = responsePromise.get(60, TimeUnit.SECONDS);
        
        JsonNode json = null;
        
        try{
        	json = Json.parse(rsp.getBody());
        }catch(Exception e){//could be of wrong response from server or no response at all (non 200 http request)
        	//return empty list of commodities
        	
        }
        
        JsonNode mainParentNode = json.get("main");
        
        double temperatureKelvin = Double.parseDouble(mainParentNode.findValue("temp").toString());
        double temperatureCelcius = temperatureKelvin - 273.15;
                
        return ok(Double.toString(temperatureCelcius));
    }
    
    
    /**
     * @return  Array of commodity names.
     */
    public static Result getCommodities(){
    	return ok("{\"Commodities\":" + Json.toJson(Commodities.getAllCommodities() ) + "}");
    	
    }
    
    /**
     * @return  Array of location names.
     */
    public static Result getLocations(){
    	return ok("{\"Locations\":" + Json.toJson(Locations.getAllLocations() ) + "}");
    }

}
