# PJ6. Social OAuth

소셜 로그인

---

## 📌 프로젝트 목적

- 소셜 로그인 구현

---

## 🧰 기술 스택

| 항목    | 내용                                                                                    |
|-------|---------------------------------------------------------------------------------------|
| 언어    | Java 21                                                                               |
| 빌드 도구 | Gradle (Build, Multi Module)                                                          |
| 개발    | Spring Boot 3.5.0 Spring Security 3.5.0, Spring Data JPA 3.5.0, Docker (Redis, MySQL) |
| 데이터   | Redis 7.4, MySQL 8.0.38                                                               |

---

## 📈 주요 기능

| 컴포넌트            | 기능 항목   | 상태 |
|-----------------|---------|----|
| **Kakao Login** | 카카오 로그인 | ✅  |
| **Employee**    | 회원 등록   | ✅  |
|                 | 회원 조회   | ✅  |

---

## 카카오 로그인 연동 절차

- 카카오 로그인 요청 (client id / redirect uri)
- redirect uri에 코드 전달
- 앱이 코드를 통해 access token을 카카오에 발급 요청
- access token 으로 카카오에 사용자 정보 요청

### 카카오 로그인 설정

- https://developers.kakao.com/console/app 접속
- 애플리케이션 등록
- 좌측 [카카오 로그인] 섹션에서 원하는 동의 항목 선택
- 좌측 [카카오 로그인] 클릭해서 활성화
- 동일 화면에서 Redirect URL 설정