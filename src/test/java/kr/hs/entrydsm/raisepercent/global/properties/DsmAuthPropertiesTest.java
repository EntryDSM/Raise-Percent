package kr.hs.entrydsm.raisepercent.global.properties;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DsmAuthPropertiesTest {

    private static final String baseUrl = "baseUrl";

    private static final String format = "baseUrl%s%s";

    private static final String clientId = "entrydsm";

    private static final String clientSecret = "entrydsmSecret";

    private static final String redirectUrl = "redirectUrl";

    private static final DsmAuthProperties dsmAuthProperties = new DsmAuthProperties(baseUrl, format, clientId,
            clientSecret, redirectUrl);

    @Test
    void 기본_링크_가져오기() {
        assertThat(dsmAuthProperties.getFormat()).isEqualTo(format);
    }

    @Test
    void 클라이언트_아이디_가져오기() {
        assertThat(dsmAuthProperties.getClientId()).isEqualTo(clientId);
    }

    @Test
    void 클라이언트_시크릿_가져오기() {
        assertThat(dsmAuthProperties.getClientSecret()).isEqualTo(clientSecret);
    }

    @Test
    void 리다이렉트_링크_가져오기() {
        assertThat(dsmAuthProperties.getRedirectUrl()).isEqualTo(redirectUrl);
    }

}