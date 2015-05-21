package global;

import lombok.Getter;
import models.Location;
import models.WeatherInfo;
import org.joda.time.DateTime;
import play.libs.Json;
import repositories.WeatherRepository;
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
            for (Location location : Location.find.all()) {
                // Add new entry to database
                createLogForLocation(location);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Error!
        }
    }
    public static void createLogForLocation(Location location){
        new WeatherInfo(
                location.getId(),
                new DateTime(),
                WeatherRepository.getWeather(location)
        ).save();
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
    public static String getLocationLogs(Long location) {
        return getLocationLogs(Location.find.byId(location));
    }
}
