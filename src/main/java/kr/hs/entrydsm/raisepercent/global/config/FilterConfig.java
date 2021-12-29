package kr.hs.entrydsm.raisepercent.global.config;

import kr.hs.entrydsm.raisepercent.global.error.ExceptionHandlingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity builder) {
        ExceptionHandlingFilter exceptionHandlingFilter = new ExceptionHandlingFilter();
        builder.addFilterBefore(exceptionHandlingFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
