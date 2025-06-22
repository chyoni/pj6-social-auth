package cwchoiit.socialauth.controller.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class SocialLoginUIController {

    @Value("${kakao.rest-api-key}")
    private String clientId;
    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @GetMapping("/page")
    public String loginPage(Model model) {
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s".formatted(clientId, redirectUri);
        model.addAttribute("location", location);
        return "login";
    }
}
