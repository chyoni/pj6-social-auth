package cwchoiit.socialauth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cwchoiit.socialauth.service.response.KakaoTokenResponse;
import cwchoiit.socialauth.service.response.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocialAuthService {

    @Value("${kakao.rest-api-key}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    HttpClient client = HttpClient.newHttpClient();

    private final ObjectMapper objectMapper;

    public KakaoTokenResponse getAccessTokenFromKakao(String code) {
        final String KAKAO_AUTH_URL = "https://kauth.kakao.com/oauth/token";

        try {
            // 쿼리 파라미터 생성
            String formData = "grant_type=" + URLEncoder.encode("authorization_code", StandardCharsets.UTF_8) +
                    "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8) +
                    "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
                    "&code=" + URLEncoder.encode(code, StandardCharsets.UTF_8);

            // HTTP 요청 생성
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(KAKAO_AUTH_URL))
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                    .POST(HttpRequest.BodyPublishers.ofString(formData))
                    .build();

            // HTTP 요청 전송
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 응답을 KakaoTokenResponse로 변환
            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), KakaoTokenResponse.class);
            } else {
                log.error("카카오 토큰 요청 실패: {}", response.body());
                throw new RuntimeException("카카오 토큰 요청 실패: " + response.statusCode());
            }

        } catch (URISyntaxException | IOException | InterruptedException e) {
            log.error("카카오 토큰 요청 중 오류 발생", e);
            throw new RuntimeException("카카오 토큰 요청 중 오류 발생", e);
        }
    }

    public KakaoUserInfoResponse findUserFromKakao(String accessToken) {
        final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(KAKAO_USER_INFO_URL))
                    .header("Authorization", "Bearer %s".formatted(accessToken))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), KakaoUserInfoResponse.class);
            } else {
                log.error("[findUserFromKakao] 카카오 유저 정보 요청 실패 : {}", response.body());
                throw new RuntimeException("카카오 유저 정보 요청 실패: " + response.statusCode());
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            log.error("[findUserFromKakao] 카카오 유저 정보 요청 중 오류 발생 ", e);
            throw new RuntimeException("카카오 유저 정보 요청 중 오류 발생", e);
        }
    }
}