package global;

import models.WeatherInfo;
import objects.Location;
import objects.Locations;
import repositories.WeatherRepository;
import lombok.Getter;
import org.joda.time.DateTime;
import play.libs.Json;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

public class WeatherLogger {

    @Getter
    private static final FiniteDuration duration = Duration.create(30, TimeUnit.MINUTES);

    /**
     * Log all of the current weather's into the database.
     */
    public static void log() {
        try {
            for (Location location : Locations.getAllLocations()) {
                // Add new entry to database
                WeatherInfo weather = new WeatherInfo();
                weather.location = location.getId();
                weather.date = new DateTime();
                weather.data = WeatherRepository.getWeather(location);
                weather.save();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Error!
        }
    }

    /**
     * Gets the location's log history.
     *
     * @param location location
     * @return History
     */
    public static String getLocationLogs(Location location) {
        return Json.toJson(WeatherInfo.find
                .where().eq("location", location.getId()) // get for that location only
                .orderBy().desc("date") // show newest first
                .setMaxRows(50)
                .findList()).toString();
    }

    /**
     * Gets the location's log history.
     *
     * @param location location
     * @return History
     */
    public static String getLocationLogs(int location) {
        return getLocationLogs(Locations.getLocation(location));
    }
}
