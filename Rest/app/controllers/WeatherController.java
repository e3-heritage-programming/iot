package controllers;

import global.WeatherLogger;
import play.mvc.Result;
import repositories.WeatherRepository;

public class WeatherController extends BaseController {

    public static Result getWeatherByLatLong(String longitude, String latitude) {
        return ok(WeatherRepository.getWeatherByLatLong(longitude, latitude));
    }

    public static Result getWeatherById(int id) {
        return ok(WeatherRepository.getWeather(id));
    }

    public static Result getWeatherLogs(int id) {
        return ok(WeatherLogger.getLocationLogs(id));
    }
}
