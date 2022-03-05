package kr.hs.entrydsm.raisepercent.global.security.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class JwtTokenFilterTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private JwtTokenFilter jwtTokenFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void securityContextConfig() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    void 잘못된_토큰() throws ServletException, IOException {
        //given
        given(jwtTokenProvider.resolveToken(request))
                .willReturn(null);

        //when
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        //then
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void 올바른_토큰() throws ServletException, IOException {
        //given
        String token = "token";
        Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, null);

        given(jwtTokenProvider.resolveToken(request))
                .willReturn(token);
        given(jwtTokenProvider.getAuthentication(token))
                .willReturn(authentication);

        //when
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        //then
        assertThat(authentication).isEqualTo(SecurityContextHolder.getContext().getAuthentication());
    }

}