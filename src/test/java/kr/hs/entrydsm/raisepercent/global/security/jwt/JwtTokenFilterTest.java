package kr.hs.entrydsm.raisepercent.global.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtTokenFilterTest {

    private static final JwtTokenProvider jwtTokenProvider = mock(JwtTokenProvider.class);

    private static final JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);

    @Test
    void 잘못된_토큰() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(jwtTokenProvider.resolveToken(request))
                .thenReturn(null);

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void 올바른_토큰() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        String token = "token";
        Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, null);

        when(jwtTokenProvider.resolveToken(request))
                .thenReturn(token);
        when(jwtTokenProvider.getAuthentication(token))
                .thenReturn(authentication);

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
    }

}