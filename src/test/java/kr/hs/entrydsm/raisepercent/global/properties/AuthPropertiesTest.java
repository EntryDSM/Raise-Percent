package kr.hs.entrydsm.raisepercent.global.properties;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AuthPropertiesTest {

    private final String baseUrl = "https://www.google.co.kr";

    private final String clientId = "asdfass1";

    private final String clientSecret = "asdf12134as";

    private final String redirectUrl = "https://www.google.co.kr";

    private final AuthProperties authProperties = new AuthProperties(baseUrl, clientId, clientSecret, redirectUrl);

    @Test
    void 기본_링크_가져오기() {
        assertThat(baseUrl).isEqualTo(authProperties.getBaseUrl());
    }

    @Test
    void 클라이언트_아이디_가져오기() {
        assertThat(clientId).isEqualTo(authProperties.getClientId());
    }

    @Test
    void 클라이언트_시크릿_가져오기() {
        assertThat(clientSecret).isEqualTo(authProperties.getClientSecret());
    }

    @Test
    void 리다이렉트_링크_가져오기() {
        assertThat(redirectUrl).isEqualTo(authProperties.getRedirectUrl());
    }

}
