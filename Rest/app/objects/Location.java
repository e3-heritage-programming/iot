package objects;

import lombok.Data;

import java.net.URLEncoder;

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
        return URLEncoder.encode(locationName + "," + countryName, "UTF-8");
    }
}
