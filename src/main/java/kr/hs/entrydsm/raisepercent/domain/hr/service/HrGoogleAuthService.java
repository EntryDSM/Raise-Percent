package kr.hs.entrydsm.raisepercent.domain.hr.service;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.repositories.CompanyRepository;
import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.global.properties.AuthProperties;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import kr.hs.entrydsm.raisepercent.global.security.jwt.dto.TokenResponse;
import kr.hs.entrydsm.raisepercent.global.util.auth.component.GoogleAuthComponent;
import kr.hs.entrydsm.raisepercent.global.util.auth.dto.PersonalInformation;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.client.GoogleAuth;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.client.GoogleInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class HrGoogleAuthService {

    private final GoogleAuth googleAuth;
    private final GoogleInfo googleInfo;
    private final AuthProperties authProperties;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final HrRepository hrRepository;
    private final GoogleAuthComponent googleAuthComponent;
    private static int status = 200;

    @Transactional
    public ResponseEntity<TokenResponse> execute(CodeRequest request){
        PersonalInformation personalInformation = googleAuthComponent.execute(request);
        String email = personalInformation.getEmail();
        String name = personalInformation.getName();

        saveHr(email,name);

        return new ResponseEntity<>(new TokenResponse(
                jwtTokenProvider.generateAccessToken(email, "hr"),
                jwtTokenProvider.generateRefreshToken(email, "hr")),
                HttpStatus.valueOf(status)
        );

    }


    private void saveHr(String email, String name){
        if(hrRepository.findById(email).isEmpty()){
            Hr.builder()
                    .user(
                            userRepository.save(
                                    User.builder()
                                            .email(email)
                                            .build()
                            )
                    )
                    .company(
                            companyRepository.save(
                                    Company.builder()
                                            .name(name)
                                            .rankValue(Rank.JUNIOR)
                                            .build()
                            )
                    )
                    .build();
        }
        status = 201;
    }
}
