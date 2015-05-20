package repositories;

import global.Helpers;
import objects.Location;
import objects.Locations;

public class WeatherRepository {
    final public static String WEATHER_API = "http://api.openweathermap.org/data/2.5/weather";
    final public static String UNITS = "metric";

    /**
     * Get weather of location
     *
     * @param location Location
     * @return weather
     */
    public static String getWeather(Location location) {
        // Check if location with id was found
        if (location == null)
            return Helpers.jsonError("Location not found");

        // Return json reply from openweathermap
        return Helpers.getBody(WEATHER_API + "?q=" +
                location.getLocationName() + "," + location.getCountryName() + "&units=" + UNITS);
    }

    /**
     * Get weather of location
     *
     * @param location Location id
     * @return weather
     */
    public static String getWeather(int location) {
        return getWeather(Locations.getLocation(location));
    }

    public static String getWeatherByLatLong(String longitude, String latitude) {
        // Return json reply from openweathermap
        return Helpers.getBody(WEATHER_API + "?lat="
                + latitude + "&lon=" + longitude + "&units=" + UNITS);
    }
}