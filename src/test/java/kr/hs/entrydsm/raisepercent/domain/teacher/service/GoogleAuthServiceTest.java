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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GoogleAuthServiceTest {

    @Mock
    private CodeRequest codeRequest;

    @Mock
    private kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.TokenResponse tokenResponse;

    @Mock
    private GoogleAuth googleAuth;

    @Mock
    private GoogleInfo googleInfo;

    @Mock
    private AuthProperties authProperties;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private JwtProperties jwtProperties;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private GoogleAuthService googleAuthService;

    @Test
    void 선생님_구글_로그인() {
        //given
        String code = "code";
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";
        String googleAccessToken = "googleAccessToken";

        String baseUrl = "https://www.google.com";
        String clientId = "asdfass1";
        String clientSecret = "asdf12134as";
        String redirectUrl = "https://localhost:3000/callback";

        given(authProperties.getBaseUrl())
               .willReturn(baseUrl);

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

        given(jwtTokenProvider.generateAccessToken(any(), any()))
                .willReturn(accessToken);

        given(jwtTokenProvider.generateRefreshToken(any(), any()))
                .willReturn(refreshToken);

        given(teacherRepository.findById(any()))
                .willReturn(Optional.empty());

        given(codeRequest.getCode())
                .willReturn(code);

        //when
        ResponseEntity<TokenResponse> response = googleAuthService.execute(codeRequest);

        //then
        assertThat(response.getBody().getAccessToken()).isEqualTo(accessToken);
        assertThat(response.getBody().getRefreshToken()).isEqualTo(refreshToken);

        then(teacherRepository).should(times(1)).save(any());
    }

    @Test
    void 선생님_구글_회원가입() {
        Teacher teacher = Teacher.builder().build();

        given(teacherRepository.findById(any()))
                .willReturn(Optional.of(teacher));
    }

}
