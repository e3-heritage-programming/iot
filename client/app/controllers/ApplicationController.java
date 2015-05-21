package controllers;

import play.mvc.Result;

public class ApplicationController extends Controller {

    /**
     * Displays default content of your web application.
     */
    public static Result getHome() {
        return ok(views.html.home.render());
    }

}
