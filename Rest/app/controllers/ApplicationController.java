package controllers;

import play.mvc.Result;

public class ApplicationController extends BaseController {
    /**
     * @return Home page
     */
    public static Result index() {
        return ok("Welcome to the Rest server.");
    }
}