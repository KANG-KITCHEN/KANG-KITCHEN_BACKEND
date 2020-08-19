package kang.kitchen.doruri.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController

public class FoodSearchAPIController {

    @GetMapping("/api/search_food")
    public String getApiHttp(@RequestParam String food_str) {
        // food_str -> 1. 사이트 주소인지 2. 검색어인지 판별
        // Service Case 1 : Coupang
        if(food_str.contains("coupang.com")) {
            Document doc = null;
            try {
                doc = Jsoup.connect(food_str).get();
                String content = doc.select("meta[property=og:title]").first().attr("content");
                String img = doc.select("meta[property=og:image]").first().attr("content");
                JSONObject js_object = new JSONObject();
                js_object.put("product_name",content);
                js_object.put("img",img);
                return js_object.toString();

            } catch (IOException e) {
                e.printStackTrace();
                return "error";

            }

        }

        // Service Case 2 : Words
        else {

            return food_str;
        }
    }

}
