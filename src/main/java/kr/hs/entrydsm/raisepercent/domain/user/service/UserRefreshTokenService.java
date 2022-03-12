package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.RefreshToken;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.RefreshTokenRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidTokenException;
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
    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(String token) {
        if (!jwtTokenProvider.isRefreshToken(token)) {
            throw InvalidTokenException.EXCEPTION;
        }

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);

        TokenResponse newToken = getToken(refreshToken.getEmail(), refreshToken.getToken());
        refreshToken.updateToken(newToken.getRefreshToken(), jwtProperties.getRefreshExp() * 1000);

        return new TokenResponse(newToken.getAccessToken(), newToken.getRefreshToken());
    }

    private TokenResponse getToken(String email, String token) {
        TokenRole role = jwtTokenProvider.getRole(token);

        switch (role) {
            case STUDENT:
                if (studentRepository.findById(email).isPresent()) {
                    return this.generateTokens(email, TokenRole.STUDENT);
                }
                break;
            case TEACHER:
                if (teacherRepository.findById(email).isPresent()) {
                    return this.generateTokens(email, TokenRole.TEACHER);
                }
                break;
            case HR_MANAGER:
                if (hrRepository.findById(email).isPresent()) {
                    return this.generateTokens(email, TokenRole.HR_MANAGER);
                }
                break;
            case USER:
                if (userRepository.findById(email).isPresent()) {
                    return this.generateTokens(email, TokenRole.USER);
                }
                break;
            default:
                throw UserNotFoundException.EXCEPTION;
        }
        throw UserNotFoundException.EXCEPTION;
    }

    private TokenResponse generateTokens(String email, TokenRole tokenRole) {
        String newAccessToken = jwtTokenProvider.generateAccessToken(email, tokenRole);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(email, tokenRole);

        return new TokenResponse(newAccessToken, newRefreshToken);
    }
}