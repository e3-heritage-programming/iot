package controllers;

import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;

public class Controller extends play.mvc.Controller {
    public static final int TIMEOUT = 10 * 1000; // In Milli

    public static String getBody(String url){
        Logger.debug("URL: " + url);

        WSRequestHolder holder = WS.url(url);
        return holder.get().get(TIMEOUT).getBody();
    }
}
