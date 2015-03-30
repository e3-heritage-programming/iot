package com.sample;

public class Location {

	Location(){
	}
	
	public Location(String locationName, String countryName){
		this.locationName = locationName;
		this.countryName = countryName;
	}
	
	private String locationName;
	private String countryName;


	
	public String getLocationName	() {
		return locationName;
	}

	public void setLocationName(String commodityName) {
		this.locationName = commodityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String rate) {
		this.countryName = rate;
	}

}
