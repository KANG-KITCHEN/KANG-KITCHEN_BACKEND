package kang.kitchen.doruri.controller;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

@RestController
public class FoodIngreidentAPIController {
    private final String SERVICE_KEY = "zB2dcESUiLERgLWmoOjpdFZj5TCULBlxmRtv7%2B72bA4EUAN1f%2BBF9XA%2BrNClYK6XVtWbytBUiv1AoYF3eizWrw%3D%3D";
    private final String API_END_POINT = "http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService";
    private final String API_FUNC_POINT = "getFoodNtrItdntList";
    private static final Logger logger = LoggerFactory.getLogger(FoodRawMaterialsAPIController.class);

    @GetMapping("/api/food_ingredent")
    public String getApiHttp(@RequestParam String descKor) {
        StringBuffer result = new StringBuffer();

        try {
            String urlstr = API_END_POINT + "/" + API_FUNC_POINT + "?ServiceKey=" + SERVICE_KEY + "&descKor=" + URLEncoder.encode(descKor, "UTF-8");
            URL url = new URL(urlstr);

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
            logger.error("UnsupportedEncodingException" + e.getMessage());
        } catch (ProtocolException e) {
            logger.error("ProtocolException" + e.getMessage());
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException" + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException" + e.getMessage());
        }
        JSONObject xmlJSONObj = XML.toJSONObject(result.toString());
        return xmlJSONObj.toString();

    }
}
