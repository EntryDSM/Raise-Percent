package kr.hs.entrydsm.raisepercent.global.util.auth.service;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.RefreshTokenRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.global.properties.AuthProperties;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import kr.hs.entrydsm.raisepercent.global.util.auth.dto.PersonalInformation;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.client.GoogleAuth;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.client.GoogleInfo;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.request.GoogleCodeRequest;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.GoogleInfoResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GoogleAuthServiceTest {

    private static final String baseUrl = "https://www.google.com";

    private static final String clientId = "asdfass1";

    private static final String clientSecret = "asdf12134as";

    private static final String redirectUrl = "https://localhost:3000/callback";

    private static final CodeRequest codeRequest = mock(CodeRequest.class);

    private static final kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.TokenResponse tokenResponse =
            mock(kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.TokenResponse.class);

    private static final GoogleAuth googleAuth = mock(GoogleAuth.class);

    private static final GoogleInfo googleInfo = mock(GoogleInfo.class);

    private static final AuthProperties authProperties = new AuthProperties(baseUrl, clientId, clientSecret, redirectUrl);

    private static final JwtTokenProvider jwtTokenProvider = mock(JwtTokenProvider.class);

    private static final TeacherRepository teacherRepository = mock(TeacherRepository.class);

    private static final JwtProperties jwtProperties = mock(JwtProperties.class);

    private static final GoogleInfoResponse googleInfoResponse = mock(GoogleInfoResponse.class);

    private static final PersonalInformation personalInformation = mock(PersonalInformation.class);

    private static final GoogleAuthService googleAuthService = new GoogleAuthService(
            googleAuth,
            googleInfo,
            authProperties
    );

    @Test
    void 사용자_이메일_이름_받기() {
        String email = "email";
        String name = "name";
        String code = "code";
        String googleAccessToken = "googleAccessToken";

        when(codeRequest.getCode())
                .thenReturn(code);

        when(googleAuth.googleAuth(any(GoogleCodeRequest.class)))
                .thenReturn(tokenResponse);

        when(tokenResponse.getAccessToken())
                .thenReturn(googleAccessToken);

        when(googleInfoResponse.getEmail())
                .thenReturn(email);

        when(googleInfoResponse.getName())
                .thenReturn(name);

        when(personalInformation.getEmail())
                .thenReturn(email);

        when(personalInformation.getName())
                .thenReturn(name);

        when(googleAuthService.execute(codeRequest))
                .thenReturn(personalInformation);
    }

}
