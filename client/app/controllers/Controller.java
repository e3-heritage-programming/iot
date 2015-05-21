package controllers;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import play.libs.ws.WS;

public class Controller extends play.mvc.Controller {

    protected final static String REMOTE_REST_SERVICE;
    protected final static String REMOTE_COMMODITIES_SERVICE_URL;
    protected final static String REMOTE_LOCATION_SERVICE_URL;
    protected final static String REMOTE_WEATHER_SERVICE_URL;

    protected static final int TIMEOUT = 10 * 1000; // In Milli

    static {
        Config conf = ConfigFactory.load();
        REMOTE_REST_SERVICE = conf.getString("application.remote");

        REMOTE_COMMODITIES_SERVICE_URL = REMOTE_REST_SERVICE + "/Commodities";
        REMOTE_LOCATION_SERVICE_URL = REMOTE_REST_SERVICE + "/Locations";
        REMOTE_WEATHER_SERVICE_URL = REMOTE_REST_SERVICE + "/Weather";
    }

    /**
     * Get remote URL and return body
     *
     * @param url String
     * @return body
     */
    protected static String getBody(String url) {
        return WS.url(url).get().get(TIMEOUT).getBody();
    }
}
