package kr.hs.entrydsm.raisepercent.domain.hr.service;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.repositories.CompanyRepository;
import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.RefreshToken;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.RefreshTokenRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.auth.GoogleAuthService;
import kr.hs.entrydsm.raisepercent.global.security.auth.dto.NameAndEmailDTO;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import kr.hs.entrydsm.raisepercent.global.security.jwt.dto.TokenResponse;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class HrGoogleAuthService {

    private final GoogleAuthService googleAuthService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final HrRepository hrRepository;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public ResponseEntity<TokenResponse> execute(CodeRequest request) {
        NameAndEmailDTO nameAndEmailDTO = googleAuthService.execute(request);

        String name = nameAndEmailDTO.getName();
        String email = nameAndEmailDTO.getEmail();

        Integer status = saveHr(name, email);

        String refreshToken = jwtTokenProvider.generateRefreshToken(email, TokenRole.HR_MANAGER);

        saveRefreshToken(email,refreshToken);

        return new ResponseEntity<>(new TokenResponse(
                jwtTokenProvider.generateAccessToken(email, TokenRole.HR_MANAGER),
                refreshToken),
                HttpStatus.valueOf(status)
        );
    }

    private Integer saveHr(String name, String email) {
        if (hrRepository.findById(email).isEmpty()) {

            Company company = Company.builder()
                    .name(name)
                    .rankValue(Rank.JUNIOR)
                    .build();
            companyRepository.save(company);

            User user = User.builder()
                    .email(email)
                    .name(name)
                    .build();
            userRepository.save(user);

            hrRepository.save(
                    Hr.builder()
                            .user(user)
                            .company(company)
                            .build()
            );
            return 201;
        }
        return 200;
    }

    private void saveRefreshToken(String email, String refreshToken) {
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .email(email)
                        .token(refreshToken)
                        .ttl(jwtProperties.getRefreshExp())
                        .build()
        );
    }

}
