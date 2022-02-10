package kr.hs.entrydsm.raisepercent.global.config;

import kr.hs.entrydsm.raisepercent.global.error.ExceptionHandlingFilter;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenFilter;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void configure(HttpSecurity builder) {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
        ExceptionHandlingFilter exceptionHandlingFilter = new ExceptionHandlingFilter();
        builder.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(exceptionHandlingFilter, JwtTokenFilter.class);
    }
}
