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
    public static Result getAddLocation(String name) {
        try {
            String weather = WeatherRepository.getWeather(name);
            JsonNode json = Json.parse(weather);

            String locationName = json.get("name").asText();
            String countryName = Helpers.getCountryNameByCode(json.get("sys").get("country").asText());

           if(Location.find
                    .where()
                    .eq("location_name", locationName)
                    .eq("country_name", countryName)
                    .findRowCount() >= 1){
               return ok(Helpers.jsonError("Already tracking."));
           }

            Location location = new Location(locationName, countryName);
            location.save();

            WeatherLogger.createLogForLocation(location);
            return ok("ok");
        } catch (Exception ex) {
            return ok(Helpers.jsonError("Location not found (or other error)"));
        }
    }

    /**
     * @return Array of location names.
     */
    public static Result getLocations() {
        List<Location> locations = Location.find.all();
        return ok("{\"Locations\":" + Json.toJson(locations) + "}");
    }
}
