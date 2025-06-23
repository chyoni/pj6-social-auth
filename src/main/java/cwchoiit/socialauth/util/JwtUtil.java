package cwchoiit.socialauth.util;

import cwchoiit.socialauth.entity.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class JwtUtil {

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            "dGhpc0lzQVZlcnlTZWN1cmVTZWNyZXRLZXlGb3JKV1RUb2tlbkdlbmVyYXRpb25BbmRWYWxpZGF0aW9u".getBytes()
    );

    public static String createToken(Employee employee) {
        Date iat = new Date();
        Date expirationDate = new Date(iat.getTime() + TimeUnit.DAYS.toMillis(1));

        Map<String, Object> claims = new HashMap<>();
        claims.put("employeeId", employee.getEmployeeId());
        claims.put("fullName", employee.getFirstName() + employee.getLastName());

        return Jwts.builder()
                .subject(String.valueOf(employee.getEmployeeId()))
                .claims(claims)
                .issuedAt(iat)
                .expiration(expirationDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
