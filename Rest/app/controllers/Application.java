package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import com.sample.Commodities;
import com.sample.Commodity;

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
    		return ok(Json.toJson(value));
    	}else{
    		return ok("Commodity not found");
    	}
    	
    }
    
    
    /**
     * @return  Array of commodity names.
     */
    public static Result getCommodities(){
    	return ok("{\"Commodities\":" + Json.toJson(Commodities.getAllCommodities() ) + "}");
    	
    }

}
