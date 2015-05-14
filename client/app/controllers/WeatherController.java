package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

public class WeatherController extends Controller {
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

    public static Result getWeatherByLatLong(String latitude, String longitude) {
        return ok(getBody(REMOTE_WEATHER_SERVICE_URL +
                "/Pos?latitude=" + latitude + "&longitude=" + longitude));
    }

    public static Result getWeatherById(int id) {
        return ok(getBody(REMOTE_WEATHER_SERVICE_URL + "/Id?id=" + id));
    }

}
