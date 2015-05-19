package objects;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locations {

    private static Map<Integer, Location> locations;

    public static void setLocations(List<Location> locations) {
        Locations.locations = new HashMap<>();
        int id = 0;
        for (Location location : locations) {
            location.setId(id);
            Locations.locations.put(id++, location);
        }

    }

    public static Location getLocation(int id) {
        return locations.get(id);
    }


    /**
     * @return Set<String> all commoditiy names.
     */
    public static Collection<Location> getAllLocations() {
        return locations.values();
    }
}
