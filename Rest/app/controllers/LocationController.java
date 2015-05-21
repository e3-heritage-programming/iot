package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import global.Helpers;
import global.WeatherLogger;
import models.Location;
import play.libs.Json;
import play.mvc.Result;
import repositories.WeatherRepository;

import java.util.List;

public class LocationController extends BaseController {
    /**
     * Add location to database and add a log
     *
     * @param name Location name
     */
    public static Result getAddLocation(String name) {
        try {
            // Get current weather
            String weather = WeatherRepository.getWeather(name);

            // Parse as json
            JsonNode json = Json.parse(weather);

            // Make variables
            String locationName = json.get("name").asText();
            String countryName = Helpers.getCountryNameByCode(json.get("sys").get("country").asText());

            // Check if already exist
            if (Location.find
                    .where()
                    .eq("location_name", locationName)
                    .eq("country_name", countryName)
                    .findRowCount() >= 1) {
                return ok(Helpers.jsonError("Already tracking."));
            }

            // Create a save
            Location location = new Location(locationName, countryName);
            location.save();

            // Make initial log for starter data (so it's not empty)
            WeatherLogger.createLogForLocation(location);

            return ok("ok");
        } catch (Exception ex) {
            // Will error if location is not found
            return ok(Helpers.jsonError("Location not found (or other error)"));
        }
    }

    /**
     * @return Array of location names.
     */
    public static Result getLocations() {
        // Format locations as json
        List<Location> locations = Location.find.all();
        return ok("{\"Locations\":" + Json.toJson(locations) + "}");
    }
}
