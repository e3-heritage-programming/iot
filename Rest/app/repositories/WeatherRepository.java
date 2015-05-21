package repositories;

import global.Helpers;
import models.Location;

public class WeatherRepository {
    final public static String WEATHER_API = "http://api.openweathermap.org/data/2.5/weather";
    final public static String UNITS = "metric";
    final public static String APIKEY = "0daae6acdb8749761a85bd6f709721da";

    /**
     * Get weather of location
     *
     * @param location Location name
     * @return weather
     */
    public static String getWeather(String location) {
        // Check if location with id was found
        if (location == null)
            return Helpers.jsonError("Location not found");

        // Return json reply from openweathermap
        try {
            return Helpers.getBody(WEATHER_API + "?q=" +
                    location + "&units=" + UNITS + "&APPID=" + APIKEY);
        } catch (Exception e) {
            e.printStackTrace();
            return Helpers.jsonError("Error");
        }
    }

    /**
     * Get weather of location
     *
     * @param location Location id
     * @return weather
     */
    public static String getWeather(Long location) {
        return getWeather(Location.find.byId(location));
    }

    public static String getWeatherByLatLong(String longitude, String latitude) {
        // Return json reply from openweathermap
        return Helpers.getBody(WEATHER_API + "?lat="
                + latitude + "&lon=" + longitude + "&units=" + UNITS);
    }

    /**
     * Get weather of location
     *
     * @param location Location
     * @return weather
     */
    public static String getWeather(Location location) {
        return getWeather(location.getLocationGlued());
    }
}