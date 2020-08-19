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

@RestController
public class FoodRawMaterialsAPIController {
    String SERVICE_KEY = "867f3dd43c874330b921";
    static final String API_END_POINT = "http://openapi.foodsafetykorea.go.kr/api";
    static final String API_FUNC_POINT = "C002";
    @GetMapping("/api/food_raw_materials")
    public String getApiHttp(@RequestParam String prdlst_nm) {
        StringBuffer result = new StringBuffer();

        try {
            String urlstr = API_END_POINT+"/"+SERVICE_KEY+"/"+API_FUNC_POINT+"/json"+"/1/10/"+"PRDLST_NM="+ URLEncoder.encode(prdlst_nm,"UTF-8");
            URL url = new URL(urlstr);

            HttpURLConnection urlconenction = (HttpURLConnection) url.openConnection();
            urlconenction.setRequestMethod("GET");
            System.out.println(urlconenction.getContentEncoding());
            BufferedReader br = new BufferedReader(new InputStreamReader(urlconenction.getInputStream(),"UTF-8"));
            String returnLine;
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine);
            }
            urlconenction.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();

    }
}
