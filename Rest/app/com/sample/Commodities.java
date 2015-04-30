package com.sample;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commodities {

    private static Map<Integer, Commodity> commodities;

    public static void setCommodities(List<Commodity> commodities) {
        Commodities.commodities = new HashMap<>();
        int id = 0;
        for (Commodity commodity : commodities) {
            commodity.setId(id);
            Commodities.commodities.put(id++, commodity);
        }

    }

    public static Commodity getCommodity(int id) {
        return commodities.get(id);
    }

    public static Collection<Commodity> getAllCommodities() {
        return commodities.values();
    }
}
