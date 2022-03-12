package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.user.domain.RefreshToken;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.RefreshTokenRepository;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidTokenException;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import kr.hs.entrydsm.raisepercent.global.security.jwt.dto.TokenResponse;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(String token) {
        if (!jwtTokenProvider.isRefreshToken(token)) {
            throw InvalidTokenException.EXCEPTION;
        }

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);

        TokenRole role = jwtTokenProvider.getRole(refreshToken.getToken());
        TokenResponse newToken = this.generateTokens(refreshToken.getEmail(), role);
        refreshToken.updateToken(newToken.getRefreshToken(), jwtProperties.getRefreshExp() * 1000);

        return new TokenResponse(newToken.getAccessToken(), newToken.getRefreshToken());
    }

    private TokenResponse generateTokens(String email, TokenRole tokenRole) {
        String newAccessToken = jwtTokenProvider.generateAccessToken(email, tokenRole);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(email, tokenRole);

        return new TokenResponse(newAccessToken, newRefreshToken);
    }
}