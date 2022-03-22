package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.TokenResponse;
import kr.hs.entrydsm.raisepercent.domain.user.domain.RefreshToken;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.RefreshTokenRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.global.properties.DsmAuthProperties;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.client.DsmAuth;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.request.DsmAuthTokenRequest;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.InformationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentAuthService {

    private final DsmAuth dsmAuth;
    private final DsmAuthProperties dsmAuthProperties;
    private final JwtProperties jwtProperties;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public ResponseEntity<TokenResponse> execute(String code) {
        int status = 200;
        String accessToken = dsmAuth.getToken(DsmAuthTokenRequest.builder()
                .code(code)
                .clientId(dsmAuthProperties.getClientId())
                .clientSecret(dsmAuthProperties.getClientSecret())
                .build()).getAccessToken();

        InformationResponse response = dsmAuth.getInformation("Bearer " + accessToken);

        if (studentRepository.findById(response.getEmail()).isEmpty()) {
            studentRepository.save(
                    Student.builder()
                            .user(userRepository.save(User.builder()
                                    .email(response.getEmail())
                                    .name(response.getName())
                                    .build()
                            ))
                            .number(response.getGcn())
                            .year(queryYear(response.getEmail()))
                            .build()
            );
            status = 201;
        }
        String refreshToken = jwtTokenProvider.generateRefreshToken(response.getEmail(), TokenRole.STUDENT);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .email(response.getEmail())
                        .token(refreshToken)
                        .ttl(jwtProperties.getRefreshExp())
                        .build()
        );

        return new ResponseEntity<>(TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(response.getEmail(), TokenRole.STUDENT))
                .refreshToken(refreshToken)
                .email(response.getEmail()).build(), HttpStatus.valueOf(status));
    }

    private String queryYear(String email) {
        return "20" + email.substring(0, 2);
    }

}
