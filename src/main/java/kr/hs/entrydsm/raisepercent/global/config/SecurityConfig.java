package kr.hs.entrydsm.raisepercent.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.GET, "/teachers/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/teachers/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/teachers/code").permitAll()
                .antMatchers(HttpMethod.POST, "feedback/{document-id}").hasRole("ROOT")
                .anyRequest().authenticated()
                .and().apply(new FilterConfig());
    }

}
