package kang.kitchen.doruri.controller;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@RestController
public class FoodRecallInformationAPIController {
    private final String SERVICE_KEY = "KRDH0QNZY5";
    private static final Logger logger = LoggerFactory.getLogger(FoodRawMaterialsAPIController.class);
    private final String API_END_POINT = "https://www.consumer.go.kr/openapi/recall/contents/index.do?";
    private final String API_FUNC_POINT = "pageNo=1&cntPerPage=50&cntntsId=0201";

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
            br.close();
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException" + e.getMessage());
        } catch (ProtocolException e) {
            logger.error("ProtocolException" + e.getMessage());
        } catch (MalformedURLException e) {
            logger.error("MalformedURLExceptio" + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException" + e.getMessage());
        }
        JSONObject xmlJSONObj = XML.toJSONObject(result.toString());
        return xmlJSONObj.toString();
    }
}
