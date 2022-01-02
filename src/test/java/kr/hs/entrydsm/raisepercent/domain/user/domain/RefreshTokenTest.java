package kr.hs.entrydsm.raisepercent.domain.user.domain;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RefreshTokenTest {

    private static final String email = "test@gmail.com";

    private static final String token = "1243.1asf.qwer";

    private static final Long ttl = 1000L;

    private static final RefreshToken refreshToken = RefreshToken.builder()
            .email(email)
            .token(token)
            .ttl(ttl)
            .build();

    @Test
    @Order(0)
    void 리프레시토큰_이메일_가져오기() {
        assertEquals(email, refreshToken.getEmail());
    }

    @Test
    @Order(1)
    void 리프레시토큰_토큰_가져오기() {
        assertEquals(token, refreshToken.getToken());
    }

    @Test
    @Order(2)
    void 리프레시토큰_ttl_가져오기() {
        assertEquals(ttl, refreshToken.getTtl());
    }

    @Test
    @Order(3)
    void 리프레시토큰_토큰_수정() {
        String token = "asdf.qwer.zxcv";
        Long ttl = 10L;
        refreshToken.updateToken(token, ttl);
        assertEquals(token, refreshToken.getToken());
        assertEquals(ttl, refreshToken.getTtl());
    }

}