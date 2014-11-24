package controllers;

import java.util.concurrent.TimeUnit;

import play.*;
import play.mvc.*;
import play.libs.ws.*;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import views.html.*;

public class Application extends Controller {
	
	// TODO read from application settings/configuration
	private final static String REMOTE_COMMODITIES_SERVICE_URL = "http://localhost:9000/Commodities";
	private final static String COMMODITY_NAME_PARAMETER = "commodityName";

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result getRemoteCommodities() {
    	//Used for tests:
    	//WSRequestHolder holder = WS.url("http://api.geonames.org/postalCodeLookupJSON?postalcode=6600&country=AT&username=demo");
    	WSRequestHolder holder = WS.url(REMOTE_COMMODITIES_SERVICE_URL);
    	
    	Promise<WSResponse> responsePromise = holder.get();
    	WSResponse rsp = responsePromise.get(60, TimeUnit.SECONDS);
    	
    	// Later we will return response as is.
    	return ok("{\"Commodities\":" + rsp.getBody() + "}");
    	
    }
    
    public static Result getRemoteCommodity(String commodityName) {
    	//WSRequestHolder holder = WS.url("http://localhost:9000/Commodities");
    	WSRequestHolder holder = WS.url(REMOTE_COMMODITIES_SERVICE_URL);
    	WSRequestHolder complexHolder = holder.setQueryParameter(COMMODITY_NAME_PARAMETER, commodityName);
    	Promise<WSResponse> responsePromise = complexHolder.get();
    	WSResponse rsp = responsePromise.get(60, TimeUnit.SECONDS);

    	return ok(Json.toJson(rsp.getBody()));
    }

}
