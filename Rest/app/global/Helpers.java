package global;

import play.libs.ws.WS;

import java.util.Arrays;
import java.util.Locale;

public class Helpers {
    public static final int TIMEOUT = 10 * 1000; // In Milli

    /**
     * Get a URL's body
     *
     * @param url URL
     * @return body
     */
    public static String getBody(String url) {
        return WS.url(url).get().get(TIMEOUT).getBody();
    }

    /**
     * Return formatted error
     *
     * @param error Error
     * @return Json formatted error
     */
    public static String jsonError(String error) {
        return "{\"error\": \"" + error + "\"}";
    }

    /**
     * Get a full country name by it's code.
     * Example: CA => Canada
     *
     * @param country Country Code
     * @return Country Name
     */
    public static String getCountryNameByCode(String country) {
        if (Arrays.asList(Locale.getISOCountries()).contains(country))
            return new Locale("", country).getDisplayCountry();
        else
            return country;
    }
}
