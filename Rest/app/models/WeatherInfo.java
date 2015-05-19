package models;

import org.joda.time.DateTime;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WeatherInfo extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public int location;

    @Formats.DateTime(pattern = "YYYY-MM-DDThh:mmTZD")
    public DateTime date;

    @Constraints.Required
    @Column(columnDefinition = "TEXT")
    public String data;

    public static Finder<Long, WeatherInfo> find = new Finder<Long, WeatherInfo>(
            Long.class, WeatherInfo.class
    );
}