package kang.kitchen.doruri.controller;

import kang.kitchen.doruri.MyOAuth2AuthorizedClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {
    @Autowired
    private MyOAuth2AuthorizedClientService myService;

    @RequestMapping("/")
    public String WelcomeAPI() {
        return "도르리 API";
    }

    @GetMapping
    @ResponseBody
    @RequestMapping("/my")
    public String getMyAuthenticationFromSession(@AuthenticationPrincipal OAuth2User oAuth2User) {
//        return oAuth2User.getName().toString() ;
        return oAuth2User.toString();
    }

    @GetMapping
    @RequestMapping(value = "/update_allergy")
    public String putMyAuthenticationFromSession(@AuthenticationPrincipal OAuth2User oAuth2User, @RequestParam String Allergy) {
        return myService.update(oAuth2User.getName().toString(), Allergy).toString();
    }
}