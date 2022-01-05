package kr.hs.entrydsm.raisepercent.global.security.jwt;

import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtPropertiesTest {

    private static final Integer MICORSECPERSEC = 1000;

    private static final String secretKey = "test";

    private static final Long accessExp = 1000L;

    private static final Long refreshExp = 2000L;

    private static final String header = "Authorization";

    private static final String prefix = "Bearer";

    private static final JwtProperties jwtProperties = new JwtProperties(secretKey, accessExp, refreshExp, header, prefix);

    @Test
    void 정적_변수_확인() {
        assertEquals("ACCESS", JwtProperties.ACCESS_TYPE);
        assertEquals("REFRESH", JwtProperties.REFRESH_TYPE);
    }

    @Test
    void 시크릿키_가져오기() {
        assertEquals(Base64.getEncoder().encodeToString(secretKey.getBytes()), jwtProperties.getSecretKey());
    }

    @Test
    void 만료_시간_가져오기() {
        assertEquals(accessExp * MICORSECPERSEC, jwtProperties.getAccessExp());
        assertEquals(refreshExp * MICORSECPERSEC, jwtProperties.getRefreshExp());
    }

    @Test
    void 헤더_가져오기() {
        assertEquals(header, jwtProperties.getHeader());
    }

    @Test
    void 접두사_가져오기() {
        assertEquals(prefix, jwtProperties.getPrefix());
    }

}