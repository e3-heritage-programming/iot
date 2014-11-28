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
    private final static String REMOTE_COMMODITIES_SERVICE_URL = "http://localhost:9000/Commodities";
    private final static String COMMODITY_NAME_PARAMETER = "commodityName";

    public static Result staticContent(){
        WSRequestHolder holder = WS.url(REMOTE_COMMODITIES_SERVICE_URL);
        Promise<WSResponse> responsePromise = holder.get();
        WSResponse rsp = responsePromise.get(60, TimeUnit.SECONDS);

        try {
            //TODO DURING INTEGRATION: should be Json.parse(rsp.getBody()); instead of passing string of json test data
            JsonNode json = Json.parse("{\"Commodities\":[{\"commodityName\":\"wheat\",\"rate\":\"5$\",\"unit\":\"kWh\"},{\"commodityName\":\"rice\",\"rate\":\"5$\",\"unit\":\"kWh\"}]}");
        }catch(Exception e){
            //TODO Error Handle if Rest Server does not return json.
        }

        Map commodities = new HashMap<String,String>();
        JsonNode commodityParentNode = json.get("Commodities");
        for(int jsonCounter=0; jsonCounter<commodityParentNode.size(); jsonCounter++){
            JsonNode commodityNode = commodityParentNode.get(jsonCounter);
            commodities.put(commodityNode.findValue("commodityName").toString().replace("\"",""), commodityNode.findValue("rate").toString().replace("\"",""));
        }
        return ok(views.html.Application.index.render(commodities));
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
