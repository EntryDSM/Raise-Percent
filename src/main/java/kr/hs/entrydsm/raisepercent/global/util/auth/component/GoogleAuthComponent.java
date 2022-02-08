package kr.hs.entrydsm.raisepercent.global.util.auth.component;

import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.global.properties.AuthProperties;
import kr.hs.entrydsm.raisepercent.global.util.auth.dto.PersonalInformation;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.client.GoogleAuth;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.client.GoogleInfo;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.request.GoogleCodeRequest;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.GoogleInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class GoogleAuthComponent {

    private final GoogleAuth googleAuth;
    private final GoogleInfo googleInfo;
    private final AuthProperties authProperties;


    public PersonalInformation execute(CodeRequest request) {
        String accessToken = googleAuth.googleAuth(
                GoogleCodeRequest.builder()
                        .code(URLDecoder.decode(request.getCode(), StandardCharsets.UTF_8))
                        .clientId(authProperties.getClientId())
                        .clientSecret(authProperties.getClientSecret())
                        .redirectUri(authProperties.getRedirectUrl())
                        .build()
        ).getAccessToken();

        GoogleInfoResponse googleInfoResponse = googleInfo.googleInfo(accessToken);

        String email = googleInfoResponse.getEmail();
        String name = googleInfoResponse.getName();

        return new PersonalInformation(email,name);
    }

}
