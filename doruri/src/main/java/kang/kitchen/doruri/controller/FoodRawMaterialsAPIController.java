package kang.kitchen.doruri.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

// Use Search LIST -> This API
@RestController
public class FoodRawMaterialsAPIController {
    String SERVICE_KEY = "867f3dd43c874330b921";
    static final String API_END_POINT = "http://openapi.foodsafetykorea.go.kr/api";
    static final String API_FUNC_POINT = "C002";
    private static final Logger logger = LoggerFactory.getLogger(FoodRawMaterialsAPIController.class);

    @GetMapping("/api/food_raw_materials")
    public String getApiHttp(@RequestParam String prdListName) {
        StringBuffer result = new StringBuffer();

        try {
            String urlStr = API_END_POINT + "/" + SERVICE_KEY + "/" + API_FUNC_POINT + "/json" + "/1/100/" + "PRDLST_NM=" + URLEncoder.encode(prdListName, "UTF-8");
            URL url = new URL(urlStr);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String returnLine;
            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine);
            }
            urlConnection.disconnect();
            br.close();
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException: " + e.getMessage());
        } catch (ProtocolException e) {
            logger.error("ProtocolException:" + e.getMessage());
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException:" + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException:" + e.getMessage());
        }
        return result.toString();

    }
}
