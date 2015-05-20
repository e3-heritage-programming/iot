package objects;

import lombok.Data;

@Data
public class Location {

    private int id;
    private String locationName;
    private String countryName;

    public Location(String locationName, String countryName) {
        this.locationName = locationName;
        this.countryName = countryName;
    }
}
