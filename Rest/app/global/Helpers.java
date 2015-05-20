package global;

import play.libs.ws.WS;

import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

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

    public static String convertToURLEscapingIllegalCharacters(String string){
        try {
            String decodedURL = URLDecoder.decode(string, "UTF-8");
            URL url = new URL(decodedURL);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            return uri.toURL().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
