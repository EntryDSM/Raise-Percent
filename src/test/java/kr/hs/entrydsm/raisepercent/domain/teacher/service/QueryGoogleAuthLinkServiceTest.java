package kr.hs.entrydsm.raisepercent.domain.teacher.service;

import kr.hs.entrydsm.raisepercent.global.properties.AuthProperties;
import kr.hs.entrydsm.raisepercent.global.util.auth.service.QueryGoogleAuthLinkService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueryGoogleAuthLinkServiceTest {

    private final String baseUrl = "http://github.com";

    private final String clientId = "asdfass1";

    private final String clientSecret = "asdf12134as";

    private final String redirectUrl = "https://www.google.co.kr";

    private final AuthProperties authProperties = new AuthProperties(baseUrl, clientId, clientSecret, redirectUrl);

    private final QueryGoogleAuthLinkService queryGoogleAuthLinkService = new QueryGoogleAuthLinkService(authProperties);

    @Test
    void 구글_로그인_링크_가져오기() {
        String link = queryGoogleAuthLinkService.execute();

        String formatStr = String.format("%s?client_id=%s&redirect_uri=%s&response_type=code"
                        + "&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile",
                baseUrl,
                clientId,
                redirectUrl);

        assertThat(link).isEqualTo(formatStr);
    }

}
