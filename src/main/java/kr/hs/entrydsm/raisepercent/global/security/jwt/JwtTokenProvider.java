package kr.hs.entrydsm.raisepercent.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.hs.entrydsm.raisepercent.global.exception.ExpiredTokenException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidTokenException;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetailsService;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
import kr.hs.entrydsm.raisepercent.global.util.EnumUtil;
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

    public String generateAccessToken(String email, TokenRole role) {
        return generateToken(email, role.name(), jwtProperties.getAccessExp(), JwtProperties.ACCESS_TYPE);
    }

    public String generateRefreshToken(String email, TokenRole role) {
        return generateToken(email, role.name(), jwtProperties.getRefreshExp(), JwtProperties.REFRESH_TYPE);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperties.getHeader());
        if (bearer != null && bearer.startsWith(jwtProperties.getPrefix()) &&
                bearer.length() > jwtProperties.getPrefix().length()) {
            return bearer.substring(jwtProperties.getPrefix().length() + 1);
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        if (isRefreshToken(token)) {
            throw InvalidTokenException.EXCEPTION;
        }
        Claims claims = getJws(token).getBody();
        UserDetails userDetails = authDetailsService
                .loadUserByUsername(claims.getSubject(), EnumUtil.convertToEnum(claims.get("role", String.class), TokenRole.class));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean isRefreshToken(String token) {
        return JwtProperties.REFRESH_TYPE.equals(getJws(token).getHeader().get("typ").toString());
    }

    public TokenRole getRole(String token) {
        Object role = getJws(token).getBody().get("role");
        if (role == null) {
            throw InvalidRoleException.EXCEPTION;
        }

        return TokenRole.valueOf(role.toString());
    }

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private String generateToken(String email, String role, Long exp, String type) {
        return Jwts.builder()
                .setSubject(email)
                .setHeaderParam("typ", type)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .compact();
    }

}
