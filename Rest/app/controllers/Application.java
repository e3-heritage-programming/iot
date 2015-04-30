package controllers;

import com.sample.Commodities;
import com.sample.Commodity;
import com.sample.Location;
import com.sample.Locations;
import play.libs.Json;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index() {
        return ok("Welcome to the Rest server.");
    }

    /**
     * @param id Integer
     * @return Details about the specific commodity request if present. If not commodity not found.
     */
    public static Result getCommodity(int id) {
        Commodity commodity = Commodities.getCommodity(id);

        // Check if commodity was found
        if (commodity == null)
            return jsonError("Commodity not found");

        String json = "{\"commodity\":[" + Json.toJson(commodity) + "]}";
        return ok(json);
    }

    public static Result getWeatherByLatLong(String longitude, String latitude) {
        // Return json reply from openweathermap
        return ok(getBody("http://api.openweathermap.org/data/2.5/weather?lat="
                + latitude + "&lon=" + longitude + "&units=metric"));
    }

    public static Result getWeatherById(int id) {
        Location location = Locations.getLocation(id);

        // Check if location with id was found
        if (location == null)
            return jsonError("Location not found");

        // Return json reply from openweathermap
        return ok(getBody("http://api.openweathermap.org/data/2.5/weather?q=" +
                location.getLocationName() + "," + location.getCountryName() + "&units=metric"));
    }


    /**
     * @return Array of commodity names.
     */
    public static Result getCommodities() {
        return ok("{\"Commodities\":" + Json.toJson(Commodities.getAllCommodities()) + "}");
    }

    /**
     * @return Array of location names.
     */
    public static Result getLocations() {
        return ok("{\"Locations\":" + Json.toJson(Locations.getAllLocations()) + "}");
    }

}
