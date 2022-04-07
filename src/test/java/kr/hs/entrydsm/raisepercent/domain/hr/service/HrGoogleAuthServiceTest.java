package kr.hs.entrydsm.raisepercent.domain.hr.service;


import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.RefreshTokenRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.auth.GoogleAuthService;
import kr.hs.entrydsm.raisepercent.global.security.auth.dto.NameAndEmailDTO;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import kr.hs.entrydsm.raisepercent.global.security.jwt.dto.TokenResponse;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
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
class HrGoogleAuthServiceTest {

    @Mock
    private CodeRequest codeRequest;

    @Mock
    private HrRepository hrRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private JwtProperties jwtProperties;

    @Mock
    private GoogleAuthService googleAuthService;

    @InjectMocks
    private HrGoogleAuthService hrGoogleAuthService;

    @Test
    void 인사_담당자_구글_로그인() {
        //given
        String code = "code";
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";
        Long ttl = 1234L;

        String name = "name";
        String email = "email";

        NameAndEmailDTO nameAndEmailDTO = NameAndEmailDTO.builder()
                .name(name)
                .email(email)
                .build();

        given(codeRequest.getCode())
                .willReturn(code);

        given(googleAuthService.execute(codeRequest))
                .willReturn(nameAndEmailDTO);

        given(hrRepository.findById(email))
                .willReturn(Optional.empty());

        given(jwtTokenProvider.generateRefreshToken(email, TokenRole.HR_MANAGER))
                .willReturn(refreshToken);

        given(jwtTokenProvider.generateAccessToken(email, TokenRole.HR_MANAGER))
                .willReturn(accessToken);

        given(jwtProperties.getRefreshExp())
                .willReturn(ttl);

        //when
        ResponseEntity<TokenResponse> response = hrGoogleAuthService.execute(codeRequest);

        then(refreshTokenRepository).should(times(1)).save(any());
        then(hrRepository).should(times(1)).save(any());
        then(userRepository).should(times(1)).save(any());

        //then
        assertThat(response.getBody().getAccessToken()).isEqualTo(accessToken);
        assertThat(response.getBody().getRefreshToken()).isEqualTo(refreshToken);
    }

    @Test
    void 인사_담당자_구글_회원가입() {
        Hr hr = Hr.builder().build();

        given(hrRepository.findById(any()))
                .willReturn(Optional.of(hr));
    }

}