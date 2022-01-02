package kr.hs.entrydsm.raisepercent.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.hs.entrydsm.raisepercent.global.exception.ExpiredTokenException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidTokenException;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;

    public String generateAccessToken(String email, String role) {
        return generateToken(email, role, jwtProperties.getAccessExp(), JwtProperties.ACCESS_TYPE);
    }

    public String generateRefreshToken(String email, String role) {
        return generateToken(email, role, jwtProperties.getAccessExp(), JwtProperties.REFRESH_TYPE);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperties.getHeader());
        System.out.println(jwtProperties.getHeader());
        System.out.println(request.getHeader(jwtProperties.getHeader()));
        System.out.println(bearer);
        if (bearer != null && bearer.startsWith(jwtProperties.getPrefix()) &&
                bearer.length() > jwtProperties.getPrefix().length()) {
            return bearer.substring(jwtProperties.getPrefix().length() + 1);
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = authDetailsService
                .loadUserByUsername(claims.getSubject(), claims.get("role", String.class));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean isRefreshToken(String token) {
        return JwtProperties.REFRESH_TYPE.equals(getClaims(token).get("typ", String.class));
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private String generateToken(String email, String role, Long exp, String type) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("typ", type)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .compact();
    }

}
