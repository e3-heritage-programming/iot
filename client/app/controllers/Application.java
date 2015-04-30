package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import play.libs.Json;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

public class Application extends Controller {

    private final static String REMOTE_REST_SERVICE;
    private final static String REMOTE_COMMODITIES_SERVICE_URL;
    private final static String REMOTE_LOCATION_SERVICE_URL;
    private final static String REMOTE_WEATHER_SERVICE_URL;

    static {
        Config conf = ConfigFactory.load();
        REMOTE_REST_SERVICE = conf.getString("application.remote");

        REMOTE_COMMODITIES_SERVICE_URL = REMOTE_REST_SERVICE + "/Commodities";
        REMOTE_LOCATION_SERVICE_URL = REMOTE_REST_SERVICE + "/Locations";
        REMOTE_WEATHER_SERVICE_URL = REMOTE_REST_SERVICE + "/Weather";
    }

    public static Result getWeatherByLatLong(String latitude, String longitude) {
        return ok(getBody(REMOTE_WEATHER_SERVICE_URL +
                "/Pos?latitude=" + latitude + "&longitude=" + longitude));
    }

    public static Result getWeatherById(int id) {
        return ok(getBody(REMOTE_WEATHER_SERVICE_URL + "/Id?id=" + id));
    }

    /**
     * Query an external webApplication and display the result data in your web Application.
     */
    public static Result consumeExternalWebserviceData() {
        return ok(Json.parse(getBody(REMOTE_COMMODITIES_SERVICE_URL)));
    }

    /**
     * Query an external webApplication and apply template and display data in readable format.
     */
    public static Result consumeExternalWebserviceRender() {
        Map<Integer, String> commodities = new HashMap<>();

        JsonNode json = Json.parse(getBody(REMOTE_COMMODITIES_SERVICE_URL));
        for (JsonNode commodityNode : json.get("Commodities")) {
            commodities.put(Integer.parseInt(commodityNode.findValue("id").toString()),
                    commodityNode.findValue("commodityName").toString().replace("\"", ""));
        }

        return ok(views.html.Application.commodity.render(commodities));
    }


    /**
     * Displays default content of your web application.
     */
    public static Result index() {
        return ok("Welcome to the client front-end.");
    }

    /**
     * Get all commodities from remote web service.
     */
    public static Result getRemoteCommodities() {
        return ok(getBody(REMOTE_COMMODITIES_SERVICE_URL));
    }

    /**
     * Get a specific commodity information from remote web service.
     *
     * @param id Integer
     */
    public static Result getRemoteCommodity(int id) {
        return ok(getBody(REMOTE_COMMODITIES_SERVICE_URL + "/Id?id=" + id));
    }


    public static Result consumeWeatherRender() {
        Map<Integer, String> locations = new HashMap<>();

        JsonNode json = Json.parse(getBody(REMOTE_LOCATION_SERVICE_URL));
        JsonNode locationsParentNode = json.get("Locations");

        for (JsonNode locationNode : locationsParentNode) {
            locations.put(Integer.parseInt(locationNode.findValue("id").toString()),
                    (locationNode.findValue("locationName") + ", " + locationNode.findValue("countryName")).replace("\"", ""));
        }

        return ok(views.html.Application.weather.render(locations));
    }
}
