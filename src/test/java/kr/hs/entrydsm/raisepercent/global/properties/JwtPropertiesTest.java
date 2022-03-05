package kr.hs.entrydsm.raisepercent.global.properties;

import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

class JwtPropertiesTest {

    private final String secretKey = "test";

    private final Long accessExp = 1000L;

    private final Long refreshExp = 2000L;

    private final String header = "Authorization";

    private final String prefix = "Bearer";

    private final JwtProperties jwtProperties = new JwtProperties(secretKey, accessExp, refreshExp, header, prefix);

    @Test
    void 정적_변수_확인() {
        assertThat("ACCESS").isEqualTo(JwtProperties.ACCESS_TYPE);
        assertThat("REFRESH").isEqualTo(JwtProperties.REFRESH_TYPE);
    }

    @Test
    void 시크릿키_가져오기() {
        assertThat(Base64.getEncoder().encodeToString(secretKey.getBytes())).isEqualTo(jwtProperties.getSecretKey());
    }

    @Test
    void 만료_시간_가져오기() {
        Integer MICORSECPERSEC = 1000;
        assertThat(accessExp * MICORSECPERSEC).isEqualTo(jwtProperties.getAccessExp());
        assertThat(refreshExp * MICORSECPERSEC).isEqualTo(jwtProperties.getRefreshExp());
    }

    @Test
    void 헤더_가져오기() {
        assertThat(header).isEqualTo(jwtProperties.getHeader());
    }

    @Test
    void 접두사_가져오기() {
        assertThat(prefix).isEqualTo(jwtProperties.getPrefix());
    }

}