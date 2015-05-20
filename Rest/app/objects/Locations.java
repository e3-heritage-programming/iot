package objects;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locations {

    private static Map<Integer, Location> locations;
    private static int id;

    static {
        reset();
    }

    public static void setLocations(List<Location> locations) {
        reset();
        locations.forEach(objects.Locations::addLocation);
    }

    private static void reset() {
        locations = new HashMap<>();
        id = 0;
    }

    public static void addLocation(Location location) {
        location.setId(id++);
        locations.put(location.getId(), location);
    }

    public static Location getLocation(int id) {
        return locations.get(id);
    }

    public static Collection<Location> getAllLocations() {
        return locations.values();
    }
}
