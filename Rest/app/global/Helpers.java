package global;

import play.libs.ws.WS;

public class Helpers {
    public static final int TIMEOUT = 10 * 1000; // In Milli

    public static String getBody(String url) {
        return WS.url(url).get().get(TIMEOUT).getBody();
    }

    public static String jsonError(String error) {
         return "{\"error\": \"" + error + "\"}";
    }

}
