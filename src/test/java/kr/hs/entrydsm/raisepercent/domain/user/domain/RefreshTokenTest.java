package kr.hs.entrydsm.raisepercent.domain.user.domain;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RefreshTokenTest {

    private final String email = "test@gmail.com";

    private final String token = "1243.1asf.qwer";

    private final Long ttl = 1000L;

    private final RefreshToken refreshToken = RefreshToken.builder()
            .email(email)
            .token(token)
            .ttl(ttl)
            .build();

    @Test
    @Order(0)
    void 리프레시토큰_이메일_가져오기() {
        assertThat(email).isEqualTo(refreshToken.getEmail());
    }

    @Test
    @Order(1)
    void 리프레시토큰_토큰_가져오기() {
        assertThat(token).isEqualTo(refreshToken.getToken());
    }

    @Test
    @Order(2)
    void 리프레시토큰_ttl_가져오기() {
        assertThat(ttl).isEqualTo(refreshToken.getTtl());
    }

    @Test
    @Order(3)
    void 리프레시토큰_토큰_수정() {
        String token = "asdf.qwer.zxcv";
        Long ttl = 10L;
        refreshToken.updateToken(token, ttl);
        assertThat(token).isEqualTo(refreshToken.getToken());
        assertThat(ttl).isEqualTo(refreshToken.getTtl());
    }

}