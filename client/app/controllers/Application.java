package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

public class Application extends Controller {

    /**
     * Query an external webApplication and display the result data in your web Application.
     */
    public static Result consumeExternalWebserviceData() {
        return ok(Json.parse(getBody(REMOTE_COMMODITIES_SERVICE_URL)));
    }

    /**
     * Query an external webApplication and apply template and display data in readable format.
     */
    public static Result consumeExternalWebserviceRender() {
        Map<Integer, String> commodities = new HashMap<>();

        JsonNode json = Json.parse(getBody(REMOTE_COMMODITIES_SERVICE_URL));
        for (JsonNode commodityNode : json.get("Commodities")) {
            commodities.put(Integer.parseInt(commodityNode.findValue("id").toString()),
                    commodityNode.findValue("commodityName").toString().replace("\"", ""));
        }

        return ok(views.html.Application.commodity.render(commodities));
    }


    /**
     * Displays default content of your web application.
     */
    public static Result index() {
        return ok("Welcome to the client front-end.");
    }

    /**
     * Get all commodities from remote web service.
     */
    public static Result getRemoteCommodities() {
        return ok(getBody(REMOTE_COMMODITIES_SERVICE_URL));
    }

    /**
     * Get a specific commodity information from remote web service.
     *
     * @param id Integer
     */
    public static Result getRemoteCommodity(int id) {
        return ok(getBody(REMOTE_COMMODITIES_SERVICE_URL + "/Id?id=" + id));
    }



}
