package cwchoiit.socialauth.util;

import cwchoiit.socialauth.entity.Employee;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    @Test
    void test_fullName() {
        Employee employee = Employee.create("Anthony", "Hops", 2L);

        String token = JwtUtil.createToken(employee);

        Claims claims = JwtUtil.parseToken(token);

        assertThat(Objects.requireNonNull(claims).get("fullName"))
                .isEqualTo(employee.getFirstName() + employee.getLastName());
    }
}