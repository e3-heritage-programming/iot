

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sample.Commodities;
import com.sample.Commodity;
import com.sample.Location;
import com.sample.Locations;

import play.*;

public class Global extends GlobalSettings {


    public void onStart(Application app) {
        Logger.info("Application about to load default resource");
        HashMap<String, Commodity> data = new HashMap<String, Commodity>(5);
        Commodity appliances = new Commodity("Appliances", "5$/kWh", "kWh", 25.0, -15.0);
        Commodity water = new Commodity("Water", "1$/100L", "Litres", 35, -25.0);
        Commodity heating = new Commodity("Heating", "3$/kWh", "kWh", 10.0, -35.0);
        Commodity airCondition = new Commodity("Air-Condition", "4$/kWh", "kWh", 35.0, 17.0);

        data.put(appliances.getCommodityName(), appliances);
        data.put(water.getCommodityName(), water);
        data.put(heating.getCommodityName(), heating);
        data.put(airCondition.getCommodityName(), airCondition);
        Commodities.setCommodities(data);


        List<Location> locations = new ArrayList<>();

        locations.add(new Location("Montreal", "Canada"));
        locations.add(new Location("Toronto", "Canada"));

        Locations.setLocations(locations);
    }
}
