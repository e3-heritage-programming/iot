package controllers;

import play.mvc.Result;

public class ApplicationController extends BaseController {
    public static Result index() {
        return ok("Welcome to the Rest server.");
    }
}