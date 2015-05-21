package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

public class CommoditiesController extends Controller {

    /**
     * @return Commodities page
     */
    public static Result getCommodities() {
        Map<Integer, String> commodities = new HashMap<>();

        JsonNode json = Json.parse(getBody(REMOTE_COMMODITIES_SERVICE_URL));
        for (JsonNode commodityNode : json.get("Commodities")) {
            commodities.put(Integer.parseInt(commodityNode.findValue("id").toString()),
                    commodityNode.findValue("commodityName").toString().replace("\"", ""));
        }

        return ok(views.html.Commodities.index.render(commodities));
    }

    /**
     * @return Commodities Json
     */
    public static Result getRemoteCommodities() {
        return ok(getBody(REMOTE_COMMODITIES_SERVICE_URL));
    }

    /**
     * @param id Commodity Id
     * @return Commodity info
     */
    public static Result getRemoteCommodity(int id) {
        return ok(getBody(REMOTE_COMMODITIES_SERVICE_URL + "/Id?id=" + id));
    }
}
