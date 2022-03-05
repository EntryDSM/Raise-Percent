package kr.hs.entrydsm.raisepercent.global.security.jwt;

import kr.hs.entrydsm.raisepercent.global.exception.ExpiredTokenException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidTokenException;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetailsService;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {

    @Mock
    private JwtProperties jwtProperties;

    @Mock
    private AuthDetailsService authDetailsService;

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void 엑세스키_발급_인증() {
        //given
        String email = "test@gmail.com";
        TokenRole role = TokenRole.STUDENT;

        given(jwtProperties.getSecretKey())
                .willReturn("SECRETKEY");
        given(jwtProperties.getAccessExp())
                .willReturn(100000000L);
        given(authDetailsService.loadUserByUsername(email, role))
                .willReturn(new AuthDetails(email, Type.STUDENT));

        //when
        String accessToken = jwtTokenProvider.generateAccessToken(email, role);
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        //then
        assertThat(email).isEqualTo(authentication.getName());
        for(GrantedAuthority authority : authentication.getAuthorities()) {
            assertThat(role.name()).isEqualTo(authority.getAuthority());
        }
        assertFalse(jwtTokenProvider.isRefreshToken(accessToken));
    }

    @Test
    void 리프레시키_발급_인증() {
        //given
        String email = "test@gmail.com";
        TokenRole role = TokenRole.STUDENT;

        given(jwtProperties.getSecretKey())
                .willReturn("SECRETKEY");
        given(jwtProperties.getAccessExp())
                .willReturn(100000000L);
        given(authDetailsService.loadUserByUsername(email, role))
                .willReturn(new AuthDetails(email, Type.STUDENT));

        //when
        String refreshToken = jwtTokenProvider.generateRefreshToken(email, role);

        //then
        assertNotNull(refreshToken);
    }

    @Test
    void 리프레시토큰_예외() {
        //given
        String email = "test@gmail.com";
        TokenRole role = TokenRole.STUDENT;

        given(jwtProperties.getSecretKey())
                .willReturn("SECRETKEY");
        given(jwtProperties.getRefreshExp())
                .willReturn(100000000L);
        given(authDetailsService.loadUserByUsername(email, role))
                .willReturn(new AuthDetails(email, Type.STUDENT));

        //when
        String refreshToken = jwtTokenProvider.generateRefreshToken(email, role);

        //then
        assertThrows(InvalidTokenException.class, () -> jwtTokenProvider.getAuthentication(refreshToken));
    }

    @Test
    void 토큰_분리() {
        //given
        HttpServletRequest request = mock(HttpServletRequest.class);

        String testToken = "TOKEN";
        String header = "Authorization";
        String prefix = "Bearer";

        given(jwtProperties.getHeader())
                .willReturn(header);
        given(jwtProperties.getPrefix())
                .willReturn(prefix);

        String bearerToken = jwtProperties.getPrefix() + " " + testToken;

        given(request.getHeader(header))
                .willReturn(bearerToken);

        //when
        String resolveToken = jwtTokenProvider.resolveToken(request);

        //then
        assertThat(testToken).isEqualTo(resolveToken);
    }

    @Test
    void 인증_토큰_만료() {
        //given
        String email = "test@gmail.com";
        TokenRole role = TokenRole.STUDENT;
        String accessToken = jwtTokenProvider.generateAccessToken(email, role);

        given(jwtProperties.getSecretKey())
                .willReturn("SECRETKEY");
        given(jwtProperties.getAccessExp())
                .willReturn(10L);
        given(authDetailsService.loadUserByUsername(email, role))
                .willReturn(new AuthDetails(email, Type.STUDENT));

        //when then
        assertThrows(ExpiredTokenException.class, () -> jwtTokenProvider.getAuthentication(accessToken));
    }

    @Test
    void 잘못된_토큰() {
        //given
        String accessToken = "ASDFAS";

        given(jwtProperties.getSecretKey())
                .willReturn("SECRETKEY");

        //when then
        assertThrows(InvalidTokenException.class, () -> jwtTokenProvider.getAuthentication(accessToken));
    }

    @Test
    void 토큰_분리_실패_접두사() {
        토큰_분리_실패("TEST");
    }

    @Test
    void 토큰_분리_실패_널() {
        토큰_분리_실패(null);
    }

    @Test
    void 토큰_분리_실패_접두사동일() {
        토큰_분리_실패("Bearer");
    }

    private void 토큰_분리_실패(String token) {
        //given
        HttpServletRequest request = mock(HttpServletRequest.class);

        String header = "Authorization";
        String prefix = "Bearer";

        given(jwtProperties.getHeader())
                .willReturn(header);
        given(jwtProperties.getPrefix())
                .willReturn(prefix);
        given(request.getHeader(header))
                .willReturn(token);

        //when then
        assertNull(jwtTokenProvider.resolveToken(request));
    }

}