package kr.hs.entrydsm.raisepercent.global.config;

import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsUtils;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

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
                .antMatchers(HttpMethod.GET, "/students/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/students/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/teachers/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/teachers/auth").permitAll()
                .antMatchers(HttpMethod.PUT, "/teachers/auth").hasRole("DEFAULT")
                .antMatchers(HttpMethod.POST, "/teachers/code").permitAll()
                .antMatchers(HttpMethod.GET, "/teachers/code").hasRole("ROOT")
                .antMatchers(HttpMethod.PUT, "/teachers/reissue").hasRole("ROOT")
                .antMatchers(HttpMethod.POST, "/feedback/{document-id}").hasRole("ROOT")
                .antMatchers(HttpMethod.GET, "/feedback/{feedback-id}").hasAnyRole("ROOT", "STUDENT")
                .antMatchers(HttpMethod.PATCH, "/feedback/{feedback-id}").hasRole("ROOT")
                .antMatchers(HttpMethod.DELETE, "/feedback/{feedback-id}").hasRole("ROOT")
                .antMatchers(HttpMethod.GET, "/students/{student-email}").hasAnyRole("ROOT", "SENIOR", "STUDENT", "TEACHER")
                .antMatchers(HttpMethod.PATCH, "/students/position").hasRole("STUDENT")
                .antMatchers(HttpMethod.GET, "/students/bookmark").hasRole("STUDENT")
                .antMatchers(HttpMethod.POST, "/notices").hasRole("ROOT")
                .antMatchers(HttpMethod.DELETE,"/notices/{notice-id}").hasRole("ROOT")
                .antMatchers(HttpMethod.POST, "/documents").hasRole("STUDENT")
                .antMatchers(HttpMethod.GET, "/documents/submit").hasRole("ROOT")
                .antMatchers(HttpMethod.POST, "/documents/local/{local-document-id}").hasRole("STUDENT")
                .antMatchers(HttpMethod.PATCH, "/documents/local/{local-document-id}").hasRole("STUDENT")
                .antMatchers(HttpMethod.GET, "/documents/public/{public-document-id}").hasAnyRole("STUDENT", "SENIOR", "TEACHER", "ROOT")
                .antMatchers(HttpMethod.DELETE, "/documents/public/{public-document-id}").hasRole("STUDENT")
                .antMatchers(HttpMethod.POST, "/tags").hasRole("ROOT")
                .antMatchers(HttpMethod.DELETE, "/tags/{tag-id}").hasRole("ROOT")
                .antMatchers(HttpMethod.POST, "/bookmarks/{student-email}").hasAnyRole("SENIOR","JUNIOR")
                .anyRequest().authenticated()
                .and().apply(new FilterConfig(jwtTokenProvider));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
