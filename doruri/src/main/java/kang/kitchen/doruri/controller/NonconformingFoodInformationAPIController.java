package kang.kitchen.doruri.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

@RestController
public class NonconformingFoodInformationAPIController {
    String SERVICE_KEY;

    {
        try {
            SERVICE_KEY = URLDecoder.decode("zB2dcESUiLERgLWmoOjpdFZj5TCULBlxmRtv7%2B72bA4EUAN1f%2BBF9XA%2BrNClYK6XVtWbytBUiv1AoYF3eizWrw%3D%3D","UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    static final String API_END_POINT = "http://apis.data.go.kr/1470000/PrsecImproptFoodInfoService";
    static final String API_FUNC_POINT = "getPrsecImproptFoodList";
    @GetMapping("/api/nonconform")
    public String getApiHttp(@RequestParam String prduct) {
        StringBuffer result = new StringBuffer();

        try {
            String urlstr = API_END_POINT+"/"+API_FUNC_POINT+"?ServiceKey="+SERVICE_KEY+"&Prduct="+prduct;
            URL url = new URL(urlstr);
            System.out.println(url.toString());
            HttpURLConnection urlconenction = (HttpURLConnection) url.openConnection();
            urlconenction.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlconenction.getInputStream(),"UTF-8"));

            String returnLine;
            result.append("<xmp>");
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine);
            }
            urlconenction.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result + "</xmp>";

    }
}
