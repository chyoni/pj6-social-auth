package cwchoiit.socialauth.controller.oauth;

import cwchoiit.socialauth.service.SocialAuthService;
import cwchoiit.socialauth.service.response.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/oauth")
public class SocialLoginController {

    private final SocialAuthService socialAuthService;

    @GetMapping("/kakao/callback")
    public ResponseEntity<KakaoTokenResponse> callback(@RequestParam("code") String code) {
        log.info("[callback] kakao callback code: {}", code);
        return ResponseEntity.ok(socialAuthService.getAccessTokenFromKakao(code));
    }
}
