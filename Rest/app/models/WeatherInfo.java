package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class WeatherInfo extends Model {

    @Id
    private Long id;

    @Constraints.Required
    private Long location;

    @Formats.DateTime(pattern = "YYYY-MM-DDThh:mmTZD")
    private DateTime date;

    @Constraints.Required
    @Column(columnDefinition = "TEXT")
    private String data;

    public WeatherInfo(Long location, DateTime date, String data){
        this.location = location;
        this.date = date;
        this.data = data;
    }

    public static Finder<Long, WeatherInfo> find = new Finder<Long, WeatherInfo>(
            Long.class, WeatherInfo.class
    );
}