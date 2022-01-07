package kr.hs.entrydsm.raisepercent.domain.teacher.service;

import kr.hs.entrydsm.raisepercent.global.properties.AuthProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryGoogleAuthLinkServiceTest {

    private static final String baseUrl = "http://github.com";

    private static final String clientId = "asdfass1";

    private static final String clientSecret = "asdf12134as";

    private static final String redirectUrl = "https://www.google.co.kr";

    private static final AuthProperties authProperties = new AuthProperties(baseUrl, clientId, clientSecret, redirectUrl);

    private static final QueryGoogleAuthLinkService queryGoogleAuthLinkService = new QueryGoogleAuthLinkService(authProperties);

    @Test
    void 구글_로그인_링크_가져오기() {
        String link = queryGoogleAuthLinkService.execute();

        String formatStr = String.format("%s?client_id=%s&redirect_uri=%s&response_type=code"
                        + "&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile",
                baseUrl,
                clientId,
                redirectUrl);

        assertEquals(link, formatStr);
    }

}
