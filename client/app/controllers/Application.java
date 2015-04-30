package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import play.api.Play;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;


import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

    private final static String REMOTE_REST_SERVICE;
    private final static String REMOTE_COMMODITIES_SERVICE_URL;
    private final static String REMOTE_LOCATION_SERVICE_URL;

    static {
        Config conf = ConfigFactory.load();
        REMOTE_REST_SERVICE = conf.getString("application.remote");

        REMOTE_COMMODITIES_SERVICE_URL = REMOTE_REST_SERVICE + "/Commodities";
        REMOTE_LOCATION_SERVICE_URL = REMOTE_REST_SERVICE + "/Locations";
    }

    public static Result getWeatherByLatLong(String latitude, String longitude) {
        WSRequestHolder holder =
                WS.url(REMOTE_REST_SERVICE + "weather?latitude=" + latitude + "&longitude=" + longitude);
        WSResponse rsp = holder.get().get(60, TimeUnit.SECONDS);

        return ok(rsp.getBody());
    }

    public static Result getWeatherById(int id) {
        WSRequestHolder holder =
                WS.url(REMOTE_REST_SERVICE + "/weather/id?id=" + id);
        WSResponse rsp = holder.get().get(60, TimeUnit.SECONDS);

        return ok(rsp.getBody());
    }

    /**
     * Query an external webApplication and display the result data in your web Application.
     */
    public static Result consumeExternalWebserviceData() {
        WSRequestHolder holder = WS.url(REMOTE_COMMODITIES_SERVICE_URL);
        WSResponse rsp = holder.get().get(60, TimeUnit.SECONDS);

        JsonNode json = null;

        try {
            json = Json.parse(rsp.getBody());
        } catch (Exception e) {
            // could be of wrong response from server or no response at all (non 200 http request)
            // return empty list of commodities
        }

        return ok(json);
    }

    /**
     * Query an external webApplication and apply template and display data in readable format.
     */
    public static Result consumeExternalWebserviceRender() {
        WSRequestHolder holder = WS.url(REMOTE_COMMODITIES_SERVICE_URL);
        WSResponse rsp = holder.get().get(60, TimeUnit.SECONDS);

        Map<String, String> commodities = new HashMap<String, String>();

        try {
            JsonNode json = Json.parse(rsp.getBody());
            for (JsonNode commodityNode : json.get("Commodities")) {
                commodities.put(commodityNode.findValue("commodityName").toString().replace("\"", ""), commodityNode.findValue("rate").toString().replace("\"", ""));
            }
        } catch (Exception e) {
            // could be of wrong response from server or no response at all (non 200 http request)
            // return empty list of commodities
        }

        return ok(views.html.Application.index.render(commodities));
    }


    /**
     * Displays default content of your web application.
     */
    public static Result index() {
        return ok("Welcome to my First Web Application!!");
    }

    /**
     * Get all commodities from remote web service.
     */
    public static Result getRemoteCommodities() {
        //Used for tests:
        //WSRequestHolder holder = WS.url("http://api.geonames.org/postalCodeLookupJSON?postalcode=6600&country=AT&username=demo");
        WSRequestHolder holder = WS.url(REMOTE_COMMODITIES_SERVICE_URL);

        WSResponse rsp = holder.get().get(60, TimeUnit.SECONDS);

        // Later we will return response as is.
        // return ok("{\"Commodities\":" + rsp.getBody() + "}");

        return ok(rsp.getBody());
    }

    /**
     * Get a specific commodity information from remote web service.
     *
     * @param commodityName String commodityName
     * @return
     */
    public static Result getRemoteCommodity(String commodityName) {
        WSRequestHolder holder = WS.url(REMOTE_COMMODITIES_SERVICE_URL + "/" + commodityName);
        WSResponse rsp = holder.get().get(60, TimeUnit.SECONDS);

        return ok(rsp.getBody());
    }


    public static Result consumeWeatherRender() {
        WSRequestHolder holder = WS.url(REMOTE_LOCATION_SERVICE_URL);
        WSResponse rsp = holder.get().get(60, TimeUnit.SECONDS);

        Map<Integer, String> locations = new HashMap<>();

        try {
            JsonNode json = Json.parse(rsp.getBody());
            JsonNode locationsParentNode = json.get("Locations");

            for (JsonNode locationNode : locationsParentNode) {
                locations.put(Integer.parseInt(locationNode.findValue("id").toString()),
                        (locationNode.findValue("locationName") + ", " + locationNode.findValue("countryName")).replace("\"", ""));
            }
        } catch (Exception e) {
            // could be of wrong response from server or no response at all (non 200 http request)
            // will return empty list of locations
        }

        return ok(views.html.Application.weather.render(locations));
    }
}
