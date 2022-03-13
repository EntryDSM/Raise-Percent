package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.user.domain.RefreshToken;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.RefreshTokenRepository;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidTokenException;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
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

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserRefreshTokenServiceTest {

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private JwtProperties jwtProperties;

    @InjectMocks
    private UserRefreshTokenService service;

    private static final String refreshToken = "refreshToken";
    private static final String newAccessToken = "newAccessToken";
    private static final String newRefreshToken = "newRefreshToken";

    @Test
    void 학생_토큰_재발급() {
        String email = "student@test.com";

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .build();

        given(jwtTokenProvider.isRefreshToken(refreshToken))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(refreshToken))
                .willReturn(Optional.of(getRefreshToken));

        given(jwtTokenProvider.getRole(refreshToken))
                .willReturn(TokenRole.STUDENT);

        given(jwtTokenProvider.generateAccessToken(email, TokenRole.STUDENT))
                .willReturn(newAccessToken);

        given(jwtTokenProvider.generateRefreshToken(email, TokenRole.STUDENT))
                .willReturn(newRefreshToken);

        TokenResponse response = service.execute(refreshToken);

        assertThat(response.getAccessToken()).isEqualTo(newAccessToken);
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    void 선생님_토큰_재발급() {
        String email = "student@test.com";

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .build();

        given(jwtTokenProvider.isRefreshToken(refreshToken))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(refreshToken))
                .willReturn(Optional.of(getRefreshToken));

        given(jwtTokenProvider.getRole(refreshToken))
                .willReturn(TokenRole.TEACHER);

        given(jwtTokenProvider.generateAccessToken(email, TokenRole.TEACHER))
                .willReturn(newAccessToken);

        given(jwtTokenProvider.generateRefreshToken(email, TokenRole.TEACHER))
                .willReturn(newRefreshToken);

        TokenResponse response = service.execute(refreshToken);

        assertThat(response.getAccessToken()).isEqualTo(newAccessToken);
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    void 회사_토큰_재발급() {
        String email = "student@test.com";

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .build();

        given(jwtTokenProvider.isRefreshToken(refreshToken))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(refreshToken))
                .willReturn(Optional.of(getRefreshToken));

        given(jwtTokenProvider.getRole(refreshToken))
                .willReturn(TokenRole.HR_MANAGER);

        given(jwtTokenProvider.generateAccessToken(email, TokenRole.HR_MANAGER))
                .willReturn(newAccessToken);

        given(jwtTokenProvider.generateRefreshToken(email, TokenRole.HR_MANAGER))
                .willReturn(newRefreshToken);

        TokenResponse response = service.execute(refreshToken);

        assertThat(response.getAccessToken()).isEqualTo(newAccessToken);
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    void 유저_토큰_재발급() {
        String email = "student@test.com";

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .build();

        given(jwtTokenProvider.isRefreshToken(refreshToken))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(refreshToken))
                .willReturn(Optional.of(getRefreshToken));

        given(jwtTokenProvider.getRole(refreshToken))
                .willReturn(TokenRole.USER);

        given(jwtTokenProvider.generateAccessToken(email, TokenRole.USER))
                .willReturn(newAccessToken);

        given(jwtTokenProvider.generateRefreshToken(email, TokenRole.USER))
                .willReturn(newRefreshToken);

        TokenResponse response = service.execute(refreshToken);

        assertThat(response.getAccessToken()).isEqualTo(newAccessToken);
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    void 토큰_타입_예외() {
        given(jwtTokenProvider.isRefreshToken(refreshToken))
                .willReturn(false);

        assertThrows(InvalidTokenException.class, () -> service.execute(refreshToken));
    }

    @Test
    void 토큰_예외() {
        when(refreshTokenRepository.findByToken(refreshToken))
                .thenReturn(Optional.empty());

        assertThrows(InvalidTokenException.class, () -> service.execute(refreshToken));
    }

}
