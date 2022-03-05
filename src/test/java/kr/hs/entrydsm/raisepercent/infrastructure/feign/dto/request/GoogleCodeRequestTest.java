package kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoogleCodeRequestTest {

    private final String code = "A2dg1F";

    private final String clientId = "asdfass1";

    private final String clientSecret = "asdf12134as";

    private final String redirectUri = "https://www.google.co.kr";

    private final String grantType = "authorization_code";

    private final GoogleCodeRequest request = GoogleCodeRequest.builder()
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
