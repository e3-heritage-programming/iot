package objects;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commodities {

    private static Map<Integer, Commodity> commodities;
    private static int id;

    static {
        reset();
    }

    public static void setCommodities(List<Commodity> commodities) {
        reset();
        commodities.forEach(objects.Commodities::addCommodity);
    }

    private static void reset() {
        commodities = new HashMap<>();
        id = 0;
    }

    public static void addCommodity(Commodity commodity) {
        commodity.setId(id++);
        commodities.put(commodity.getId(), commodity);
    }

    public static Commodity getCommodity(int id) {
        return commodities.get(id);
    }

    public static Collection<Commodity> getAllCommodities() {
        return commodities.values();
    }
}
