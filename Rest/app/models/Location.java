package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Location extends Model{

    @Id
    private Long id;

    @Constraints.Required
    private String locationName;

    @Constraints.Required
    private String countryName;


    public Location(String locationName, String countryName) {
        this.locationName = locationName;
        this.countryName = countryName;
    }

    public String getLocationGlued() {
        return (locationName + "," + countryName).replace(" ", "+");
    }

    public static Model.Finder<Long, Location> find = new Model.Finder<Long, Location>(
            Long.class, Location.class
    );
}
