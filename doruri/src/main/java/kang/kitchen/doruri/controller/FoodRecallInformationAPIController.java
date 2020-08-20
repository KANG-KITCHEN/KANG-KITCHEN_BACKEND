package kang.kitchen.doruri.controller;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class FoodRecallInformationAPIController {
    String SERVICE_KEY = "KRDH0QNZY5";

    //https://www.consumer.go.kr/openapi/recall/contents/index.do?serviceKey=XXXXXXXXXX&pageNo=1 &cntPerPage=10&cntntsId=메뉴ID
    static final String API_END_POINT = "https://www.consumer.go.kr/openapi/recall/contents/index.do?";
    static final String API_FUNC_POINT = "pageNo=1&cntPerPage=50&cntntsId=0201";

    @GetMapping("/api/food_recall_information")

    public String getApiHttp() {
        StringBuffer result = new StringBuffer();
        try {
            String urlStr = API_END_POINT + "serviceKey=" + SERVICE_KEY + "&" + API_FUNC_POINT;
            URL url = new URL(urlStr);
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
