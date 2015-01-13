package com.sample;

public class Commodity {

	Commodity(){
	}
	
	public Commodity(String commodityName, String rate, String unit, double upperTempThreshold, double lowerTempThreshold){
		this.commodityName = commodityName;
		this.rate = rate;
		this.unit = unit;
		this.upperTempThreshold = upperTempThreshold;
		this.lowerTempThreshold = lowerTempThreshold;
	}
	
	private String commodityName;
	private String rate;
	private String unit;
	private double upperTempThreshold; //commodity recommended to use under this temp. Otherwise more pricier. 
	private double lowerTempThreshold; //commodity recommended to use over this temp. Otherwise more pricier.
	private double increaseInCost; //possible price increase if commodity not in norm temp range


	public double getIncreaseInCost() {
		return increaseInCost;
	}

	public void setIncreaseInCost(double increaseInCost) {
		this.increaseInCost = increaseInCost;
	}
	
	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getUpperTempThreshold() {
		return upperTempThreshold;
	}

	public void setUpperTempThreshold(double upperTempThreshold) {
		this.upperTempThreshold = upperTempThreshold;
	}

	public double getLowerTempThreshold() {
		return lowerTempThreshold;
	}

	public void setLowerTempThreshold(double lowerTempThreshold) {
		this.lowerTempThreshold = lowerTempThreshold;
	}
}
