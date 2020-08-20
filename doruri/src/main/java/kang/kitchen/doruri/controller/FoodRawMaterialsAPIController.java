package kang.kitchen.doruri.controller;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

// Use Search LIST -> This API
@RestController
public class FoodRawMaterialsAPIController {
    String SERVICE_KEY = "867f3dd43c874330b921";
    static final String API_END_POINT = "http://openapi.foodsafetykorea.go.kr/api";
    static final String API_FUNC_POINT = "C002";

    @GetMapping("/api/food_raw_materials")
    public String getApiHttp(@RequestParam String prdlst_nm) {
        StringBuffer result = new StringBuffer();

        try {
            String urlStr = API_END_POINT + "/" + SERVICE_KEY + "/" + API_FUNC_POINT + "/json" + "/1/100/" + "PRDLST_NM=" + URLEncoder.encode(prdlst_nm, "UTF-8");
            URL url = new URL(urlStr);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            System.out.println(urlConnection.getContentEncoding());
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String returnLine;
            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine);
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();

    }
}
