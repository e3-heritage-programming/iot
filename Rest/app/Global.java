

import java.util.HashMap;

import com.sample.Commodities;
import com.sample.Commodity;

import play.*;

public class Global extends GlobalSettings {

	
	public void onStart(Application app) {		
		Logger.info("Application about to load default resource");
		HashMap<String, Commodity> data= new HashMap<String, Commodity>(5);
		Commodity wheat = new Commodity("wheat", "5$", "kWh");
		Commodity rice = new Commodity("rice", "5$", "kWh");
		data.put(wheat.getCommodityName(), wheat);
		data.put(rice.getCommodityName(), rice);
		Commodities.setCommodities(data);
	}
}
