package global;

import objects.Commodities;
import objects.Commodity;
import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    public void onStart(Application app) {

        // Setup commodities
        Commodities.addCommodity(new Commodity("Appliances", "5$/kWh", "kWh", 25.0, -15.0));
        Commodities.addCommodity(new Commodity("Water", "1$/100L", "Litres", 35, -25.0));
        Commodities.addCommodity(new Commodity("Heating", "3$/kWh", "kWh", 10.0, -35.0));
        Commodities.addCommodity(new Commodity("Air-Condition", "4$/kWh", "kWh", 35.0, 17.0));

        // Setup tasks
        tasks();
    }

    private void tasks() {
        // Schedule task
        Akka.system().scheduler().schedule(
                Duration.create(0, TimeUnit.SECONDS), // start right away
                WeatherLogger.getDuration(), // duration between each
                (Runnable) WeatherLogger::log, // action
                Akka.system().dispatcher()
        );
    }
}
