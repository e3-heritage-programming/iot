package objects;

import lombok.Data;

@Data
public class Commodity {

    private int id;
    private String commodityName;
    private String rate;
    private String unit;
    private double upperTempThreshold; //commodity recommended to use under this temp. Otherwise more pricier.
    private double lowerTempThreshold; //commodity recommended to use over this temp. Otherwise more pricier.
    private double increaseInCost; //possible price increase if commodity not in norm temp range

    public Commodity(String commodityName, String rate, String unit, double upperTempThreshold, double lowerTempThreshold) {
        this.commodityName = commodityName;
        this.rate = rate;
        this.unit = unit;
        this.upperTempThreshold = upperTempThreshold;
        this.lowerTempThreshold = lowerTempThreshold;
    }
}
