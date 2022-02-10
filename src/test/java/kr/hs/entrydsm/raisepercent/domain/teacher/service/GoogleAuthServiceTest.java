package kr.hs.entrydsm.raisepercent.domain.teacher.service;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.RefreshTokenRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.global.properties.AuthProperties;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import kr.hs.entrydsm.raisepercent.global.security.jwt.dto.TokenResponse;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.client.GoogleAuth;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.client.GoogleInfo;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.request.GoogleCodeRequest;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.GoogleInfoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    private static final UserRepository userRepository = mock(UserRepository.class);

    private static final JwtTokenProvider jwtTokenProvider = mock(JwtTokenProvider.class);

    private static final TeacherRepository teacherRepository = mock(TeacherRepository.class);

    private static final JwtProperties jwtProperties = mock(JwtProperties.class);

    private static final RefreshTokenRepository refreshTokenRepository = mock(RefreshTokenRepository.class);

    private static final GoogleAuthService googleAuthService = new GoogleAuthService(
            googleAuth,
            googleInfo,
            authProperties,
            userRepository,
            jwtTokenProvider,
            teacherRepository,
            jwtProperties,
            refreshTokenRepository
    );

    @Test
    void 선생님_구글_로그인() {
        String code = "code";
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";
        String googleAccessToken = "googleAccessToken";

        when(tokenResponse.getAccessToken())
                .thenReturn(googleAccessToken);

        when(googleAuth.googleAuth(any(GoogleCodeRequest.class)))
                .thenReturn(tokenResponse);

        when(googleInfo.googleInfo(googleAccessToken))
                .thenReturn(new GoogleInfoResponse());

        when(jwtTokenProvider.generateAccessToken(any(), any()))
                .thenReturn(accessToken);

        when(jwtTokenProvider.generateRefreshToken(any(), any()))
                .thenReturn(refreshToken);

        when(teacherRepository.findById(any()))
                .thenReturn(Optional.empty());

        when(codeRequest.getCode())
                .thenReturn(code);

        ResponseEntity<TokenResponse> response = googleAuthService.execute(codeRequest);

        assertEquals(response.getBody().getAccessToken(), accessToken);
        assertEquals(response.getBody().getRefreshToken(), refreshToken);

        verify(teacherRepository, times(1)).save(any());
    }

    @Test
    void 선생님_구글_회원가입() {
        Teacher teacher = Teacher.builder().build();

        when(teacherRepository.findById(any()))
                .thenReturn(Optional.of(teacher));
    }

}
