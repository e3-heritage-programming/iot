

import java.util.HashMap;

import com.sample.Commodities;
import com.sample.Commodity;
import com.sample.Location;
import com.sample.Locations;

import play.*;

public class Global extends GlobalSettings {

	
	public void onStart(Application app) {		
		Logger.info("Application about to load default resource");
		HashMap<String, Commodity> data= new HashMap<String, Commodity>(5);
		Commodity appliances = new Commodity("Appliances", "5$/kWh", "kWh", 25.0, -15.0);
		Commodity water = new Commodity("Water", "1$/100L", "Litres", 35, -25.0);
		Commodity heating = new Commodity("Heating", "3$/kWh", "kWh", 10.0, -35.0);
		Commodity airCondition = new Commodity("Air-Condition", "4$/kWh", "kWh", 35.0, 17.0);

		data.put(appliances.getCommodityName(), appliances);
		data.put(water.getCommodityName(), water);
		data.put(heating.getCommodityName(), heating);
		data.put(airCondition.getCommodityName(), airCondition);
		Commodities.setCommodities(data);
		
		
		
		HashMap<String, Location> locationData = new HashMap<String, Location>(5);
		Location location1 = new Location("Montreal", "Canada");
		Location location2 = new Location("Toronto", "Canada");
		
		locationData.put(location1.getLocationName(), location1);
		locationData.put(location2.getLocationName(), location2);	
		Locations.setCommodities(locationData);
		
	}
}
