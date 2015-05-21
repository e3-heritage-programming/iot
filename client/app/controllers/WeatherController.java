package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WeatherController extends Controller {
    /**
     * @return Weather page
     */
    public static Result getWeather() {
        // Setup locations
        Map<Integer, String> locations = new HashMap<>();

        JsonNode json = Json.parse(getBody(REMOTE_LOCATION_SERVICE_URL));
        JsonNode locationsParentNode = json.get("Locations");

        for (JsonNode locationNode : locationsParentNode) {
            locations.put(Integer.parseInt(locationNode.findValue("id").toString()),
                    (locationNode.findValue("locationName") + ", " + locationNode.findValue("countryName")).replace("\"", ""));
        }

        // Render
        return ok(views.html.Weather.index.render(locations));
    }

    /**
     * @param latitude Latitude
     * @param longitude Longitude
     * @return Weather Json
     */
    public static Result getWeatherByLatLong(String latitude, String longitude) {
        return ok(getBody(REMOTE_WEATHER_SERVICE_URL +
                "/Pos?latitude=" + latitude + "&longitude=" + longitude));
    }

    /**
     * @param id Location id
     * @return Weather Logs Json
     */
    public static Result getWeatherById(Long id) {
        return ok(getBody(REMOTE_WEATHER_SERVICE_URL +
                "/Logs?id=" + id));
    }

    /**
     * @param name Location name to add
     * @return Redirect to weather page
     */
    public static Result getAddLocation(String name) {
        try {
            getBody(REMOTE_LOCATION_SERVICE_URL +
                    "/Add?name=" + URLEncoder.encode(name,  "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return redirect("/weather");
    }

}
