package controllers;

import global.WeatherLogger;
import objects.Locations;
import play.libs.Json;
import play.mvc.Result;
import repositories.WeatherRepository;

public class WeatherController extends BaseController {

    /**
     * Get current weather of lat/long
     * @param longitude longitude
     * @param latitude latitude
     * @return weather
     */
    public static Result getWeatherByLatLong(String longitude, String latitude) {
        return ok(WeatherRepository.getWeatherByLatLong(longitude, latitude));
    }

    /**
     * Get current weather of a location
     * @param id Location id
     * @return weather
     */
    public static Result getWeatherById(int id) {
        return ok(WeatherRepository.getWeather(id));
    }

    /**
     * Get weather history of a location
     *
     * @param id Location id
     * @return logs
     */
    public static Result getWeatherLogs(int id) {
        return ok(WeatherLogger.getLocationLogs(id));
    }

    /**
     * @return Array of location names.
     */
    public static Result getLocations() {
        return ok("{\"Locations\":" + Json.toJson(Locations.getAllLocations()) + "}");
    }
}