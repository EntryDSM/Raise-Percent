package kr.hs.entrydsm.raisepercent.global.security.jwt.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenResponseTest {

    private static final String accessToken = "sadfasfdsadsfsfsadfasfesadfa.asdfesadfasedafsd.asdfaesdfadsdasfdsfsfa";

    private static final String refreshToken = "finioanoijxlcnioaybes.uebusvbfaugvfausigfe.iyasvguhdsfasknfkasdufbeiolow";

    @Test
    void 토큰_응답() {
        TokenResponse response = new TokenResponse(accessToken, refreshToken);

        assertEquals(accessToken, response.getAccessToken());
        assertEquals(refreshToken, response.getRefreshToken());
    }

}
