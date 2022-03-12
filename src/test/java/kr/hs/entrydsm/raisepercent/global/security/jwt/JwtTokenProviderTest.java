package kr.hs.entrydsm.raisepercent.global.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.hs.entrydsm.raisepercent.global.exception.ExpiredTokenException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidTokenException;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetailsService;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtTokenProviderTest {

    private static final JwtProperties jwtProperties = mock(JwtProperties.class);

    private static final AuthDetailsService authDetailsService = mock(AuthDetailsService.class);

    private static final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(jwtProperties, authDetailsService);

    @Test
    void 엑세스키_발급_인증() {
        String email = "test@gmail.com";
        TokenRole role = TokenRole.STUDENT;

        when(jwtProperties.getSecretKey())
                .thenReturn("SECRETKEY");
        when(jwtProperties.getAccessExp())
                .thenReturn(100000000L);
        when(authDetailsService.loadUserByUsername(email, role))
                .thenReturn(new AuthDetails(email, Type.STUDENT));


        String accessToken = jwtTokenProvider.generateAccessToken(email, role);

        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        assertEquals(email, authentication.getName());
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            assertEquals(role.name(), authority.getAuthority());
        }

        assertFalse(jwtTokenProvider.isRefreshToken(accessToken));
    }

    @Test
    void 리프레시키_발급_인증() {
        String email = "test@gmail.com";
        TokenRole role = TokenRole.STUDENT;

        when(jwtProperties.getSecretKey())
                .thenReturn("SECRETKEY");
        when(jwtProperties.getAccessExp())
                .thenReturn(100000000L);
        when(authDetailsService.loadUserByUsername(email, role))
                .thenReturn(new AuthDetails(email, Type.STUDENT));


        String refreshToken = jwtTokenProvider.generateRefreshToken(email, role);

        assertNotNull(refreshToken);
    }

    @Test
    void 리프레시토큰_예외() {
        String email = "test@gmail.com";
        TokenRole role = TokenRole.STUDENT;

        when(jwtProperties.getSecretKey())
                .thenReturn("SECRETKEY");
        when(jwtProperties.getRefreshExp())
                .thenReturn(100000000L);
        when(authDetailsService.loadUserByUsername(email, role))
                .thenReturn(new AuthDetails(email, Type.STUDENT));


        String refreshToken = jwtTokenProvider.generateRefreshToken(email, role);

        assertThrows(InvalidTokenException.class, () -> jwtTokenProvider.getAuthentication(refreshToken));
    }

    @Test
    void 토큰_분리() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        String testToken = "TOKEN";
        String header = "Authorization";
        String prefix = "Bearer";

        when(jwtProperties.getHeader())
                .thenReturn(header);
        when(jwtProperties.getPrefix())
                .thenReturn(prefix);

        String bearerToken = jwtProperties.getPrefix() + " " + testToken;

        when(request.getHeader(header))
                .thenReturn(bearerToken);

        assertEquals(testToken, jwtTokenProvider.resolveToken(request));
    }

    @Test
    void 권한_가져오기_예외() {
        String email = "test@test.com";

        given(jwtProperties.getSecretKey())
                .willReturn("secret_key");

        String token = Jwts.builder()
                .setSubject(email)
                .setHeaderParam("typ", "refresh")
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setIssuedAt(new Date())
                .compact();

        assertThrows(InvalidRoleException.class, () -> jwtTokenProvider.getRole(token));
    }

    @Test
    void 인증_토큰_만료() {
        String email = "test@gmail.com";
        TokenRole role = TokenRole.STUDENT;

        when(jwtProperties.getSecretKey())
                .thenReturn("SECRETKEY");
        when(jwtProperties.getAccessExp())
                .thenReturn(10L);
        when(authDetailsService.loadUserByUsername(email, role))
                .thenReturn(new AuthDetails(email, Type.STUDENT));


        String accessToken = jwtTokenProvider.generateAccessToken(email, role);

        assertThrows(ExpiredTokenException.class, () -> jwtTokenProvider.getAuthentication(accessToken));
    }

    @Test
    void 잘못된_토큰() {
        String accessToken = "ASDFAS";

        when(jwtProperties.getSecretKey())
                .thenReturn("SECRETKEY");

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
        HttpServletRequest request = mock(HttpServletRequest.class);

        String header = "Authorization";
        String prefix = "Bearer";

        when(jwtProperties.getHeader())
                .thenReturn(header);
        when(jwtProperties.getPrefix())
                .thenReturn(prefix);

        when(request.getHeader(header))
                .thenReturn(token);

        assertNull(jwtTokenProvider.resolveToken(request));
    }

}