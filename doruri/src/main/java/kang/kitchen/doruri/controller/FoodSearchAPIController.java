package kang.kitchen.doruri.controller;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController

public class FoodSearchAPIController {
    private static final Logger logger = LoggerFactory.getLogger(FoodRawMaterialsAPIController.class);
    @GetMapping("/api/search_food")
    public String getApiHttp(@RequestParam String food_str) {
        try {
            URLEncoder.encode(food_str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException: "  + e.getMessage());
        }

        // food_str -> 1. 사이트 주소인지 2. 검색어인지 판별
        // Service Case 1 : Coupang
        if (food_str.contains("coupang.com")) {

            try {
                Document doc = Jsoup.connect(food_str).get();
                String content = doc.select("meta[property=og:title]").first().attr("content");
                String img = doc.select("meta[property=og:image]").first().attr("content");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("product_name", content);
                jsonObject.put("img", img);
                return jsonObject.toString();

            } catch (IOException e) {
                logger.error("IOException: "  + e.getMessage());
                return "error";

            }

        }

        // Service Case 2 : Words
        else {

            return food_str;
        }
    }

}
