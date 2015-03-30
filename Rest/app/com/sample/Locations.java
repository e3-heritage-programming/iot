package com.sample;

import java.util.Collection;
import java.util.HashMap;

public class Locations {
	
	private static HashMap<String, Location> locations ;
	
	public static void setCommodities(HashMap<String, Location> data){
		locations = data;
		
	}
	
	public static Location getLocation(String locationName){
		return locations.get(locationName);
	}

	
	/**
	 * @return  Set<String> all commoditiy names.
	 */
	public static Collection<Location> getAllLocations(){
		return locations.values();
	}
}
