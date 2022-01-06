package kr.hs.entrydsm.raisepercent.global.properties;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthPropertiesTest {

    private static final String baseUrl = "https://www.google.co.kr";

    private static final String clientId = "asdfass1";

    private static final String clientSecret = "asdf12134as";

    private static final String redirectUrl = "https://www.google.co.kr";

    private static final AuthProperties authProperties = new AuthProperties(baseUrl, clientId, clientSecret, redirectUrl);

    @Test
    void 기본_링크_가져오기() {
        assertEquals(baseUrl, authProperties.getBaseUrl());
    }

    @Test
    void 클라이언트_아이디_가져오기() {
        assertEquals(clientId, authProperties.getClientId());
    }

    @Test
    void 클라이언트_시크릿_가져오기() {
        assertEquals(clientSecret, authProperties.getClientSecret());
    }

    @Test
    void 리다이렉트_링크_가져오기() {
        assertEquals(redirectUrl, authProperties.getRedirectUrl());
    }

}
