package controllers;

import objects.Locations;
import play.libs.Json;
import play.mvc.Result;

public class ApplicationController extends BaseController {

    public static Result index() {
        return ok("Welcome to the Rest server.");
    }

    /**
     * @return Array of location names.
     */
    public static Result getLocations() {
        return ok("{\"Locations\":" + Json.toJson(Locations.getAllLocations()) + "}");
    }

}
