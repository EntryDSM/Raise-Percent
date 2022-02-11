package kr.hs.entrydsm.raisepercent.domain.teacher.service;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.domain.user.domain.RefreshToken;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.RefreshTokenRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import kr.hs.entrydsm.raisepercent.global.security.jwt.dto.TokenResponse;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
import kr.hs.entrydsm.raisepercent.global.util.auth.service.GoogleAuthService;
import kr.hs.entrydsm.raisepercent.global.util.auth.dto.PersonalInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeacherGoogleAuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TeacherRepository teacherRepository;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final GoogleAuthService googleAuthService;
    private static int status = 200;

    @Transactional
    public ResponseEntity<TokenResponse> execute(CodeRequest request) {
        PersonalInformation personalInformation= googleAuthService.execute(request);
        String email = personalInformation.getEmail();
        String name = personalInformation.getName();

        saveTeacher(email, name);

        String refreshToken = jwtTokenProvider.generateRefreshToken(email, TokenRole.TEACHER);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .email(email)
                        .token(refreshToken)
                        .ttl(jwtProperties.getRefreshExp())
                        .build()
        );

        return new ResponseEntity<>(new TokenResponse(
                jwtTokenProvider.generateAccessToken(email, TokenRole.TEACHER),
                refreshToken),
                HttpStatus.valueOf(status)
        );
    }

    private void saveTeacher(String email, String name) {
        if(teacherRepository.findById(email).isEmpty()) {
            teacherRepository.save(
                    Teacher.builder()
                            .user(
                                    userRepository.save(
                                            User.builder()
                                                    .email(email)
                                                    .name(name)
                                                    .build()
                                    )
                            )
                            .role(Role.DEFAULT)
                            .build()
            );
            status = 201;
        }
    }

}
