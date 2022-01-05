package kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GoogleCodeRequestTest {

    private static final String code = "A2dg1F";

    private static final String clientId = "asdfass1";

    private static final String clientSecret = "asdf12134as";

    private static final String redirectUri = "https://www.google.co.kr";

    private static final String grantType = "authorization_code";

    private static final GoogleCodeRequest request = GoogleCodeRequest.builder()
            .code(code)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectUri(redirectUri)
            .build();

    @Test
    void 구글_코드_요청() {
        assertEquals(code, request.getCode());
        assertEquals(clientId, request.getClientId());
        assertEquals(clientSecret, request.getClientSecret());
        assertEquals(redirectUri, request.getRedirectUri());
        assertEquals(grantType, request.getGrantType());
    }

}
