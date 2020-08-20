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
public class FoodIngreidentAPIController {
    String SERVICE_KEY = "zB2dcESUiLERgLWmoOjpdFZj5TCULBlxmRtv7%2B72bA4EUAN1f%2BBF9XA%2BrNClYK6XVtWbytBUiv1AoYF3eizWrw%3D%3D";
    static final String API_END_POINT = "http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService";
    static final String API_FUNC_POINT = "getFoodNtrItdntList";

    @GetMapping("/api/food_ingredent")
    public String getApiHttp(@RequestParam String desc_kor) {
        StringBuffer result = new StringBuffer();

        try {
            String urlstr = API_END_POINT + "/" + API_FUNC_POINT + "?ServiceKey=" + SERVICE_KEY + "&desc_kor=" + URLEncoder.encode(desc_kor, "UTF-8");
            URL url = new URL(urlstr);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String returnLine;
            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine);
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject xmlJSONObj = XML.toJSONObject(result.toString());
        return xmlJSONObj.toString();

    }
}
