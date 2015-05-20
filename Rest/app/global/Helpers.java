package global;

import play.libs.ws.WS;

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

}
