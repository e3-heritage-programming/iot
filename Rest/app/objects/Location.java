package objects;

public class Location {

    private int id;
    private String locationName;
    private String countryName;

    public Location(String locationName, String countryName) {
        this.locationName = locationName;
        this.countryName = countryName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String commodityName) {
        this.locationName = commodityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String rate) {
        this.countryName = rate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
