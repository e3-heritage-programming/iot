

import com.sample.Commodities;
import com.sample.Commodity;
import com.sample.Location;
import com.sample.Locations;
import play.Application;
import play.GlobalSettings;
import play.Logger;

import java.util.ArrayList;
import java.util.List;

public class Global extends GlobalSettings {


    public void onStart(Application app) {
        Logger.info("Application about to load default resource");

        List<Commodity> commodities = new ArrayList<>();

        commodities.add(new Commodity("Appliances", "5$/kWh", "kWh", 25.0, -15.0));
        commodities.add(new Commodity("Water", "1$/100L", "Litres", 35, -25.0));
        commodities.add(new Commodity("Heating", "3$/kWh", "kWh", 10.0, -35.0));
        commodities.add( new Commodity("Air-Condition", "4$/kWh", "kWh", 35.0, 17.0));

        Commodities.setCommodities(commodities);


        List<Location> locations = new ArrayList<>();

        locations.add(new Location("Montreal", "Canada"));
        locations.add(new Location("Toronto", "Canada"));

        Locations.setLocations(locations);
    }
}
