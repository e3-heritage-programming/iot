package objects;

import lombok.Data;

@Data
public class Location {

    private int id;
    private String locationName;
    private String countryName;
    private String locationGlued;

    public Location(String locationName, String countryName) {
        this.locationName = locationName;
        this.countryName = countryName;
    }

    public String getLocationGlued() {
        return (locationName + "," + countryName).replace(" ", "+");
    }
}
