package com.sample;

import java.util.Collection;
import java.util.HashMap;

public class Commodities {
	
	private static HashMap<String, Commodity> commodities ;
	
	public static void setCommodities(HashMap<String, Commodity> data){
		commodities = data;
		
	}
	
	public static Commodity getCommodity(String commodityName){
		return commodities.get(commodityName);
	}

	
	/**
	 * @return  Set<String> all commoditiy names.
	 */
	public static Collection<Commodity> getAllCommodities(){
		return commodities.values();
	}
}
