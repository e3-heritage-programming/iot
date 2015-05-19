package controllers;

import global.Helpers;
import objects.Commodities;
import objects.Commodity;
import play.libs.Json;
import play.mvc.Result;

public class CommodityController extends BaseController {

    /**
     * @param id Integer
     * @return Details about the specific commodity request if present. If not commodity not found.
     */
    public static Result getCommodity(int id) {
        Commodity commodity = Commodities.getCommodity(id);

        // Check if commodity was found
        if (commodity == null)
            return ok(Helpers.jsonError("Commodity not found"));

        String json = "{\"commodity\":[" + Json.toJson(commodity) + "]}";
        return ok(json);
    }


    /**
     * @return Array of commodity names.
     */
    public static Result getCommodities() {
        return ok("{\"Commodities\":" + Json.toJson(Commodities.getAllCommodities()) + "}");
    }

}
