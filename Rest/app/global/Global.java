package global;

import global.WeatherLogger;
import objects.Commodities;
import objects.Commodity;
import objects.Location;
import objects.Locations;
import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import scala.concurrent.duration.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    public void onStart(Application app) {

        List<Commodity> commodities = new ArrayList<>();

        commodities.add(new Commodity("Appliances", "5$/kWh", "kWh", 25.0, -15.0));
        commodities.add(new Commodity("Water", "1$/100L", "Litres", 35, -25.0));
        commodities.add(new Commodity("Heating", "3$/kWh", "kWh", 10.0, -35.0));
        commodities.add(new Commodity("Air-Condition", "4$/kWh", "kWh", 35.0, 17.0));

        Commodities.setCommodities(commodities);


        List<Location> locations = new ArrayList<>();

        locations.add(new Location("Montreal", "Canada"));
        locations.add(new Location("Toronto", "Canada"));
        locations.add(new Location("Ste-Julie", "Canada"));

        Locations.setLocations(locations);

        tasks();
    }

    private void tasks() {
        Akka.system().scheduler().schedule(
                Duration.create(0, TimeUnit.SECONDS),
                WeatherLogger.getDuration(),
                (Runnable) WeatherLogger::log,
                Akka.system().dispatcher()
        );
    }
}
