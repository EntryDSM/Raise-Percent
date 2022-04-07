package kr.hs.entrydsm.raisepercent.global.security.auth;

import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.global.properties.AuthProperties;
import kr.hs.entrydsm.raisepercent.global.security.auth.dto.NameAndEmailDTO;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.client.GoogleAuth;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.client.GoogleInfo;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.request.GoogleCodeRequest;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.GoogleInfoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GoogleAuthTest {

    @Mock
    private kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.TokenResponse tokenResponse;

    @Mock
    private GoogleAuth googleAuth;

    @Mock
    private GoogleInfo googleInfo;

    @Mock
    CodeRequest codeRequest;

    @Mock
    private AuthProperties authProperties;

    @InjectMocks
    private GoogleAuthService googleAuthService;

    @Test
    void 이름_이메일_발급() {
        //given
        String googleAccessToken = "googleAccessToken";
        String code = "code";
        String clientId = "asdfass1";
        String clientSecret = "asdf12134as";
        String redirectUrl = "https://localhost:3000/callback";

        given(codeRequest.getCode())
                .willReturn(code);

        given(authProperties.getClientId())
                .willReturn(clientId);

        given(authProperties.getClientSecret())
                .willReturn(clientSecret);

        given(authProperties.getRedirectUrl())
                .willReturn(redirectUrl);

        given(tokenResponse.getAccessToken())
                .willReturn(googleAccessToken);

        given(googleAuth.googleAuth(any(GoogleCodeRequest.class)))
                .willReturn(tokenResponse);

        given(googleInfo.googleInfo(googleAccessToken))
                .willReturn(new GoogleInfoResponse());

        assertThat(googleAuthService.execute(codeRequest))
                .isInstanceOf(NameAndEmailDTO.class);

    }
}