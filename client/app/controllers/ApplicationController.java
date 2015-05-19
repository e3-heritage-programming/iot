package controllers;

import play.libs.Json;
import play.mvc.Result;

public class ApplicationController extends Controller {

    /**
     * Query an external webApplication and display the result data in your web ApplicationController.
     */
    public static Result consumeExternalWebserviceData() {
        return ok(Json.parse(getBody(REMOTE_COMMODITIES_SERVICE_URL)));
    }


    /**
     * Displays default content of your web application.
     */
    public static Result getHome() {
        return ok(views.html.home.render());
    }




}
