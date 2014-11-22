package com.sample;

public class Commodity {

	Commodity(){
		
	}
	
	public Commodity(String commodityName, String rate, String unit){
		this.commodityName = commodityName;
		this.rate = rate;
		this.unit = unit;
		
	}
	
	
	private String commodityName;
	
	private  String rate;
	
	private String unit;
	
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


}
