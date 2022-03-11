package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.RefreshToken;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.RefreshTokenRepository;
import kr.hs.entrydsm.raisepercent.domain.user.exception.TokenNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.UserNotFoundException;
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
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final HrRepository hrRepository;
    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> TokenNotFoundException.EXCEPTION);

        TokenResponse newToken = token(refreshToken.getEmail());
        refreshToken.updateToken(newToken.getRefreshToken(), jwtProperties.getRefreshExp() * 1000);

        return new TokenResponse(newToken.getAccessToken(), newToken.getRefreshToken());
    }

    private TokenResponse token(String email) {
        if (studentRepository.findById(email).isPresent()) {
            return this.generateTokens(email, TokenRole.USER);
        } else if (teacherRepository.findById(email).isPresent()) {
            return this.generateTokens(email, TokenRole.TEACHER);
        } else if (hrRepository.findById(email).isPresent()) {
            return this.generateTokens(email, TokenRole.HR_MANAGER);
        } else {
            throw UserNotFoundException.EXCEPTION;
        }

    }

    private TokenResponse generateTokens(String email, TokenRole tokenRole) {
        String newAccessToken = jwtTokenProvider.generateAccessToken(email, tokenRole);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(email, tokenRole);

        return new TokenResponse(newAccessToken, newRefreshToken);
    }
}
